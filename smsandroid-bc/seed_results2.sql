-- Examination results: Student A (id=1, gradePoint stored)
INSERT INTO examination_results (id, marks, grade, grade_point, credit, student_id, examination_id)
VALUES (6001,85,'A',4.0,3.0,1,901),
       (6002,78,'A-',3.7,3.0,1,902),
       (6003,92,'A',4.0,3.0,1,903),
       (6004,88,'A',4.0,3.0,1,9021),
       (6005,65,'B',3.0,3.0,1,9022),
       (6006,55,'C',2.0,3.0,1,9023);

-- Examination results: Student B (id=2, gradePoint NULL -> derived)
INSERT INTO examination_results (id, marks, grade, grade_point, credit, student_id, examination_id)
VALUES (6007,60,NULL,NULL,3.0,2,901),
       (6008,50,NULL,NULL,3.0,2,902),
       (6009,72,NULL,NULL,3.0,2,903),
       (6010,80,NULL,NULL,3.0,2,9021),
       (6011,45,NULL,NULL,3.0,2,9022),
       (6012,67,NULL,NULL,3.0,2,9023);
