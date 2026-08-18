package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.ClassRoutineRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ClassRoutineResponse;
import com.brilliantsofts.EliteUniversity.dto.response.ConflictCheckResponse;
import com.brilliantsofts.EliteUniversity.service.ClassRoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-routines")
@RequiredArgsConstructor
public class ClassRoutineController {
    private final ClassRoutineService service;

    @PostMapping
    public ResponseEntity<ClassRoutineResponse> create(@RequestBody ClassRoutineRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoutineResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassRoutineResponse>> getAll(
            @RequestParam(required = false) Long semesterId,
            @RequestParam(required = false) Long sectionId,
            @RequestParam(required = false) String dayOfWeek) {
        return ResponseEntity.ok(service.getAll(semesterId, sectionId, dayOfWeek));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoutineResponse> update(@PathVariable Long id, @RequestBody ClassRoutineRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/semester/{semesterId}/section/{sectionId}")
    public ResponseEntity<List<ClassRoutineResponse>> getBySemesterAndSection(
            @PathVariable Long semesterId, @PathVariable Long sectionId) {
        return ResponseEntity.ok(service.getBySemesterAndSection(semesterId, sectionId));
    }

    @PostMapping("/check-conflicts")
    public ResponseEntity<ConflictCheckResponse> checkConflicts(@RequestBody ClassRoutineRequest request) {
        return ResponseEntity.ok(service.checkConflicts(request));
    }

    @PostMapping("/publish/{semesterId}")
    public ResponseEntity<Void> publishRoutine(@PathVariable Long semesterId) {
        return ResponseEntity.ok().build();
    }
}
