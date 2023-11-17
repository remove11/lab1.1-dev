use KTHDB;

show tables;
select * from patient;
select * from person;
select * from doctor;
select * from other_personal;
select * from message;
select * from encounter;
select * from medicalcondition;
select * from employee;
select * from observation;


select *
from information_schema.table_constraints
where constraint_schema = 'KTHDB'
