-- Seed Academic Session
INSERT INTO academic_sessions (id, session_name, start_date, end_date, active)
VALUES (1, '2025-2026', '2025-01-01', '2026-12-31', 1)
ON DUPLICATE KEY UPDATE session_name='2025-2026', active=1;

-- Seed Faculty, Department, Program if needed
INSERT IGNORE INTO faculties (id, name, description)
VALUES (1, 'Faculty of Science & Engineering', 'FSE');

INSERT IGNORE INTO departments (id, name, description, faculty_id)
VALUES (1, 'Computer Science & Engineering', 'CSE Department', 1);

INSERT IGNORE INTO programs (id, name, program_code, total_semesters, total_credits, department_id)
VALUES (1, 'B.Sc. in Computer Science & Engineering', 'BSC-CSE', 8, 148, 1);

-- Seed Students
INSERT INTO students (id, student_id, full_name, user_id, program_id, academic_session_id, admission_date)
VALUES (1, 'STU-2025-001', 'Test Student A', 6, 1, 1, '2025-01-15'),
       (2, 'STU-2025-002', 'Test Student B', 9, 1, 1, '2025-01-15')
ON DUPLICATE KEY UPDATE full_name=VALUES(full_name), program_id=1, academic_session_id=1;

-- Seed Courses
INSERT INTO courses (id, course_name, course_code, credit, description, department_id, program_id)
VALUES (901, 'Data Structures and Algorithms', 'CSE 225', 3, 'Core course', 1, 1),
       (902, 'Digital Logic Design', 'CSE 231', 3, 'Core course', 1, 1),
       (903, 'Object Oriented Programming', 'CSE 241', 3, 'Core course', 1, 1)
ON DUPLICATE KEY UPDATE course_name=VALUES(course_name), course_code=VALUES(course_code), credit=3;

-- Seed Grade Rules
INSERT INTO grade_rules (id, unique_code, grade, min_percentage, max_percentage, grade_point, course_id, created_at, updated_at)
VALUES 
(9011, 'GR-901-A', 'A', 80, 100, 4.0, 901, NOW(), NOW()),
(9012, 'GR-901-A-', 'A-', 75, 79, 3.7, 901, NOW(), NOW()),
(9013, 'GR-901-B', 'B', 70, 74, 3.3, 901, NOW(), NOW()),
(9014, 'GR-901-B-', 'B-', 65, 69, 3.0, 901, NOW(), NOW()),
(9015, 'GR-901-C', 'C', 60, 64, 2.0, 901, NOW(), NOW()),
(9016, 'GR-901-D', 'D', 50, 59, 1.0, 901, NOW(), NOW()),
(9017, 'GR-901-F', 'F', 0, 49, 0.0, 901, NOW(), NOW()),
(9021, 'GR-902-A', 'A', 80, 100, 4.0, 902, NOW(), NOW()),
(9022, 'GR-902-A-', 'A-', 75, 79, 3.7, 902, NOW(), NOW()),
(9023, 'GR-902-B', 'B', 70, 74, 3.3, 902, NOW(), NOW()),
(9024, 'GR-902-B-', 'B-', 65, 69, 3.0, 902, NOW(), NOW()),
(9025, 'GR-902-C', 'C', 60, 64, 2.0, 902, NOW(), NOW()),
(9026, 'GR-902-D', 'D', 50, 59, 1.0, 902, NOW(), NOW()),
(9027, 'GR-902-F', 'F', 0, 49, 0.0, 902, NOW(), NOW()),
(9031, 'GR-903-A', 'A', 80, 100, 4.0, 903, NOW(), NOW()),
(9032, 'GR-903-A-', 'A-', 75, 79, 3.7, 903, NOW(), NOW()),
(9033, 'GR-903-B', 'B', 70, 74, 3.3, 903, NOW(), NOW()),
(9034, 'GR-903-B-', 'B-', 65, 69, 3.0, 903, NOW(), NOW()),
(9035, 'GR-903-C', 'C', 60, 64, 2.0, 903, NOW(), NOW()),
(9036, 'GR-903-D', 'D', 50, 59, 1.0, 903, NOW(), NOW()),
(9037, 'GR-903-F', 'F', 0, 49, 0.0, 903, NOW(), NOW())
ON DUPLICATE KEY UPDATE grade=VALUES(grade), grade_point=VALUES(grade_point);

-- Seed Examinations
INSERT INTO examinations (id, examination_name, semester, total_marks, pass_marks, course_id)
VALUES (901, 'CSE225 Final Spring', 'Spring 2025', 100, 40, 901),
       (902, 'CSE231 Final Spring', 'Spring 2025', 100, 40, 902),
       (903, 'CSE241 Final Spring', 'Spring 2025', 100, 40, 903),
       (9021, 'CSE225 Final Fall', 'Fall 2025', 100, 40, 901),
       (9022, 'CSE231 Final Fall', 'Fall 2025', 100, 40, 902),
       (9023, 'CSE241 Final Fall', 'Fall 2025', 100, 40, 903)
ON DUPLICATE KEY UPDATE examination_name=VALUES(examination_name), semester=VALUES(semester), total_marks=100;

-- Clean existing results for Student 1 & 2
DELETE FROM examination_results WHERE student_id IN (1, 2);

-- Examination results: Student A (id=1, GPA 3.90 Spring / 3.00 Fall, CGPA 3.45)
INSERT INTO examination_results (id, marks, grade, grade_point, credit, student_id, examination_id)
VALUES (6001, 85, 'A', 4.0, 3.0, 1, 901),
       (6002, 78, 'A-', 3.7, 3.0, 1, 902),
       (6003, 92, 'A', 4.0, 3.0, 1, 903),
       (6004, 88, 'A', 4.0, 3.0, 1, 9021),
       (6005, 65, 'B-', 3.0, 3.0, 1, 9022),
       (6006, 55, 'D', 1.0, 3.0, 1, 9023);

-- Examination results: Student B (id=2, GPA 2.10 Spring / 2.33 Fall, CGPA 2.22)
INSERT INTO examination_results (id, marks, grade, grade_point, credit, student_id, examination_id)
VALUES (6007, 60, 'C', 2.0, 3.0, 2, 901),
       (6008, 50, 'D', 1.0, 3.0, 2, 902),
       (6009, 72, 'B', 3.3, 3.0, 2, 903),
       (6010, 80, 'A', 4.0, 3.0, 2, 9021),
       (6011, 45, 'F', 0.0, 3.0, 2, 9022),
       (6012, 67, 'B-', 3.0, 3.0, 2, 9023);
