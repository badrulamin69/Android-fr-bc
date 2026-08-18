package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.DepartmentAllocationMapper;
import com.brilliantsofts.EliteUniversity.dto.mapper.PreAdmissionRegistrationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AdmissionConfirmationRequest;
import com.brilliantsofts.EliteUniversity.dto.request.DocumentSubmitRequest;
import com.brilliantsofts.EliteUniversity.dto.request.DocumentVerifyRequest;
import com.brilliantsofts.EliteUniversity.dto.request.FeePaymentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionConfirmationResponse;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionDocumentResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionConfirmation;
import com.brilliantsofts.EliteUniversity.entity.AdmissionDocument;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionConfirmationMapper;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionDocumentMapper;
import com.brilliantsofts.EliteUniversity.repository.*;
import com.brilliantsofts.EliteUniversity.service.AdmissionConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdmissionConfirmationServiceImpl implements AdmissionConfirmationService {

    @Autowired
    private AdmissionConfirmationRepository confirmationRepository;
    @Autowired
    private AdmissionDocumentRepository documentRepository;
    @Autowired
    private PreAdmissionRegistrationRepository preAdmissionRepo;
    @Autowired
    private DepartmentAllocationRepository allocationRepo;
    @Autowired
    private ProgramRepository programRepo;
    @Autowired
    private DepartmentRepository departmentRepo;
    @Autowired
    private BatchRepository batchRepo;

    private AdmissionConfirmationResponse enrichResponse(AdmissionConfirmation entity) {
        if (entity == null) return null;
        AdmissionConfirmationResponse res = AdmissionConfirmationMapper.toResponse(entity);

        Long regId = entity.getRegistrationId();
        Long allocId = entity.getAllocationId();

        if (allocId != null) {
            allocationRepo.findById(allocId).ifPresent(alloc -> {
                res.setAllocation(DepartmentAllocationMapper.toResponse(alloc));
                if (res.getRegistrationId() == null && alloc.getRegistrationId() != null) {
                    res.setRegistrationId(alloc.getRegistrationId());
                }
                if (alloc.getAllocatedProgramId() != null) {
                    programRepo.findById(alloc.getAllocatedProgramId()).ifPresent(p -> res.setProgramName(p.getName()));
                }
                if (alloc.getAllocatedDepartmentId() != null) {
                    departmentRepo.findById(alloc.getAllocatedDepartmentId()).ifPresent(d -> res.setDepartmentName(d.getName()));
                }
                if (alloc.getAllocatedBatchId() != null) {
                    batchRepo.findById(alloc.getAllocatedBatchId()).ifPresent(b -> res.setBatchName(b.getName()));
                }
            });
        }

        Long finalRegId = res.getRegistrationId() != null ? res.getRegistrationId() : regId;
        if (finalRegId != null) {
            preAdmissionRepo.findById(finalRegId).ifPresent(reg -> {
                res.setRegistration(PreAdmissionRegistrationMapper.toResponse(reg));
                res.setRegistrationNumber(reg.getRegistrationNumber());
                String fullName = ((reg.getFirstName() != null ? reg.getFirstName() : "") + " " + (reg.getLastName() != null ? reg.getLastName() : "")).trim();
                res.setApplicantName(!fullName.isEmpty() ? fullName : "Applicant #" + reg.getId());
                res.setApplicantEmail(reg.getEmail());
                res.setApplicantPhone(reg.getPhone());
                if (res.getProgramName() == null && reg.getProgramPreference1() != null) {
                    res.setProgramName(reg.getProgramPreference1());
                }
            });
        }

        return res;
    }

    private void syncConfirmationsFromAllocationsAndRegistrations() {
        var allocations = allocationRepo.findAll();
        for (var alloc : allocations) {
            if (alloc.getId() != null && confirmationRepository.findByAllocationId(alloc.getId()).isEmpty()) {
                AdmissionConfirmation conf = new AdmissionConfirmation();
                conf.setConfirmationNumber("CONF-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                conf.setAllocationId(alloc.getId());
                conf.setRegistrationId(alloc.getRegistrationId());
                boolean isConf = "CONFIRMED".equalsIgnoreCase(alloc.getStatus());
                conf.setStatus(isConf ? "CONFIRMED" : "PENDING");
                conf.setDocumentsSubmitted(true);
                conf.setDocumentsVerified(isConf);
                conf.setFeePaid(isConf);
                if (isConf) {
                    conf.setFeeAmount(15000.0);
                    conf.setFeePaidAt(LocalDateTime.now());
                    conf.setConfirmedAt(LocalDateTime.now());
                }
                confirmationRepository.save(conf);
            }
        }

        if (confirmationRepository.count() == 0) {
            var regs = preAdmissionRepo.findAll();
            for (var reg : regs) {
                if (reg.getId() != null && confirmationRepository.findByRegistrationId(reg.getId()).isEmpty()) {
                    AdmissionConfirmation conf = new AdmissionConfirmation();
                    conf.setConfirmationNumber("CONF-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                    conf.setRegistrationId(reg.getId());
                    conf.setStatus("PENDING");
                    conf.setDocumentsSubmitted(true);
                    conf.setDocumentsVerified(false);
                    conf.setFeePaid(false);
                    confirmationRepository.save(conf);
                }
            }
        }
    }

    @Override
    public AdmissionConfirmationResponse create(AdmissionConfirmationRequest request) {
        AdmissionConfirmation entity = AdmissionConfirmationMapper.toEntity(request);
        if (entity.getConfirmationNumber() == null || entity.getConfirmationNumber().isBlank()) {
            entity.setConfirmationNumber("CONF-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }
        if (entity.getStatus() == null || entity.getStatus().isBlank()) {
            entity.setStatus("PENDING");
        }
        return enrichResponse(confirmationRepository.save(entity));
    }

    @Override
    public AdmissionConfirmationResponse update(Long id, AdmissionConfirmationRequest request) {
        AdmissionConfirmation entity = confirmationRepository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionConfirmation not found"));
        if (request.getConfirmationNumber() != null) entity.setConfirmationNumber(request.getConfirmationNumber());
        if (request.getAllocationId() != null) entity.setAllocationId(request.getAllocationId());
        if (request.getRegistrationId() != null) entity.setRegistrationId(request.getRegistrationId());
        if (request.getStatus() != null) entity.setStatus(request.getStatus());
        if (request.getDocumentsSubmitted() != null) entity.setDocumentsSubmitted(request.getDocumentsSubmitted());
        if (request.getDocumentsVerified() != null) entity.setDocumentsVerified(request.getDocumentsVerified());
        if (request.getDocumentsVerifiedBy() != null) entity.setDocumentsVerifiedBy(request.getDocumentsVerifiedBy());
        if (request.getDocumentsVerifiedAt() != null) entity.setDocumentsVerifiedAt(request.getDocumentsVerifiedAt());
        if (request.getDocumentRemarks() != null) entity.setDocumentRemarks(request.getDocumentRemarks());
        if (request.getFeePaid() != null) entity.setFeePaid(request.getFeePaid());
        if (request.getFeeAmount() != null) entity.setFeeAmount(request.getFeeAmount());
        if (request.getFeePaymentMethod() != null) entity.setFeePaymentMethod(request.getFeePaymentMethod());
        if (request.getFeeTransactionId() != null) entity.setFeeTransactionId(request.getFeeTransactionId());
        if (request.getFeePaidAt() != null) entity.setFeePaidAt(request.getFeePaidAt());
        if (request.getConfirmedAt() != null) entity.setConfirmedAt(request.getConfirmedAt());
        if (request.getConfirmedBy() != null) entity.setConfirmedBy(request.getConfirmedBy());
        if (request.getRemarks() != null) entity.setRemarks(request.getRemarks());
        if (request.getSessionId() != null) entity.setSessionId(request.getSessionId());
        return enrichResponse(confirmationRepository.save(entity));
    }

    @Override
    public AdmissionConfirmationResponse getById(Long id) {
        AdmissionConfirmation entity = confirmationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AdmissionConfirmation not found"));
        return enrichResponse(entity);
    }

    @Override
    public Page<AdmissionConfirmationResponse> getAll(Pageable pageable, String search, String status, Boolean documentsVerified, Boolean feePaid) {
        syncConfirmationsFromAllocationsAndRegistrations();

        List<AdmissionConfirmation> all = confirmationRepository.findAll();
        List<AdmissionConfirmationResponse> enrichedList = all.stream()
                .map(this::enrichResponse)
                .filter(res -> {
                    if (status != null && !status.isBlank() && !status.equalsIgnoreCase("ALL")) {
                        if (!status.equalsIgnoreCase(res.getStatus())) return false;
                    }
                    if (documentsVerified != null) {
                        if (!Objects.equals(documentsVerified, res.getDocumentsVerified())) return false;
                    }
                    if (feePaid != null) {
                        if (!Objects.equals(feePaid, res.getFeePaid())) return false;
                    }
                    if (search != null && !search.isBlank()) {
                        String s = search.toLowerCase();
                        boolean matchNo = res.getConfirmationNumber() != null && res.getConfirmationNumber().toLowerCase().contains(s);
                        boolean matchReg = res.getRegistrationNumber() != null && res.getRegistrationNumber().toLowerCase().contains(s);
                        boolean matchName = res.getApplicantName() != null && res.getApplicantName().toLowerCase().contains(s);
                        boolean matchProg = res.getProgramName() != null && res.getProgramName().toLowerCase().contains(s);
                        boolean matchDept = res.getDepartmentName() != null && res.getDepartmentName().toLowerCase().contains(s);
                        if (!matchNo && !matchReg && !matchName && !matchProg && !matchDept) return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), enrichedList.size());
        List<AdmissionConfirmationResponse> subList = (start <= end && start < enrichedList.size()) ? enrichedList.subList(start, end) : Collections.emptyList();

        return new PageImpl<>(subList, pageable, enrichedList.size());
    }

    @Override
    public List<AdmissionConfirmationResponse> getMy(Long registrationId) {
        syncConfirmationsFromAllocationsAndRegistrations();
        return confirmationRepository.findByRegistrationId(registrationId).stream()
                .map(this::enrichResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AdmissionConfirmationResponse initiate(Long allocationId) {
        AdmissionConfirmation existing = confirmationRepository.findByAllocationId(allocationId).orElse(null);
        if (existing != null) {
            return enrichResponse(existing);
        }
        var alloc = allocationRepo.findById(allocationId).orElse(null);
        AdmissionConfirmation entity = new AdmissionConfirmation();
        entity.setConfirmationNumber("CONF-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setAllocationId(allocationId);
        if (alloc != null) {
            entity.setRegistrationId(alloc.getRegistrationId());
        }
        entity.setStatus("PENDING");
        entity.setDocumentsSubmitted(false);
        entity.setDocumentsVerified(false);
        entity.setFeePaid(false);
        return enrichResponse(confirmationRepository.save(entity));
    }

    @Override
    public AdmissionConfirmationResponse submitDocuments(Long confirmationId, List<DocumentSubmitRequest> documents) {
        AdmissionConfirmation entity = confirmationRepository.findById(confirmationId).orElseThrow(() -> new RuntimeException("AdmissionConfirmation not found"));
        if (documents != null) {
            for (DocumentSubmitRequest docRequest : documents) {
                AdmissionDocument doc = new AdmissionDocument();
                doc.setConfirmationId(confirmationId);
                doc.setDocumentType(docRequest.getDocumentType());
                doc.setDocumentName(docRequest.getDocumentName());
                doc.setFileUrl(docRequest.getFileUrl());
                doc.setStatus("SUBMITTED");
                documentRepository.save(doc);
            }
        }
        entity.setDocumentsSubmitted(true);
        entity.setStatus("DOCUMENTS_SUBMITTED");
        return enrichResponse(confirmationRepository.save(entity));
    }

    @Override
    public AdmissionConfirmationResponse verifyDocuments(Long confirmationId, DocumentVerifyRequest request) {
        AdmissionConfirmation entity = confirmationRepository.findById(confirmationId).orElseThrow(() -> new RuntimeException("AdmissionConfirmation not found"));
        entity.setDocumentsVerified(request.getVerified());
        entity.setDocumentRemarks(request.getRemarks());
        entity.setDocumentsVerifiedAt(LocalDateTime.now());
        if (Boolean.TRUE.equals(request.getVerified())) {
            entity.setStatus("DOCUMENTS_VERIFIED");
            List<AdmissionDocument> docs = documentRepository.findByConfirmationId(confirmationId);
            for (AdmissionDocument doc : docs) {
                doc.setStatus("VERIFIED");
                documentRepository.save(doc);
            }
        } else {
            entity.setStatus("DOCUMENTS_REJECTED");
        }
        return enrichResponse(confirmationRepository.save(entity));
    }

    @Override
    public AdmissionConfirmationResponse payFee(Long confirmationId, FeePaymentRequest request) {
        AdmissionConfirmation entity = confirmationRepository.findById(confirmationId).orElseThrow(() -> new RuntimeException("AdmissionConfirmation not found"));
        entity.setFeePaid(true);
        entity.setFeeAmount(request.getAmount());
        entity.setFeePaymentMethod(request.getPaymentMethod());
        entity.setFeeTransactionId(request.getTransactionId());
        entity.setFeePaidAt(LocalDateTime.now());
        entity.setStatus("FEE_PAID");
        return enrichResponse(confirmationRepository.save(entity));
    }

    @Override
    public AdmissionConfirmationResponse confirm(Long confirmationId) {
        AdmissionConfirmation entity = confirmationRepository.findById(confirmationId).orElseThrow(() -> new RuntimeException("AdmissionConfirmation not found"));
        entity.setStatus("CONFIRMED");
        entity.setConfirmedAt(LocalDateTime.now());
        return enrichResponse(confirmationRepository.save(entity));
    }

    @Override
    public List<AdmissionDocumentResponse> getDocuments(Long confirmationId) {
        return documentRepository.findByConfirmationId(confirmationId).stream().map(AdmissionDocumentMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getStats() {
        syncConfirmationsFromAllocationsAndRegistrations();
        Map<String, Object> stats = new HashMap<>();
        List<AdmissionConfirmation> all = confirmationRepository.findAll();
        long total = all.size();
        long pending = all.stream().filter(c -> "PENDING".equalsIgnoreCase(c.getStatus())).count();
        long documentsSubmitted = all.stream().filter(c -> "DOCUMENTS_SUBMITTED".equalsIgnoreCase(c.getStatus()) || Boolean.TRUE.equals(c.getDocumentsSubmitted())).count();
        long documentsVerified = all.stream().filter(c -> "DOCUMENTS_VERIFIED".equalsIgnoreCase(c.getStatus()) || Boolean.TRUE.equals(c.getDocumentsVerified())).count();
        long feePaid = all.stream().filter(c -> "FEE_PAID".equalsIgnoreCase(c.getStatus()) || Boolean.TRUE.equals(c.getFeePaid())).count();
        long confirmed = all.stream().filter(c -> "CONFIRMED".equalsIgnoreCase(c.getStatus())).count();
        long enrolled = all.stream().filter(c -> "ENROLLED".equalsIgnoreCase(c.getStatus())).count();

        stats.put("total", total);
        stats.put("totalConfirmations", total);
        stats.put("pending", pending);
        stats.put("pendingCount", pending);
        stats.put("documentsSubmitted", documentsSubmitted);
        stats.put("documentsVerified", documentsVerified);
        stats.put("feePaid", feePaid);
        stats.put("confirmed", confirmed);
        stats.put("confirmedCount", confirmed);
        stats.put("enrolled", enrolled);
        return stats;
    }

    @Override
    public void delete(Long id) {
        confirmationRepository.deleteById(id);
    }
}
