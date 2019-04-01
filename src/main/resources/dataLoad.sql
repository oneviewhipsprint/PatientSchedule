insert into patient (patientid,username,password, firstname,lastname) values (1, 'gerrygarcia','davita', 'Gerry', 'Garcia');
insert into patient (patientid,username,password,firstname,lastname) values (2, 'jimmorrison','davita','Jim', 'Morrison');
insert into patient (patientid,username,password,firstname,lastname) values (3, 'janisjoplin', 'davita', 'Janis', 'Joplin');
insert into patient (patientid,username,password,firstname,lastname) values (4, 'jimihendrix', 'davita', 'Jimi', 'Hendrix');
insert into patient (patientid,username,password,firstname,lastname) values (5, 'kurtcobain','davita', 'Kurt', 'Cobain');
insert into patient (patientid,username,password,firstname,lastname) values (6, 'johnlennon', 'davita',  'John', 'Lennon');
insert into patient (patientid,username,password,firstname,lastname) values (7, 'freddymercury','davita', 'Freddy', 'Mercury');
insert into patient (patientid,username,password,firstname,lastname) values (8, 'bobmarley','davita', 'Bob', 'Marley');
insert into patient (patientid,username,password,firstname,lastname) values (9, 'stevierayvaughn','davita', 'Stevie Ray', 'Vaughn');


insert into clinic(clinicid,clinicName) values(1, 'DenverWest');
insert into chair (chairid, chairName) values(1, 'upstairs');
insert into chair (chairid, chairName) values(2, 'downstairs');
insert into chair (chairid, chairName) values(3, 'window seat');
insert into shift(shiftId,shiftName,shiftTime) values(1, 'Morning', '8am-12pm');
insert into shift(shiftId,shiftName,shiftTime) values(2, 'Afternoon', '12pm-4pm');
insert into shift(shiftId,shiftName,shiftTime) values(3, 'Evening', '4pm-8pm');

insert into chairshift(chairshiftid, chairid,shiftid) values (1, 1, 1);
insert into chairshift(chairshiftid, chairid,shiftid) values (2, 1, 2);
insert into chairshift(chairshiftid, chairid,shiftid) values (3, 1, 3);
insert into chairshift(chairshiftid, chairid,shiftid) values (4, 2, 1);
insert into chairshift(chairshiftid, chairid,shiftid) values (5, 2, 2);
insert into chairshift(chairshiftid, chairid,shiftid) values (6, 2, 3);
insert into chairshift(chairshiftid, chairid,shiftid) values (7, 3, 1);
insert into chairshift(chairshiftid, chairid,shiftid) values (8, 3, 2);
insert into chairshift(chairshiftid, chairid,shiftid) values (9, 3, 3);

insert into clinicchair(clinicchairid, clinicid, chairid) values (1, 1, 1);
insert into clinicchair(clinicchairid, clinicid, chairid) values (2, 1, 2);
insert into clinicchair(clinicchairid, clinicid, chairid) values (3, 1, 3);

insert into schedule(scheduleid, patientId,clinicId,chairId,shiftId,shiftDate) values(1, 1, 1, 1, 1, '04032019');
insert into schedule(scheduleid, patientId,clinicId,chairId,shiftId,shiftDate) values(2, 2, 1, 2, 1, '04032019');
insert into schedule(scheduleid, patientId,clinicId,chairId,shiftId,shiftDate) values(3, 3, 1, 3, 1, '04032019');
insert into schedule(scheduleid, patientId,clinicId,chairId,shiftId,shiftDate) values(4, 4, 1, 1, 2, '04032019');
insert into schedule(scheduleid, patientId,clinicId,chairId,shiftId,shiftDate) values(5, 5, 1, 2, 2, '04032019');
insert into schedule(scheduleid, patientId,clinicId,chairId,shiftId,shiftDate) values(6, 6, 1, 3, 2, '04032019');
insert into schedule(scheduleid, patientId,clinicId,chairId,shiftId,shiftDate) values(7, 7, 1, 1, 3, '04032019');
insert into schedule(scheduleid, patientId,clinicId,chairId,shiftId,shiftDate) values(8, 8, 1, 2, 3, '04032019');
insert into schedule(scheduleid, patientId,clinicId,chairId,shiftId,shiftDate) values(9, 9, 1, 3, 3, '04032019');
