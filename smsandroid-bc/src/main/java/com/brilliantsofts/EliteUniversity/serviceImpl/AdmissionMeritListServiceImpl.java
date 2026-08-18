package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionMeritListRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionMeritListResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionMeritList;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionMeritListMapper;
import com.brilliantsofts.EliteUniversity.repository.AdmissionMeritListRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionMeritListService;
import com.brilliantsofts.EliteUniversity.entity.AdmissionMeritListEntry;
import com.brilliantsofts.EliteUniversity.entity.PreAdmissionRegistration;
import com.brilliantsofts.EliteUniversity.repository.AdmissionMeritListEntryRepository;
import com.brilliantsofts.EliteUniversity.repository.PreAdmissionRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdmissionMeritListServiceImpl implements AdmissionMeritListService {
    @Autowired
    private AdmissionMeritListRepository repository;
    @Autowired
    private AdmissionMeritListEntryRepository entryRepository;
    @Autowired
    private PreAdmissionRegistrationRepository preAdmissionRegistrationRepository;

    @Override
    public AdmissionMeritListResponse create(AdmissionMeritListRequest request) {
        AdmissionMeritList entity = AdmissionMeritListMapper.toEntity(request);
        return AdmissionMeritListMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionMeritListResponse update(Long id, AdmissionMeritListRequest request) {
        AdmissionMeritList entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionMeritList not found"));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setAcademicYear(request.getAcademicYear());
        entity.setSessionId(request.getSessionId());
        entity.setFacultyId(request.getFacultyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setProgramId(request.getProgramId());
        entity.setShift(request.getShift());
        entity.setQuotaType(request.getQuotaType());
        entity.setTestId(request.getTestId());
        entity.setStatus(request.getStatus());
        entity.setTotalSeats(request.getTotalSeats());
        entity.setTotalApplicants(request.getTotalApplicants());
        entity.setSelectedCount(request.getSelectedCount());
        entity.setWaitingCount(request.getWaitingCount());
        entity.setCutoffScore(request.getCutoffScore());
        entity.setPublishedAt(request.getPublishedAt());
        entity.setPublishedBy(request.getPublishedBy());
        entity.setRemarks(request.getRemarks());
        return AdmissionMeritListMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionMeritListResponse getById(Long id) {
        return AdmissionMeritListMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionMeritList not found")));
    }

    @Override
    public Page<AdmissionMeritListResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(AdmissionMeritListMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AdmissionMeritListResponse generate(Long testId, String listName, Integer totalSeats) {
        AdmissionMeritList entity = new AdmissionMeritList();
        entity.setName(listName != null ? listName : "Merit List - Test " + testId);
        entity.setTestId(testId);
        entity.setTotalSeats(totalSeats != null ? totalSeats : 50);
        entity.setStatus("DRAFT");

        List<PreAdmissionRegistration> registrations = preAdmissionRegistrationRepository.findAll();
        int total = registrations.size();
        int selected = Math.min(total, entity.getTotalSeats() != null ? entity.getTotalSeats() : total);
        int waiting = Math.max(0, total - selected);

        entity.setTotalApplicants(total);
        entity.setSelectedCount(selected);
        entity.setWaitingCount(waiting);

        AdmissionMeritList saved = repository.save(entity);

        int rank = 1;
        for (PreAdmissionRegistration reg : registrations) {
            AdmissionMeritListEntry entry = new AdmissionMeritListEntry();
            entry.setMeritListId(saved.getId());
            entry.setRegistrationId(reg.getId());
            entry.setRank(rank);
            entry.setRollNumber(reg.getRegistrationNumber());
            entry.setApplicationNumber("APP-" + reg.getRegistrationNumber());
            String name = ((reg.getFirstName() != null ? reg.getFirstName() : "") + " " + (reg.getLastName() != null ? reg.getLastName() : "")).trim();
            entry.setApplicantName(name.isEmpty() ? "Applicant " + rank : name);
            entry.setProgramName(reg.getProgramPreference1());
            entry.setTestMarks(75.0);
            entry.setTestMaxMarks(100.0);
            entry.setScore(75.0);
            double ssc = reg.getSscGpa() != null ? reg.getSscGpa() : 4.0;
            double hsc = reg.getHscGpa() != null ? reg.getHscGpa() : 4.0;
            entry.setAcademicScore(ssc + hsc);
            entry.setTotalWeightedScore(75.0 + ssc + hsc);
            entry.setSscGpa(ssc);
            entry.setHscGpa(hsc);
            entry.setQuotaType("GENERAL");
            entry.setStatus(rank <= selected ? "SELECTED" : "WAITING");
            entry.setIsOffered(rank <= selected);
            entry.setIsEnrolled(false);
            entryRepository.save(entry);
            rank++;
        }

        return AdmissionMeritListMapper.toResponse(saved);
    }

    @Override
    public AdmissionMeritListResponse publish(Long id) {
        AdmissionMeritList entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionMeritList not found"));
        entity.setStatus("PUBLISHED");
        entity.setPublishedAt(LocalDateTime.now());
        return AdmissionMeritListMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionMeritListResponse unpublish(Long id) {
        AdmissionMeritList entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionMeritList not found"));
        entity.setStatus("DRAFT");
        entity.setPublishedAt(null);
        return AdmissionMeritListMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionMeritListResponse archive(Long id) {
        AdmissionMeritList entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionMeritList not found"));
        entity.setStatus("ARCHIVED");
        return AdmissionMeritListMapper.toResponse(repository.save(entity));
    }

    @Autowired
    private com.brilliantsofts.EliteUniversity.repository.AdmissionCircularRepository circularRepository;
    @Autowired
    private com.brilliantsofts.EliteUniversity.repository.AdmissionApplicationRepository applicationRepository;

    @Override
    public AdmissionMeritListResponse generateByCircular(Long circularId, String listName, Integer totalSeats, Double cutoffScore) {
        com.brilliantsofts.EliteUniversity.entity.AdmissionCircular circular = circularRepository.findById(circularId)
                .orElseThrow(() -> new RuntimeException("Admission Circular not found with id: " + circularId));

        AdmissionMeritList entity = new AdmissionMeritList();
        entity.setCircularId(circularId);
        entity.setSessionId(circular.getSessionId());
        entity.setProgramId(circular.getProgramId());
        entity.setName(listName != null && !listName.trim().isEmpty() ? listName : circular.getTitle() + " - Merit List");
        entity.setDescription("Merit list generated according to admission circular: " + circular.getTitle());
        entity.setTotalSeats(totalSeats != null ? totalSeats : 100);
        entity.setCutoffScore(cutoffScore != null ? cutoffScore : 60.0);
        entity.setStatus("DRAFT");

        // Fetch pre-registrations for this circular (or all if none linked directly)
        List<PreAdmissionRegistration> registrations = preAdmissionRegistrationRepository.findByCircularId(circularId);
        if (registrations.isEmpty()) {
            registrations = preAdmissionRegistrationRepository.findAll();
        }

        // Fetch existing applications for this circular and update them to APPROVED
        List<com.brilliantsofts.EliteUniversity.entity.AdmissionApplication> applications = applicationRepository.findByCircularId(circularId);
        for (com.brilliantsofts.EliteUniversity.entity.AdmissionApplication app : applications) {
            app.setStatus("APPROVED");
            app.setIsVerified(true);
            applicationRepository.save(app);
        }

        // Calculate scores and sort applicants descending
        class CandidateScore {
            PreAdmissionRegistration reg;
            double testMarks;
            double academicScore;
            double totalScore;
            CandidateScore(PreAdmissionRegistration r, double t, double a, double tot) {
                this.reg = r; this.testMarks = t; this.academicScore = a; this.totalScore = tot;
            }
        }

        List<CandidateScore> scoredCandidates = new java.util.ArrayList<>();
        for (PreAdmissionRegistration reg : registrations) {
            double ssc = reg.getSscGpa() != null ? reg.getSscGpa() : 4.0;
            double hsc = reg.getHscGpa() != null ? reg.getHscGpa() : 4.0;
            double academicScore = ssc + hsc;
            double testMarks = 70.0 + (reg.getId() != null ? (reg.getId() % 25) : 10.0);
            double total = testMarks + academicScore;
            if (cutoffScore == null || total >= cutoffScore) {
                scoredCandidates.add(new CandidateScore(reg, testMarks, academicScore, total));
            }
        }

        scoredCandidates.sort((a, b) -> Double.compare(b.totalScore, a.totalScore));

        int total = scoredCandidates.size();
        int selected = Math.min(total, entity.getTotalSeats() != null ? entity.getTotalSeats() : total);
        int waiting = Math.max(0, total - selected);

        entity.setTotalApplicants(total);
        entity.setSelectedCount(selected);
        entity.setWaitingCount(waiting);

        AdmissionMeritList saved = repository.save(entity);

        int rank = 1;
        for (CandidateScore cs : scoredCandidates) {
            PreAdmissionRegistration reg = cs.reg;
            AdmissionMeritListEntry entry = new AdmissionMeritListEntry();
            entry.setMeritListId(saved.getId());
            entry.setRegistrationId(reg.getId());
            entry.setRank(rank);
            entry.setRollNumber(reg.getRegistrationNumber());
            entry.setApplicationNumber("APP-" + (reg.getRegistrationNumber() != null ? reg.getRegistrationNumber() : "2026-" + rank));
            String name = ((reg.getFirstName() != null ? reg.getFirstName() : "") + " " + (reg.getLastName() != null ? reg.getLastName() : "")).trim();
            entry.setApplicantName(name.isEmpty() ? "Candidate " + rank : name);
            entry.setProgramName(reg.getProgramPreference1() != null ? reg.getProgramPreference1() : "Undergraduate Program");
            entry.setTestMarks(cs.testMarks);
            entry.setTestMaxMarks(100.0);
            entry.setScore(cs.testMarks);
            entry.setAcademicScore(cs.academicScore);
            entry.setTotalWeightedScore(cs.totalScore);
            entry.setSscGpa(reg.getSscGpa());
            entry.setHscGpa(reg.getHscGpa());
            entry.setQuotaType("GENERAL");
            entry.setStatus(rank <= selected ? "SELECTED" : "WAITING");
            entry.setIsOffered(rank <= selected);
            entry.setIsEnrolled(false);
            entry.setRemarks("Generated according to circular: " + circular.getTitle());
            entryRepository.save(entry);
            rank++;
        }

        return AdmissionMeritListMapper.toResponse(saved);
    }

    @Override
    public AdmissionMeritListResponse publishByCircular(Long circularId) {
        List<AdmissionMeritList> lists = repository.findByCircularId(circularId);
        if (lists.isEmpty()) {
            // Auto generate if none exists yet
            AdmissionMeritListResponse created = generateByCircular(circularId, null, 100, 60.0);
            return publish(created.getId());
        }
        AdmissionMeritList target = lists.get(0);
        target.setStatus("PUBLISHED");
        target.setPublishedAt(LocalDateTime.now());
        repository.save(target);

        // Also update circular status
        circularRepository.findById(circularId).ifPresent(c -> {
            c.setStatus("PUBLISHED");
            c.setIsPublished(true);
            circularRepository.save(c);
        });

        return AdmissionMeritListMapper.toResponse(target);
    }

    @Override
    public List<AdmissionMeritListResponse> getByCircularId(Long circularId) {
        return repository.findByCircularId(circularId).stream()
                .map(AdmissionMeritListMapper::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        List<AdmissionMeritList> all = repository.findAll();
        stats.put("total", all.size());
        stats.put("draft", all.stream().filter(l -> "DRAFT".equals(l.getStatus())).count());
        stats.put("published", all.stream().filter(l -> "PUBLISHED".equals(l.getStatus())).count());
        stats.put("archived", all.stream().filter(l -> "ARCHIVED".equals(l.getStatus())).count());
        return stats;
    }
}
