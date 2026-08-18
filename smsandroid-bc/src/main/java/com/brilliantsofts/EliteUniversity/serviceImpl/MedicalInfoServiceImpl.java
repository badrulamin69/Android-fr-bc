package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.MedicalInfoRequest;
import com.brilliantsofts.EliteUniversity.dto.response.MedicalInfoResponse;
import com.brilliantsofts.EliteUniversity.entity.MedicalInfo;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.dto.mapper.MedicalInfoMapper;
import com.brilliantsofts.EliteUniversity.repository.MedicalInfoRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.MedicalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MedicalInfoServiceImpl implements MedicalInfoService {
    @Autowired
    private MedicalInfoRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public MedicalInfoResponse create(MedicalInfoRequest request) {
        MedicalInfo entity = MedicalInfoMapper.toEntity(request);
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        return MedicalInfoMapper.toResponse(repository.save(entity));
    }

    @Override
    public MedicalInfoResponse update(Long id, MedicalInfoRequest request) {
        MedicalInfo entity = repository.findById(id).orElseThrow(() -> new RuntimeException("MedicalInfo not found"));
        entity.setBloodGroup(request.getBloodGroup());
        entity.setHeight(request.getHeight());
        entity.setWeight(request.getWeight());
        entity.setAllergies(request.getAllergies());
        entity.setMedications(request.getMedications());
        entity.setConditions(request.getConditions());
        entity.setEmergencyContact(request.getEmergencyContact());
        entity.setEmergencyPhone(request.getEmergencyPhone());
        entity.setInsuranceProvider(request.getInsuranceProvider());
        entity.setInsuranceNumber(request.getInsuranceNumber());
        entity.setDoctorName(request.getDoctorName());
        entity.setDoctorPhone(request.getDoctorPhone());
        entity.setNotes(request.getNotes());
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        return MedicalInfoMapper.toResponse(repository.save(entity));
    }

    @Override
    public MedicalInfoResponse getById(Long id) {
        return MedicalInfoMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("MedicalInfo not found")));
    }

    @Override
    public Page<MedicalInfoResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(MedicalInfoMapper::toResponse);
        }
        return repository.findAll(pageable).map(MedicalInfoMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        return stats;
    }
}
