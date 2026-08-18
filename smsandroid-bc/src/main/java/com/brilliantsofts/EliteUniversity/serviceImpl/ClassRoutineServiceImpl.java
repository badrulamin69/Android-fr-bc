package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.ClassRoutineMapper;
import com.brilliantsofts.EliteUniversity.dto.request.ClassRoutineRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ClassRoutineResponse;
import com.brilliantsofts.EliteUniversity.dto.response.ConflictCheckResponse;
import com.brilliantsofts.EliteUniversity.entity.ClassRoutine;
import com.brilliantsofts.EliteUniversity.repository.ClassRoutineRepository;
import com.brilliantsofts.EliteUniversity.service.ClassRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassRoutineServiceImpl implements ClassRoutineService {

    @Autowired
    private ClassRoutineRepository repository;

    @Override
    public ClassRoutineResponse create(ClassRoutineRequest request) {
        ClassRoutine entity = ClassRoutineMapper.toEntity(request);
        return ClassRoutineMapper.toResponse(repository.save(entity));
    }

    @Override
    public ClassRoutineResponse update(Long id, ClassRoutineRequest request) {
        ClassRoutine entity = repository.findById(id).orElseThrow(() -> new RuntimeException("ClassRoutine not found"));
        entity.setSubjectId(request.getSubjectId());
        entity.setAdministrationId(request.getAdministrationId());
        entity.setSectionId(request.getSectionId());
        entity.setSemesterId(request.getSemesterId());
        entity.setBatchId(request.getBatchId());
        entity.setTimeSlotId(request.getTimeSlotId());
        entity.setClassroomId(request.getClassroomId());
        entity.setDayOfWeek(request.getDayOfWeek());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setRoom(request.getRoom());
        entity.setBuilding(request.getBuilding());
        entity.setClassType(request.getClassType());
        entity.setShift(request.getShift());
        entity.setActive(request.isActive());
        return ClassRoutineMapper.toResponse(repository.save(entity));
    }

    @Override
    public ClassRoutineResponse getById(Long id) {
        return ClassRoutineMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("ClassRoutine not found")));
    }

    @Override
    public List<ClassRoutineResponse> getAll(Long semesterId, Long sectionId, String dayOfWeek) {
        return repository.findAllFiltered(semesterId, sectionId, dayOfWeek).stream()
                .map(ClassRoutineMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ClassRoutineResponse> getBySemesterAndSection(Long semesterId, Long sectionId) {
        return repository.findBySemesterIdAndSectionId(semesterId, sectionId).stream()
                .map(ClassRoutineMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ConflictCheckResponse checkConflicts(ClassRoutineRequest request) {
        ConflictCheckResponse response = new ConflictCheckResponse();
        List<ClassRoutine> existing = repository.findBySemesterId(request.getSemesterId());
        for (ClassRoutine routine : existing) {
            if (routine.getSectionId().equals(request.getSectionId()) &&
                routine.getDayOfWeek().equals(request.getDayOfWeek()) &&
                routine.getStartTime().equals(request.getStartTime())) {
                response.setHasConflict(true);
                response.setConflictType("TIME_CONFLICT");
                response.setConflictMessage("A routine already exists for this section on " + request.getDayOfWeek() + " at " + request.getStartTime());
                response.setConflictingRoutineId(routine.getId());
                response.setConflictingDetails("Section: " + routine.getSectionId() + ", Day: " + routine.getDayOfWeek());
                return response;
            }
            if (routine.getClassroomId() != null && routine.getClassroomId().equals(request.getClassroomId()) &&
                routine.getDayOfWeek().equals(request.getDayOfWeek()) &&
                routine.getStartTime().equals(request.getStartTime())) {
                response.setHasConflict(true);
                response.setConflictType("ROOM_CONFLICT");
                response.setConflictMessage("Classroom is already booked for this time slot");
                response.setConflictingRoutineId(routine.getId());
                response.setConflictingDetails("Room: " + routine.getClassroomId() + ", Day: " + routine.getDayOfWeek());
                return response;
            }
        }
        response.setHasConflict(false);
        return response;
    }
}
