-- Seed / Ensure Merit List 1 exists
INSERT INTO admission_merit_lists (id, name, description, academic_year, status, total_seats, total_applicants, selected_count, waiting_count, cutoff_score, published_at, created_at, updated_at)
VALUES (1, 'Merit List 1 - Spring 2026', 'Official First Merit List for Spring 2026 Undergraduate Admissions', '2026', 'PUBLISHED', 100, 3, 2, 1, 70.0, NOW(), NOW(), NOW())
ON DUPLICATE KEY UPDATE 
  name='Merit List 1 - Spring 2026',
  status='PUBLISHED',
  total_seats=100,
  total_applicants=3,
  selected_count=2,
  waiting_count=1,
  cutoff_score=70.0;

-- Delete old entries if any for clean seeding
DELETE FROM admission_merit_list_entries WHERE merit_list_id = 1;

-- Seed Merit List Entries for List 1
INSERT INTO admission_merit_list_entries 
(id, merit_list_id, registration_id, `rank`, roll_number, application_number, applicant_name, faculty_name, department_name, program_name, shift, test_marks, test_max_marks, score, academic_score, total_weighted_score, ssc_gpa, hsc_gpa, quota_type, status, is_offered, is_enrolled, remarks, created_at, updated_at)
VALUES
(1, 1, 1, 1, 'REG-67C93EB1', 'APP-2026-001', 'Tanvir Ahmed', 'Faculty of Science & Engineering', 'Computer Science & Engineering', 'B.Sc. in Computer Science & Engineering', 'DAY', 85.0, 100.0, 85.0, 9.8, 92.5, 5.0, 4.8, 'GENERAL', 'SELECTED', 1, 0, 'First Merit List - Selected', NOW(), NOW()),
(2, 1, 2, 2, 'REG-2026-002', 'APP-2026-002', 'Tariqul Islam', 'Faculty of Science & Engineering', 'Electrical & Electronic Engineering', 'B.Sc. in Electrical & Electronic Engineering', 'DAY', 78.0, 100.0, 78.0, 9.7, 86.0, 4.8, 4.9, 'GENERAL', 'SELECTED', 1, 0, 'First Merit List - Selected', NOW(), NOW()),
(3, 1, 3, 3, 'REG-2026-003', 'APP-2026-003', 'Nusrat Jahan', 'Faculty of Business Administration', 'Business Administration', 'Bachelor of Business Administration (BBA)', 'DAY', 68.0, 100.0, 68.0, 9.7, 77.7, 5.0, 4.7, 'GENERAL', 'WAITING', 0, 0, 'Waiting List 1', NOW(), NOW());
