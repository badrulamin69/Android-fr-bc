-- Academic session
INSERT INTO academic_sessions (id, session_name, start_date, end_date, active)
VALUES (1, '2025-2026', '2025-01-01', '2026-12-31', 1);

-- Second student user (reuse teacher bcrypt hash => password Password@123)
INSERT INTO users (id, username, email, password, phone, enabled, account_non_locked, account_non_expired, credentials_non_expired, email_verified, failed_login_attempts, created_at, updated_at, role)
VALUES (9, 'student.b', 'studentb@eliteuniversity.edu', '$2a$10$IHu2KpETSIKW.OTgb6TofOrvNB8DXX9J3oUgH4ppW.67Vwa29i/gG', '+8801700000009', 1, 1, 1, 1, 0, 0, NOW(), NOW(), 'STUDENT');

-- Ensure existing student user (id 6) has known password + enabled
UPDATE users SET password='$2a$10$IHu2KpETSIKW.OTgb6TofOrvNB8DXX9J3oUgH4ppW.67Vwa29i/gG', enabled=1 WHERE id=6;

-- Student profiles
INSERT INTO students (student_id, full_name, user_id, program_id, academic_session_id)
VALUES ('STU-2025-001','Test Student A',6,1,1),
       ('STU-2025-002','Test Student B',9,1,1);

-- Courses (CSE program)
INSERT INTO courses (id, course_name, course_code, credit, description, department_id, program_id)
VALUES (901,'Data Structures and Algorithms','CSE 225',3,'',1,1),
       (902,'Digital Logic Design','CSE 231',3,'',1,1),
       (903,'Object Oriented Programming','CSE 241',3,'',1,1);

-- Examinations (two semesters)
INSERT INTO examinations (id, examination_name, semester, total_marks, pass_marks, course_id)
VALUES (901,'CSE225 Final Spring','Spring 2025',100,40,901),
       (902,'CSE231 Final Spring','Spring 2025',100,40,902),
       (903,'CSE241 Final Spring','Spring 2025',100,40,903),
       (9021,'CSE225 Final Fall','Fall 2025',100,40,901),
       (9022,'CSE231 Final Fall','Fall 2025',100,40,902),
       (9023,'CSE241 Final Fall','Fall 2025',100,40,903);

-- Grade rules (per course) for real grade-point derivation
INSERT INTO grade_rules (grade, min_percentage, max_percentage, grade_point, course_id, description)
VALUES
('A',80,100,4.0,901,''),('A-',75,79,3.7,901,''),('B',70,74,3.3,901,''),('B-',65,69,3.0,901,''),('C',60,64,2.0,901,''),('D',50,59,1.0,901,''),('F',0,49,0.0,901,''),
('A',80,100,4.0,902,''),('A-',75,79,3.7,902,''),('B',70,74,3.3,902,''),('B-',65,69,3.0,902,''),('C',60,64,2.0,902,''),('D',50,59,1.0,902,''),('F',0,49,0.0,902,''),
('A',80,100,4.0,903,''),('A-',75,79,3.7,903,''),('B',70,74,3.3,903,''),('B-',65,69,3.0,903,''),('C',60,64,2.0,903,''),('D',50,59,1.0,903,''),('F',0,49,0.0,903,'');

-- Examination results: Student A (gradePoint stored explicitly)
INSERT INTO examination_results (id, marks, grade, grade_point, credit, student_id, examination_id)
VALUES (6001,85,'A',4.0,3.0,6,901),
       (6002,78,'A-',3.7,3.0,6,902),
       (6003,92,'A',4.0,3.0,6,903),
       (6004,88,'A',4.0,3.0,6,9021),
       (6005,65,'B',3.0,3.0,6,9022),
       (6006,55,'C',2.0,3.0,6,9023);

-- Examination results: Student B (gradePoint NULL -> derived from GradeRule via marks)
INSERT INTO examination_results (id, marks, grade, grade_point, credit, student_id, examination_id)
VALUES (6007,60,NULL,NULL,3.0,9,901),
       (6008,50,NULL,NULL,3.0,9,902),
       (6009,72,NULL,NULL,3.0,9,903),
       (6010,80,NULL,NULL,3.0,9,9021),
       (6011,45,NULL,NULL,3.0,9,9022),
       (6012,67,NULL,NULL,3.0,9,9023);
