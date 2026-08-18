const fs = require('fs');

const mismatches = JSON.parse(fs.readFileSync('f:/Spring-ng/EliteUniversity/scratch_mismatches.json', 'utf8'));

let report = `# API Mismatches Report

The following endpoints are called by the Angular frontend but do not have a matching \`@RequestMapping\` in the Spring Boot backend controllers.

`;

const byService = {};
for (const m of mismatches) {
    if (!byService[m.file]) byService[m.file] = [];
    byService[m.file].push(m);
}

for (const [service, calls] of Object.entries(byService)) {
    report += `## ${service}\n\n`;
    for (const call of calls) {
        report += `- **${call.method}** \`${call.frontendPath}\` (Raw: \`${call.raw}\`)\n`;
    }
    report += '\n';
}

fs.writeFileSync('f:/Spring-ng/EliteUniversity/mismatches_report.md', report);
console.log('Report generated at f:/Spring-ng/EliteUniversity/mismatches_report.md');
