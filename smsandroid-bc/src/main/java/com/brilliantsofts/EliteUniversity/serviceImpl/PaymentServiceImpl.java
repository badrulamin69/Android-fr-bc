package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.PaymentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PaymentResponse;
import com.brilliantsofts.EliteUniversity.entity.Payment;
import com.brilliantsofts.EliteUniversity.enums.PaymentStatus;
import com.brilliantsofts.EliteUniversity.dto.mapper.PaymentMapper;
import com.brilliantsofts.EliteUniversity.repository.ApplicantRepository;
import com.brilliantsofts.EliteUniversity.repository.PaymentRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository repository;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public PaymentResponse create(PaymentRequest request) {
        Payment entity = PaymentMapper.toEntity(request);
        if (request.getApplicantId() != null) entity.setApplicant(applicantRepository.findById(request.getApplicantId()).orElse(null));
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        return PaymentMapper.toResponse(repository.save(entity));
    }
    @Override
    public PaymentResponse update(Long id, PaymentRequest request) {
        Payment entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        entity.setTransactionId(request.getTransactionId());
        entity.setAmount(request.getAmount());
        entity.setStatus(request.getStatus());
        entity.setPaymentMethod(request.getPaymentMethod());
        entity.setNotes(request.getNotes());
        entity.setCreatedBy(request.getCreatedBy());
        if (request.getApplicantId() != null) entity.setApplicant(applicantRepository.findById(request.getApplicantId()).orElse(null));
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        return PaymentMapper.toResponse(repository.save(entity));
    }
    @Override
    public PaymentResponse getById(Long id) {
        return PaymentMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found")));
    }
    @Override
    public PaymentResponse getByTransactionId(String transactionId) {
        return PaymentMapper.toResponse(repository.findByTransactionId(transactionId));
    }
    @Override
    public List<PaymentResponse> getAll() {
        return repository.findAll().stream().map(PaymentMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<PaymentResponse> getByApplicant(Long applicantId) {
        return repository.findByApplicantId(applicantId).stream().map(PaymentMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<PaymentResponse> getByStudent(Long studentId) {
        return repository.findByStudentId(studentId).stream().map(PaymentMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<PaymentResponse> getByStatus(PaymentStatus status) {
        return repository.findByStatus(status).stream().map(PaymentMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
