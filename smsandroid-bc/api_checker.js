const fs = require('fs');
const path = require('path');

function walkDir(dir, ext, callback) {
    fs.readdirSync(dir).forEach(f => {
        let dirPath = path.join(dir, f);
        let isDirectory = fs.statSync(dirPath).isDirectory();
        if (isDirectory) {
            walkDir(dirPath, ext, callback);
        } else if (dirPath.endsWith(ext)) {
            callback(dirPath);
        }
    });
}

const backendEndpoints = [];
walkDir('f:/Spring-ng/EliteUniversity/src/main/java', '.java', (filepath) => {
    const content = fs.readFileSync(filepath, 'utf8');
    if (!content.includes('@RestController') && !content.includes('@Controller')) return;

    let basePaths = [''];
    const classMappingMatch = content.match(/@RequestMapping\s*\(\s*(?:(?:value|path)\s*=\s*)?(?:\{([^}]+)\}|["']([^"']+)["'])/);
    if (classMappingMatch) {
        if (classMappingMatch[1]) {
            basePaths = classMappingMatch[1].split(',').map(s => s.replace(/["'\s]/g, '')).filter(Boolean);
        } else if (classMappingMatch[2]) {
            basePaths = [classMappingMatch[2]];
        }
    }

    const methodRegex = /@(GetMapping|PostMapping|PutMapping|DeleteMapping|PatchMapping|RequestMapping)\s*(?:\(\s*(?:(?:value|path)\s*=\s*)?(?:\{([^}]+)\}|["']([^"']+)["'])?\s*\))?/g;
    let match;
    while ((match = methodRegex.exec(content)) !== null) {
        const type = match[1].replace('Mapping', '').toUpperCase();
        let methodPaths = [''];
        if (match[2]) {
            methodPaths = match[2].split(',').map(s => s.replace(/["'\s]/g, '')).filter(Boolean);
        } else if (match[3]) {
            methodPaths = [match[3]];
        }
        
        basePaths.forEach(bp => {
            methodPaths.forEach(mp => {
                let fullPath = bp + mp;
                fullPath = fullPath.replace(/\/\//g, '/').replace(/\/$/, '');
                if (fullPath === '') fullPath = '/';
                backendEndpoints.push({
                    method: type === 'REQUEST' ? 'ANY' : type,
                    path: fullPath,
                    file: filepath
                });
            });
        });
    }
});

const frontendEndpoints = [];
walkDir('f:/Spring-ng/EliteUniversity/angular-ui/src/app/services', '.ts', (filepath) => {
    const content = fs.readFileSync(filepath, 'utf8');
    
    // Find apiUrl or baseUrl or publicUrl
    let apiUrl = '';
    const apiUrlMatch = content.match(/(?:apiUrl|baseUrl|publicUrl)\s*=\s*`\$\{environment\.apiUrl\}([^`]*)`/);
    if (apiUrlMatch) {
        apiUrl = apiUrlMatch[1];
    } else {
        const apiUrlStringMatch = content.match(/(?:apiUrl|baseUrl|publicUrl)\s*=\s*environment\.apiUrl\s*\+\s*['"]([^'"]*)['"]/);
        if (apiUrlStringMatch) {
            apiUrl = apiUrlStringMatch[1];
        } else {
            const standaloneMatch = content.match(/(?:apiUrl|baseUrl|publicUrl)\s*=\s*['"]\/api([^'"]*)['"]/);
            if (standaloneMatch) {
                apiUrl = '/api' + standaloneMatch[1];
            }
        }
    }

    const httpRegex = /this\.http\.(get|post|put|delete|patch)[^(\n]*\(([^,\n]+)/g;
    let match;
    while ((match = httpRegex.exec(content)) !== null) {
        const method = match[1].toUpperCase();
        let urlArg = match[2].trim();
        
        // Strip trailing .pipe(...), .subscribe(...), etc.
        urlArg = urlArg.replace(/\)\s*\.pipe\(.*/, '').replace(/\)\s*\.subscribe\(.*/, '');

        let pathStr = '';
        
        if (urlArg.startsWith('this.apiUrl') || urlArg.startsWith('this.baseUrl') || urlArg.startsWith('this.publicUrl')) {
            if (urlArg === 'this.apiUrl' || urlArg === 'this.baseUrl' || urlArg === 'this.publicUrl') {
                pathStr = apiUrl;
            } else if (urlArg.includes('+')) {
                pathStr = apiUrl + urlArg.split('+')[1].replace(/['"]/g, '').trim();
            }
        } else if (urlArg.startsWith('`')) {
            pathStr = urlArg.replace(/^`|`$/g, '');
            pathStr = pathStr.replace(/\$\{(?:this\.apiUrl|this\.baseUrl|this\.publicUrl)\}/g, apiUrl);
            pathStr = pathStr.replace('${environment.apiUrl}', '');
            pathStr = pathStr.replace(/\$\{[^}]+\}/g, '{param}');
        } else {
            pathStr = urlArg + " (unresolved)";
        }

        pathStr = pathStr.replace(/['"`)]/g, '');
        pathStr = pathStr.replace(/;.*/, '');
        pathStr = pathStr.replace(/\?.*/, '');
        pathStr = pathStr.replace(/\/\//g, '/').replace(/\/$/, '');
        if (pathStr === '') pathStr = '/';

        frontendEndpoints.push({
            method: method,
            path: pathStr,
            file: filepath,
            raw: urlArg
        });
    }
});

function normalizePath(p) {
    return p.replace(/\{[^}]+\}/g, '{param}').replace(/^\/api\/v1/, '').replace(/^\/api/, '');
}

const backendPaths = backendEndpoints.map(e => ({
    key: `${e.method} ${normalizePath(e.path)}`,
    original: e
}));

const frontendPaths = frontendEndpoints.filter(e => !e.path.includes('(unresolved)') && e.path !== '/').map(e => ({
    key: `${e.method} ${normalizePath(e.path)}`,
    original: e
}));

const mismatches = [];

frontendPaths.forEach(fe => {
    const found = backendPaths.find(be => be.key === fe.key || be.key.replace('/api', '') === fe.key.replace('/api', '') || be.key === fe.key.replace(/^\//, ''));
    if (!found) {
        mismatches.push({
            frontendPath: fe.original.path,
            normalizedKey: fe.key,
            method: fe.original.method,
            file: path.basename(fe.original.file),
            raw: fe.original.raw
        });
    }
});

fs.writeFileSync('f:/Spring-ng/EliteUniversity/scratch_mismatches.json', JSON.stringify(mismatches, null, 2));
console.log(`Found ${backendEndpoints.length} backend endpoints and ${frontendEndpoints.length} frontend endpoints.`);
console.log(`Wrote ${mismatches.length} true mismatches to scratch_mismatches.json`);

