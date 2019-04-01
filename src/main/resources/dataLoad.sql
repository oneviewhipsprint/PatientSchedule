insert into patient (patientid,firstname,lastname) values (1, 'Gerry', 'Garcia');
insert into patient (patientid,firstname,lastname) values (2, 'Jim', 'Morrison');
insert into patient (patientid,firstname,lastname) values (3, 'Janis', 'Joplin');
insert into patient (patientid,firstname,lastname) values (4, 'Jimi', 'Hendrix');
insert into patient (patientid,firstname,lastname) values (5, 'Kurt', 'Cobain');
insert into patient (patientid,firstname,lastname) values (6, 'John', 'Lennon');
insert into patient (patientid,firstname,lastname) values (7, 'Freddy', 'Mercury');
insert into patient (patientid,firstname,lastname) values (8, 'Bob', 'Marley');
insert into patient (patientid,firstname,lastname) values (9, 'Stevie Ray', 'Vaughn');


insert into clinic(clinicid,clinicName values(1, 'DenverWest');
insert into chair (chairid, chairName) values(1, 'upstairs');
insert into chair (chairid, chairName) values(2, 'downstairs');
insert into chair (chairid, chairName) values(3, 'window seat');
insert into shift(shiftId,shiftName,shiftDate,shiftTime) values(1, 'Morning', '04032019', '8am-12pm');
insert into shift(shiftId,shiftName,shiftDate,shiftTime) values(2, 'Afternoon', '04032019', '12pm-4pm');
insert into shift(shiftId,shiftName,shiftDate,shiftTime) values(3, 'Morning', '04032019', '8am-12pm');

insert into clinic(clinicid,clinicName,shiftId,shiftName,shiftDate,shiftTime)
    values(1, 'DenverWest', 3, 1, 'Morning', '04032019', '8am-12pm');
insert into clinic(clinicid,clinicName,chairid,shiftId,shiftName,shiftDate,shiftTime)
    values(1, 'DenverWest', 1, 2, 'Afternoon', '04032019', '12pm-4pm');
insert into clinic(clinicid,clinicName,shiftId,shiftName,shiftDate,shiftTime)
    values(1, 'DenverWest', 2, 2, 'Afternoon', '04032019', '12pm-4pm');
insert into clinic(clinicid,clinicName,shiftId,shiftName,shiftDate,shiftTime)
    values(1, 'DenverWest', 3, 2, 'Afternoon', '04032019', '12pm-4pm');
insert into clinic(clinicid,clinicName,chairid,shiftId,shiftName,shiftDate,shiftTime)
    values(1, 'DenverWest', 1, 3, 'Evening', '04032019', '4pm-8pm');
insert into clinic(clinicid,clinicName,shiftId,shiftName,shiftDate,shiftTime)
    values(1, 'DenverWest', 2, 3, 'Evening', '04032019', '4pm-8pm');
insert into clinic(clinicid,clinicName,shiftId,shiftName,shiftDate,shiftTime)
    values(1, 'DenverWest', 3, 3, 'Evening', '04032019', '4pm-8pm');
insert into schedule(scheduleid, patientId,clinicId,chairId;shiftId) values(1, 1, 1, 1, 1);
insert into schedule(scheduleid, patientId,clinicId,chairId;shiftId) values(2, 2, 1, 2, 1);
insert into schedule(scheduleid, patientId,clinicId,chairId;shiftId) values(3, 3, 1, 3, 1);
insert into schedule(scheduleid, patientId,clinicId,chairId;shiftId) values(4, 4, 1, 1, 2);
insert into schedule(scheduleid, patientId,clinicId,chairId;shiftId) values(5, 5, 1, 2, 2);
insert into schedule(scheduleid, patientId,clinicId,chairId;shiftId) values(6, 6, 1, 3, 2);
insert into schedule(scheduleid, patientId,clinicId,chairId;shiftId) values(7, 7, 1, 1, 3);
insert into schedule(scheduleid, patientId,clinicId,chairId;shiftId) values(8, 8, 1, 2, 3);
insert into schedule(scheduleid, patientId,clinicId,chairId;shiftId) values(9, 9, 1, 3, 3);
