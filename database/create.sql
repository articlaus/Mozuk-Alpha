CREATE TABLE OVERTIME_DATES (id bigint(20) NOT NULL , work_date date NOT NULL, start_time datetime NULL, end_time datetime NULL, hours int(11) NOT NULL, isHoliday bit(1) NOT NULL, Overtimeid bigint(20) NOT NULL, PRIMARY KEY (id));
CREATE TABLE EMPLOYEE_WORK_MONTH (id bigint(20) NOT NULL , Work_Monthsid bigint(20) NOT NULL, worked_hours int(10), final_salary int(10) NOT NULL, employee_code varchar(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE WORK_MONTHS (id bigint(20) NOT NULL , year int(10), month int(2), total_work_hours int(10), isLocked bit(1), created_date date, PRIMARY KEY (id));
CREATE TABLE EMPLOYEE_POSITION (id bigint(20) NOT NULL , salary int(10), position_code varchar(6) NOT NULL, employee_code varchar(10) NOT NULL, created_date date, department_code varchar(6) NOT NULL, start_date date NOT NULL, end_date date, isActive bit(1) NOT NULL, PRIMARY KEY (id));
CREATE TABLE DOCUMENT_TYPE (id bigint(20) NOT NULL , name varchar(25) NOT NULL, description text, PRIMARY KEY (id));
CREATE TABLE DOCUMENT (id bigint(20) NOT NULL , foreign_key int(10) NOT NULL, description varchar(250), file_name varchar(50) NOT NULL, file_type varchar(50), document_type_id bigint(20) NOT NULL, created_date date, PRIMARY KEY (id));
CREATE TABLE OVERTIME (id bigint(20) NOT NULL , employee_id varchar(10) NOT NULL, isHoliday bit(1) NOT NULL, reason text, Work_Monthsid bigint(20) NOT NULL, PRIMARY KEY (id));
CREATE TABLE RESOLUTION (code varchar(25) NOT NULL, employee_id varchar(10), resolution_file blob NOT NULL, department_id varchar(6) NOT NULL, created_date date NOT NULL, isDepartment bit(1) DEFAULT true NOT NULL, resolution_type varchar(20) NOT NULL, PRIMARY KEY (code));
CREATE TABLE LEAVE_TYPE (id bigint(20) NOT NULL , leave_type varchar(25) NOT NULL, PRIMARY KEY (id));
CREATE TABLE LEAVE_ABSENCE (id bigint(20) NOT NULL , employee_id varchar(10) NOT NULL, leave_type_id bigint(20) NOT NULL, start_date date NOT NULL, end_date date NOT NULL, number_of_days int(11) NOT NULL, isPaid bit(1) NOT NULL, Work_Monthsid bigint(20) NOT NULL, created_date date NOT NULL, PRIMARY KEY (id));
CREATE TABLE PROBATION (id bigint(20) NOT NULL , employee_code varchar(10) NOT NULL, probation_reason varchar(255) NOT NULL, start_date date NOT NULL, end_date date NOT NULL, isActive bit(1) DEFAULT true NOT NULL, department_id varchar(6) NOT NULL, created_date date NOT NULL, PRIMARY KEY (id));
CREATE TABLE RESUME (id bigint(20) NOT NULL , created_date date NOT NULL, position_code varchar(6) NOT NULL, email varchar(50) NOT NULL, note varchar(255), PRIMARY KEY (id));
CREATE TABLE POSITION (code varchar(6) NOT NULL, position_title varchar(25) NOT NULL, position_description text NOT NULL, created_date date, PRIMARY KEY (code));
CREATE TABLE DEPARTMENT_HEADS (department_code varchar(6) NOT NULL UNIQUE, employee_code varchar(10) NOT NULL UNIQUE, id bigint(20) NOT NULL AUTO_INCREMENT, PRIMARY KEY (id));
CREATE TABLE DEPARTMENT (code varchar(6) NOT NULL, department_title varchar(25) NOT NULL, department_description text, created_date date, PRIMARY KEY (code));
CREATE TABLE USER_ROLES (id bigint(20) NOT NULL AUTO_INCREMENT, user_role varchar(25) NOT NULL, PRIMARY KEY (id));
CREATE TABLE USERS (username varchar(50) NOT NULL, password varchar(100) NOT NULL, employee_code varchar(10) NOT NULL, user_role_id bigint(20) NOT NULL, isLocked bit(1) NOT NULL, created_date date, PRIMARY KEY (username));
CREATE TABLE EMERGENCY (id bigint(20) NOT NULL AUTO_INCREMENT, cell_number varchar(20) NOT NULL, name varchar(50) NOT NULL, relationship varchar(25) NOT NULL, employee_code varchar(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE EMPLOYEE (code varchar(10) NOT NULL, portrait blob NOT NULL, firstname varchar(50) NOT NULL, lastname varchar(50) NOT NULL, surname varchar(50) NOT NULL, cell_number varchar(20) NOT NULL, address text, gender bit(1), dob date NOT NULL, marital_status varchar(25) NOT NULL, family_size int(11), social_security_number varchar(15) NOT NULL, created_date date, PRIMARY KEY (code));
ALTER TABLE EMPLOYEE_POSITION ADD INDEX FKEMPLOYEE_P816118 (department_code), ADD CONSTRAINT FKEMPLOYEE_P816118 FOREIGN KEY (department_code) REFERENCES DEPARTMENT (code);
ALTER TABLE OVERTIME_DATES ADD INDEX FKOVERTIME_D364655 (Overtimeid), ADD CONSTRAINT FKOVERTIME_D364655 FOREIGN KEY (Overtimeid) REFERENCES OVERTIME (id);
ALTER TABLE EMPLOYEE_WORK_MONTH ADD INDEX FKEMPLOYEE_W373980 (Work_Monthsid), ADD CONSTRAINT FKEMPLOYEE_W373980 FOREIGN KEY (Work_Monthsid) REFERENCES WORK_MONTHS (id);
ALTER TABLE EMPLOYEE_WORK_MONTH ADD INDEX FKEMPLOYEE_W256139 (employee_code), ADD CONSTRAINT FKEMPLOYEE_W256139 FOREIGN KEY (employee_code) REFERENCES EMPLOYEE (code);
ALTER TABLE OVERTIME ADD INDEX FKOVERTIME825399 (Work_Monthsid), ADD CONSTRAINT FKOVERTIME825399 FOREIGN KEY (Work_Monthsid) REFERENCES WORK_MONTHS (id);
ALTER TABLE LEAVE_ABSENCE ADD INDEX FKLEAVE_ABSE467073 (Work_Monthsid), ADD CONSTRAINT FKLEAVE_ABSE467073 FOREIGN KEY (Work_Monthsid) REFERENCES WORK_MONTHS (id);
ALTER TABLE EMPLOYEE_POSITION ADD INDEX FKEMPLOYEE_P560577 (position_code), ADD CONSTRAINT FKEMPLOYEE_P560577 FOREIGN KEY (position_code) REFERENCES POSITION (code);
ALTER TABLE EMPLOYEE_POSITION ADD INDEX FKEMPLOYEE_P487052 (employee_code), ADD CONSTRAINT FKEMPLOYEE_P487052 FOREIGN KEY (employee_code) REFERENCES EMPLOYEE (code);
ALTER TABLE EMERGENCY ADD INDEX FKEMERGENCY899504 (employee_code), ADD CONSTRAINT FKEMERGENCY899504 FOREIGN KEY (employee_code) REFERENCES EMPLOYEE (code);
ALTER TABLE USERS ADD INDEX FKUSERS299456 (employee_code), ADD CONSTRAINT FKUSERS299456 FOREIGN KEY (employee_code) REFERENCES EMPLOYEE (code);
ALTER TABLE USERS ADD INDEX FKUSERS584035 (user_role_id), ADD CONSTRAINT FKUSERS584035 FOREIGN KEY (user_role_id) REFERENCES USER_ROLES (id);
ALTER TABLE DEPARTMENT_HEADS ADD INDEX FKDEPARTMENT235193 (department_code), ADD CONSTRAINT FKDEPARTMENT235193 FOREIGN KEY (department_code) REFERENCES DEPARTMENT (code);
ALTER TABLE DEPARTMENT_HEADS ADD INDEX FKDEPARTMENT433226 (employee_code), ADD CONSTRAINT FKDEPARTMENT433226 FOREIGN KEY (employee_code) REFERENCES EMPLOYEE (code);
ALTER TABLE RESUME ADD INDEX FKRESUME83412 (position_code), ADD CONSTRAINT FKRESUME83412 FOREIGN KEY (position_code) REFERENCES POSITION (code);
ALTER TABLE PROBATION ADD INDEX FKPROBATION792610 (employee_code), ADD CONSTRAINT FKPROBATION792610 FOREIGN KEY (employee_code) REFERENCES EMPLOYEE (code);
ALTER TABLE PROBATION ADD INDEX FKPROBATION713738 (department_id), ADD CONSTRAINT FKPROBATION713738 FOREIGN KEY (department_id) REFERENCES DEPARTMENT (code);
ALTER TABLE LEAVE_ABSENCE ADD INDEX FKLEAVE_ABSE331786 (employee_id), ADD CONSTRAINT FKLEAVE_ABSE331786 FOREIGN KEY (employee_id) REFERENCES EMPLOYEE (code);
ALTER TABLE LEAVE_ABSENCE ADD INDEX FKLEAVE_ABSE693795 (leave_type_id), ADD CONSTRAINT FKLEAVE_ABSE693795 FOREIGN KEY (leave_type_id) REFERENCES LEAVE_TYPE (id);
ALTER TABLE RESOLUTION ADD INDEX FKRESOLUTION308229 (employee_id), ADD CONSTRAINT FKRESOLUTION308229 FOREIGN KEY (employee_id) REFERENCES EMPLOYEE (code);
ALTER TABLE RESOLUTION ADD INDEX FKRESOLUTION784433 (department_id), ADD CONSTRAINT FKRESOLUTION784433 FOREIGN KEY (department_id) REFERENCES DEPARTMENT (code);
ALTER TABLE OVERTIME ADD INDEX FKOVERTIME39313 (employee_id), ADD CONSTRAINT FKOVERTIME39313 FOREIGN KEY (employee_id) REFERENCES EMPLOYEE (code);
ALTER TABLE DOCUMENT ADD INDEX FKDOCUMENT212789 (document_type_id), ADD CONSTRAINT FKDOCUMENT212789 FOREIGN KEY (document_type_id) REFERENCES DOCUMENT_TYPE (id);
