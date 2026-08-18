package com.brilliantsofts.EliteUniversity.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private final Map<Long, Map<String, Object>> clubs = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<Page<Map<String, Object>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        List<Map<String, Object>> list = new ArrayList<>(clubs.values());
        if (search != null && !search.isEmpty()) {
            list.removeIf(c -> !String.valueOf(c.getOrDefault("name", "")).toLowerCase().contains(search.toLowerCase()));
        }
        int start = Math.min(page * size, list.size());
        int end = Math.min((page + 1) * size, list.size());
        List<Map<String, Object>> sublist = list.subList(start, end);
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(new PageImpl<>(sublist, pageable, list.size()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        Map<String, Object> club = clubs.get(id);
        if (club == null) {
            Map<String, Object> mock = new HashMap<>();
            mock.put("id", id);
            mock.put("name", "Sample Club " + id);
            return ResponseEntity.ok(mock);
        }
        return ResponseEntity.ok(club);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> club) {
        Long id = idGenerator.getAndIncrement();
        club.put("id", id);
        clubs.put(id, club);
        return ResponseEntity.status(HttpStatus.CREATED).body(club);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Map<String, Object> club) {
        club.put("id", id);
        clubs.put(id, club);
        return ResponseEntity.ok(club);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clubs.remove(id);
        return ResponseEntity.noContent().build();
    }
}
