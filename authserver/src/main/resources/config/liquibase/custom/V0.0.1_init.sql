--Application
--His
INSERT INTO application ( id, name, url, active, created_date, created_by, last_modified, modified_by) VALUES ( 1,'His','his/','Y', current_timestamp, 1, current_timestamp, 1);
--Pharmacy
INSERT INTO application ( id, name, url, active, created_date, created_by, last_modified, modified_by) VALUES ( 2,'Pharmacy','pharmacy/','Y', current_timestamp, 1, current_timestamp, 1);

--Role
-- His
INSERT INTO role (id, name, ldap_id, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (1, 'Admin', 'cn=Admin,ou=users', 'Y', current_timestamp, 1, current_timestamp , 1, 1);
--Pharmacy
INSERT INTO role (id, name, ldap_id, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (2, 'Pharmacy_Admin', 'cn=Pharmacy_Admin,ou=users', 'Y', NULL, 1, NULL , 1, 2);

--Resource
--His
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (1, 'View Address', 'master/viewAddress/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (2, 'Create/Edit Address', 'master/saveOrUpdateAddress/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (3, 'Delete Address', 'master/deleteAddress/', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (4, 'View Country', 'master/viewCountry/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (5, 'Create/Edit Country', 'master/saveOrUpdateCountry/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (6, 'Delete Country', 'master/deleteCountry/', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (7, 'View Hospital', 'master/viewHospital/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (8, 'Create/Edit Hospital', 'master/saveOrUpdateHospital/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (9, 'Delete Hospital', 'master/deleteHospital/', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (10, 'View Insurance', 'master/viewInsurance/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (11, 'Create/Edit Insurance', 'master/saveOrUpdateInsurance/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (12, 'Delete Insurance', 'master/deleteInsurance/', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (13, 'View NokRelationship', 'master/viewNokRelationship/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (14, 'Create/Edit NokRelationship', 'master/saveOrUpdateNokRelationship/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (15, 'Delete NokRelationship', 'master/deleteNokRelationship/', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);


INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (16, 'Create/Edit Patient Registration', 'his/saveOrUpdateRegistration/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (17, 'View Patient Explorer', 'his/viewPatientExplorer/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (18, 'Create/Reschedule Appointment', 'his/saveOrUpdateAppoinment/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (19, 'Block/Unblock Appointment', 'his/blockOrUnblockAppointment/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (20, 'View Appointment History', 'his/viewAppointmentHistory/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (21, 'View Appointment Block History', 'his/viewAppointmentBlockHistory/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Doctor
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (22, 'Create/Edit Doctor', 'his/saveOrUpdateDoctor/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (23, 'View Doctor', 'his/viewDoctor/', 'READ_ONLY','Y', current_timestamp, 1, current_timestamp, 1, 1);

--Doctor schedule
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (24, 'Doctor Schedule/Reschedule', 'his/saveOrUpdateDoctorSchedule/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (25, 'View Doctor Schedule', 'his/viewDoctorSchedule/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Services & Charges
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (26, 'Create/Edit Service & Charges', 'his/saveOrUpdateServiceCharges/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (27, 'View Service & Charges', 'his/viewServicesCharges/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (28, 'Delete Service & Charges', 'his/deleteServiceCharges/', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Speciality
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (29, 'Create/Edit Speciality', 'his/saveOrUpdateSpeciality/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (30, 'View Speciality', 'his/viewSpeciality/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (31, 'Delete Speciality', 'his/deleteSpeciality/', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Sub Speciality
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (32, 'Create/Edit Sub Speciality', 'his/saveOrUpdateSubSpeciality/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (33, 'View Sub Speciality', 'his/viewSubSpeciality/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (34, 'Delete Sub Speciality', 'his/deleteSubSpeciality/', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Units
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(35, 'Create/Edit Units', 'his/saveOrUpdateUnits', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(36, 'View Units', 'his/viewUnits', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(37, 'Delete Units', 'his/deleteUnits', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Service Categories
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(38, 'Create/Edit Service Categories', 'his/saveOrUpdateServiceCategories', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(39, 'View Service Categories', 'his/viewServiceCategories', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1 );
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(40, 'Delete Service Categories', 'his/deleteServiceCategories', 'FULL', 'Y',current_timestamp, 1, current_timestamp, 1, 1);

--Specialisation
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(41, 'Create/Edit Specialisation', 'his/saveOrUpdateSpecialisation', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(42, 'View Specialisation', 'his/saveOrUpdateSpecialisation', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(43, 'Delete Specialisation', 'his/deleteSpecialisation', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Insurance
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(44, 'Create/Edit Insurance', 'his/saveOrUpdateInsurance', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(45, 'View Insurance Insurance','his/saveOrUpdateInsurance','READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Corporate
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(46, 'Create/Edit Corporate', 'his/saveOrUpdateCorporate', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(47, 'View Corporate', 'his/viewCorporate', 'READ_ONLY','Y', current_timestamp, 1, current_timestamp, 1, 1);

--Doctor Affiliation
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(48, 'Create/Edit Doctor Affiliation', 'his/saveOrUpdateDoctorAffiliation', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(49, 'View Doctor Affiliation', 'his/viewDoctorAffiliation', 'READ_ONLY','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(50, 'Delete Doctor Affiliation', 'his/deleteDoctorAffiliation', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--BloodGroup
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(51, 'Create/Edit Blood Group', 'his/saveOrUpdateBloodGroup', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(52, 'View Blood Group', 'his/viewBloodGroup', 'READ_ONLY','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(53, 'Delete Blood Group', 'his/deleteBloodGroup', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Languages
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(54, 'Create/Edit Languages', 'his/saveOrUpdateLanguages', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(55, 'View Languages', 'his/viewLanguages', 'READ_ONLY','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(56, 'Delete Languages', 'his/deleteLanguages', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Marital Status
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(57, 'Create/Edit Marital Status', 'his/saveOrUpdateMaritalStatus', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(58, 'View Marital Status', 'his/viewMaritalStatus', 'READ_ONLY','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(59, 'Delete Marital Status', 'his/deleteMaritalStatus', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--RelationShip
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(60, 'Create/Edit RelationShip', 'his/saveOrUpdateRelationShip', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(61, 'View RelationShip', 'his/viewRelationShip', 'READ_ONLY','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(62, 'Delete RelationShip', 'his/deleteRelationShip', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Salutation
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(63, 'Create/Edit Salutation', 'his/saveOrUpdateSalutation', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(64, 'View Salutation', 'his/viewSalutation', 'READ_ONLY','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(65, 'Delete Salutation', 'his/deleteSalutation', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Price Book
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(66, 'Create/Edit Price Book', 'his/saveOrUpdatePriceBook', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(67, 'View Price Book', 'his/viewPriceBook', 'READ_ONLY','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(68, 'Delete Price Book', 'his/deletePriceBook', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Visit Purpose
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(69, 'Create/Edit Visit Purpose', 'his/saveOrUpdateVisitPurpose', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(70, 'View Visit Purpose', 'his/viewVisitPurpose', 'READ_ONLY','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(71, 'Delete Visit Purpose', 'his/deleteVisitPurpose', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--ReferralType
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(72, 'Create/Edit Referral Type', 'his/saveOrUpdateReferralType', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(73, 'View Referral Type', 'his/viewReferralType', 'READ_ONLY','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(74, 'Delete Referral Type', 'his/deleteReferralType', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Events
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(75, 'Create/Edit Events', 'his/saveOrUpdateEvents', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(76, 'View Events', 'his/viewEvents', 'READ_ONLY','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(77, 'Delete Events', 'his/deleteEvents', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Billing
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(78, 'Create New Bill', 'his/saveOrUpdateBill', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(79, 'Edit Bill', 'his/editBill', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(80, 'Cancel Bill', 'his/cancelBill', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(81, 'Collect Payment(POS)', 'his/billPaymentAtPos', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(82, 'Enter Additional Discount', 'his/enterAdditionalDiscount', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(83, 'View Bill History ', 'his/viewBillHistory', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Receipt
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(84, 'Create New Receipt', 'his/saveOrUpdateReceipt', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(85, 'Cancel Receipt', 'his/cancelReceipt', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(86, 'View Receipt History ', 'his/viewReceiptHistory', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Credit Note
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(87, 'Create New Credit Note', 'his/saveOrUpdateCreditNote', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(88, 'Cancel Credit Note', 'his/cancelCreditNote', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(89, 'View Credit Note History ', 'his/viewCreditNoteHistory', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Cash Counter
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(90, 'Collect Payment (Cash)', 'his/cashCounterCollectPayment', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(91, 'Make Payment', 'his/cashCounterMakePayment', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(92, 'View Receipts ', 'his/viewReceipts', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Cash Register
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(93, 'Cash Transfer', 'his/saveOrUpdateCashTransfer', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(94, 'Cash Receipt', 'his/saveOrUpdateCashReceipt', 'ADD_EDIT','Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(95, 'View Cashier Log ', 'his/viewCashierLog', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(96, 'View Bill Log ', 'his/viewBillLog', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Billing
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(97, 'Override Maximum Discount', 'his/overrideMaximumDiscount', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Department
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (98, 'Create/Edit Department', 'his/saveOrUpdateDepartment/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (99, 'View Department', 'his/viewDepartment/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (100, 'Delete Department', 'his/deleteDepartment/', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Sub Department
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (101, 'Create/Edit Sub Department', 'his/saveOrUpdateSubDepartment/', 'ADD_EDIT', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (102, 'View Sub Department', 'his/viewSubDepartment/', 'READ_ONLY', 'Y', current_timestamp, 1, current_timestamp, 1, 1);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (103, 'Delete Sub Department', 'his/deleteSubDepartment/', 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1);

--Pharmacy
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (104,'View Dash Board','pharmacy/viewDashBoard','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (105,'Create / Edit Purchase','pharmacy/saveOrUpdatePurchase','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (106,'Discard Purchase','pharmacy/discardPurchase','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (107,'Confirm Purchase','pharmacy/confirmPurchase','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (108,'View Purchase','pharmacy/viewPurchase','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (109,'Create / Edit Delivery Challan','pharmacy/saveOrUpdateDeliveryChallan','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (110,'Discard Delivery Challan','pharmacy/discardDeliveryChallan','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (111,'Confirm Delivery Challan','pharmacy/confirmDeliveryChallan','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (112,'View Delivery Challan','pharmacy/viewDeliveryChallan','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (113,'Convert To Purchase','pharmacy/convertToPurchase','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (114,'Create / Edit Purchase Return','pharmacy/saveOrUpdatePurchaseReturn','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (115,'Discard Purchase Return','pharmacy/discardPurchaseReturn','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (116,'Confirm Purchase Return','pharmacy/confirmPurchaseReturn','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (117,'View Purchase Return','pharmacy/viewPurchaseReturn','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (118,'View Purchase History','pharmacy/viewPurchaseHistory','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (119,'Discard Pending Purchase Entry','pharmacy/discardPendingPurchaseEntry','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (120,'Create / Edit Sale','pharmacy/saveOrUpdateSale','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (121,'Discard Sale','pharmacy/discardSale','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (122,'Confirm Sale','pharmacy/confirmSale','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (123,'View Sale','pharmacy/viewSale','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (124,'Load Prescription','pharmacy/loadPrescription','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (125,'Choose Discount Schema','pharmacy/chooseDiscountSchema','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (126,'Make Payment','pharmacy/makePayment','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (127,'Create / Edit Sale','pharmacy/saveOrUpdateSale','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (128,'Discard Sale','pharmacy/discardSale','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (129,'View Sale','pharmacy/viewSale','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (130,'Create / Edit SaleReturn','pharmacy/saveOrUpdateSaleReturn','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (131,'Discard SaleReturn','pharmacy/discardSaleReturn','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (132,'Confirm SaleReturn','pharmacy/confirmSaleReturn','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (133,'View SaleReturn','pharmacy/viewSaleReturn','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (134,'View Sale History','pharmacy/viewSaleHistory','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (135,'Discard Pending Sale Entry','pharmacy/discardPendingSaleEntry','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (136,'View Stock Ledger','pharmacy/viewStockLedger','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (137,'View Alternate Product','pharmacy/viewAlternateProducts','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (138,'View Product Detail','pharmacy/viewProductDetails','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (139,'Relocate Product','pharmacy/relocateProducts','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (140,'Adjust Product','pharmacy/adjustProducts','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (141,'Stock Transfer Product Wise','pharmacy/transferProductWise','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (142,'Stock Transfer Purchase Wise','pharmacy/transferPurchaseWise','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (143,'Stock Transfer Request Wise','pharmacy/transferRequestWise','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (144,'View Stock Transfer','pharmacy/viewStockTransfers','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (145,'Stock Issue Product Wise','pharmacy/issueProductWise','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (146,'Stock Issue Request Wise','pharmacy/issueRequestWise','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (147,'View Stock Issue','pharmacy/viewStockIssues','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (148,'Reject Stock Receipt','pharmacy/rejectStockReceipt','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (149,'Confirm Stock Receipt','pharmacy/confirmStockReceipt','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (150,'View Stock Receipt','pharmacy/viewStockReceipt','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (151,'Assign Bin','pharmacy/assignBin','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (152,'Create / Edit Stock Indent','pharmacy/saveOrUpdateStockIndent','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (153,'Discard Stock Indent','pharmacy/discardStockIndent','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (154,'View Stock Indent','pharmacy/viewStockIndent','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (155,'View Stagnant Stocks','pharmacy/viewStagnantStocks','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (156,'View Short Expiry Stock','pharmacy/viewShortExpiryStock','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (157,'View Ageing Report','pharmacy/viewAgeingReport','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (158,'Create Wanted List','pharmacy/saveOrUpdateWantedList','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (159,'Discard Wanted List','pharmacy/discardWantedList','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (160,'View Wanted List','pharmacy/viewWantedList','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (161,'Add Wanted List To PO','pharmacy/addWantedListToPO','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (162,'Reject Wanted List','pharmacy/rejectWantedList','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (163,'View Wanted List','pharmacy/viewWantedList','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (164,'Generate Purchase Order','pharmacy/saveOrUpdatePurchaseOrder','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (165,'View Purchase Order','pharmacy/viewPurchaseOrder','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (166,'Discard Purchase Order','pharmacy/discardPurchaseOrder','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (167,'View Purchase Register','pharmacy/viewPurchaseRegister','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (168,'View Supplier Ledger Default Mode','pharmacy/viewSupplierLedgerDefaultMode','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (169,'View Supplier Ledger Bill Wise Mode','pharmacy/viewSupplierLedgerBillWiseMode','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (170,'View Supplier Ledger Payment Wise Mode','pharmacy/viewSupplierLedgerPaymentWiseMode','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (171,'View Supplier Ledger Payment Mode','pharmacy/viewSupplierLedgerPaymentMode','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (172,'View Sales Register Default Mode','pharmacy/viewSalesRegisterDefaultMode','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (173,'View Sales Register Expanded Mode','pharmacy/viewSalesRegisterExpandedMode','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (174,'Create / Edit Receipt','pharmacy/saveOrUpdateReceipt','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (175,'Discard Receipt','pharmacy/discardReceipt','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (176,'View Receipt','pharmacy/viewReceipt','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (177,'Create / Edit Payment Process','pharmacy/processPayment','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (178,'Discard Payment Process','pharmacy/discardProcessedPayment','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (179,'View Payment Process','pharmacy/viewProcessedPayment','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (180,'Approve Payment','pharmacy/approvePayment','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (181,'Reject Payment','pharmacy/rejectPayment','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (182,'View Payment Approval','pharmacy/viewApprovedPayment','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (183,'Create / Edit Payment ','pharmacy/saveOrUpdatePayment','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (184,'Discard Payment','pharmacy/discardPayment','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (185,'Do Payment','pharmacy/doPayment','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (186,'View Credit Note','pharmacy/viewCreditNote','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (187,'View Debit Note','pharmacy/viewDebitNote','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (188,'Collect Cash','pharmacy/collectCash','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (189,'View Receipts','pharmacy/viewReceipt','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (190,'Transfer Fund','pharmacy/transferFund','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (191,'View Cash Register','pharmacy/viewCashRegister','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (192,'Create / Edit Account Setting','pharmacy/saveOrUpdateAccountSetting','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (193,'View Account Setting','pharmacy/viewAccountSetting','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (194,'Create / Edit Product Group','pharmacy/saveOrUpdateProductGroup','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (195,'Delete Product Group','pharmacy/deleteProductGroup','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (196,'View Product Group','pharmacy/viewProductGroup','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (197,'Create / Edit Product Category','pharmacy/saveOrUpdateProductCategory','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (198,'Delete Product Category','pharmacy/deleteProductCategory','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (199,'View Product Category','pharmacy/viewProductCategory','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (200,'Create / Edit Division','pharmacy/saveOrUpdateDivision','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (201,'Delete Division','pharmacy/deleteDivision','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (202,'View Division','pharmacy/viewDivision','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (203,'Create / Edit Tax','pharmacy/saveOrUpdateTax','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (204,'Delete Tax','pharmacy/deleteTax','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (205,'View Tax','pharmacy/viewTax','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (206,'Create / Edit Manufacturer','pharmacy/saveOrUpdateManufacturer','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (207,'Delete Manufacturer','pharmacy/deleteManufacturer','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (208,'View Manufacturer','pharmacy/viewManufacturer','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (209,'Create / Edit Marketer','pharmacy/saveOrUpdate','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (210,'Delete Marketer','pharmacy/deleteMarketer','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (211,'View Marketer','pharmacy/viewMarketer','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (212,'Create / Edit Rack and Bin','pharmacy/saveOrUpdateRackAndBin','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (213,'Delete Rack and Bin','pharmacy/deleteRackAndBin','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (214,'View Rack and Bin','pharmacy/viewRackAndBin','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (215,'Create / Edit Composition','pharmacy/saveOrUpdateComposition','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (216,'Delete Composition','pharmacy/deleteComposition','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (217,'View Composition','pharmacy/viewComposition','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (218,'Create / Edit Hsn Code','pharmacy/saveOrUpdateHsnCode','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (219,'Delete Hsn Code','pharmacy/deleteHsnCode','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (220,'View Hsn Code','pharmacy/viewHsnCode','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (221,'Create / Edit Doctor','pharmacy/saveOrUpdateDoctor','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (222,'Delete Doctor','pharmacy/deleteDoctor','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (223,'View Doctor','pharmacy/viewDoctor','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (224,'Create / Edit Speciality','pharmacy/saveOrUpdateSpeciality','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (225,'Delete Speciality','pharmacy/deleteSpeciality','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (226,'View Speciality','pharmacy/viewSpeciality','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (227,'Create / Edit Location','pharmacy/saveOrUpdateLocation','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (228,'Delete Location','pharmacy/deleteLocation','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (229,'View Location','pharmacy/viewLocation','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (230,'Create / Edit Drug Classification','pharmacy/saveOrUpdateDrugClassification','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (231,'Delete Drug Classification','pharmacy/deleteDrugClassification','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (232,'View Drug Classification','pharmacy/viewDrugClassification','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (233,'Create / Edit Product','pharmacy/saveOrUpdateProduct','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (234,'Delete Product','pharmacy/deleteProduct','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (235,'View Product','pharmacy/viewProduct','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (236,'Create / Edit Supplier','pharmacy/saveOrUpdateSupplier','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (237,'Delete Supplier','pharmacy/deleteSupplier','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (238,'View Supplier','pharmacy/viewSupplier','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (239,'Create / Edit Unit','pharmacy/saveOrUpdateUnit','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (240,'Delete Unit','pharmacy/deleteUnit','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (241,'View Units','pharmacy/viewUnit','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (242,'Create / Edit Discount Schema','pharmacy/saveOrUpdateDiscountSchema','ADD_EDIT','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (243,'Delete Discount Schema','pharmacy/deleteDiscountSchema','FULL','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (244,'View Discount Schema','pharmacy/viewDiscountSchema','READ_ONLY','Y',now(),1,now(),1,2);
INSERT INTO resource (id, name, url, permission_type, active, created_date, created_by, last_modified, modified_by, application_id) VALUES (245,'View Report','pharmacy/viewReport','READ_ONLY','Y',now(),1,now(),1,2);


--Role resource
--His
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (1, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 1);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (2, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 2);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (3, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 3);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (4, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 4);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (5, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 5);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (6, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 6);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (7, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 7);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (8, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 8);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (9, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 9);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (10, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 10);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (11, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 11);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (12, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 12);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (13, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 13);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (14, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 14);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (15, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 15);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (16, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 16);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (17, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 17);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (18, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 18);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (19, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 19);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (20, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 20);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (21, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 21);

INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (22, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 22);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (23, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 23);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (24, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 24);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (25, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 25);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (26, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 26);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (27, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 27);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (28, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 28);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (29, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 29);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (30, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 30);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (31, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 31);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (32, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 32);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (33, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 33);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (34, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 34);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (35, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 35);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (36, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 36);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (37, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 37);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (38, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 38);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (39, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 39);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (40, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 40);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (41, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 41);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (42, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 42);

INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (43, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 43);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (44, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 44);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (45, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 45);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (46, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 46);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (47, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 47);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (48, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 48);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (49, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 49);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (50, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 50);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (51, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 51);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (52, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 52);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (53, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 53);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (54, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 54);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (55, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 55);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (56, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 56);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (57, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 57);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (58, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 58);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (59, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 59);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (60, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 60);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (61, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 61);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (62, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 62);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (63, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 63);

INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (64, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 64);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (65, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 65);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (66, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 66);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (67, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 67);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (68, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 68);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (69, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 69);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (70, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 70);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (71, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 71);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (72, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 72);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (73, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 73);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (74, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 74);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (75, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 75);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (76, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 76);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (77, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 77);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (78, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 78);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (79, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 79);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (80, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 80);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (81, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 81);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (82, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 82);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (83, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 83);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (84, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 84);

INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (85, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 85);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (86, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 86);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (87, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 87);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (88, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 88);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (89, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 89);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (90, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 90);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (91, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 91);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (92, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 92);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (93, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 93);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (94, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 94);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (95, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 95);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (96, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 96);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (97, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 97);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (98, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 98);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (99, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 99);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (100, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 100);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (101, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 101);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (102, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 102);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (103, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 103);

--Pharmacy
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (104, 'Y', now(), 1, now(), 1, 2, 104);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (105, 'Y', now(), 1, now(), 1, 2, 105);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (106, 'Y', now(), 1, now(), 1, 2, 106);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (107, 'Y', now(), 1, now(), 1, 2, 107);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (108, 'Y', now(), 1, now(), 1, 2, 108);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (109, 'Y', now(), 1, now(), 1, 2, 109);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (110, 'Y', now(), 1, now(), 1, 2, 110);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (111, 'Y', now(), 1, now(), 1, 2, 111);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (112, 'Y', now(), 1, now(), 1, 2, 112);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (113, 'Y', now(), 1, now(), 1, 2, 113);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (114, 'Y', now(), 1, now(), 1, 2, 114);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (115, 'Y', now(), 1, now(), 1, 2, 115);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (116, 'Y', now(), 1, now(), 1, 2, 116);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (117, 'Y', now(), 1, now(), 1, 2, 117);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (118, 'Y', now(), 1, now(), 1, 2, 118);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (119, 'Y', now(), 1, now(), 1, 2, 119);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (120, 'Y', now(), 1, now(), 1, 2, 120);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (121, 'Y', now(), 1, now(), 1, 2, 121);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (122, 'Y', now(), 1, now(), 1, 2, 122);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (123, 'Y', now(), 1, now(), 1, 2, 123);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (124, 'Y', now(), 1, now(), 1, 2, 124);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (125, 'Y', now(), 1, now(), 1, 2, 125);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (126, 'Y', now(), 1, now(), 1, 2, 126);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (127, 'Y', now(), 1, now(), 1, 2, 127);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (128, 'Y', now(), 1, now(), 1, 2, 128);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (129, 'Y', now(), 1, now(), 1, 2, 129);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (130, 'Y', now(), 1, now(), 1, 2, 130);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (131, 'Y', now(), 1, now(), 1, 2, 131);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (132, 'Y', now(), 1, now(), 1, 2, 132);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (133, 'Y', now(), 1, now(), 1, 2, 133);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (134, 'Y', now(), 1, now(), 1, 2, 134);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (135, 'Y', now(), 1, now(), 1, 2, 135);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (136, 'Y', now(), 1, now(), 1, 2, 136);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (137, 'Y', now(), 1, now(), 1, 2, 137);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (138, 'Y', now(), 1, now(), 1, 2, 138);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (139, 'Y', now(), 1, now(), 1, 2, 139);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (140, 'Y', now(), 1, now(), 1, 2, 140);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (141, 'Y', now(), 1, now(), 1, 2, 141);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (142, 'Y', now(), 1, now(), 1, 2, 142);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (143, 'Y', now(), 1, now(), 1, 2, 143);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (144, 'Y', now(), 1, now(), 1, 2, 144);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (145, 'Y', now(), 1, now(), 1, 2, 145);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (146, 'Y', now(), 1, now(), 1, 2, 146);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (147, 'Y', now(), 1, now(), 1, 2, 147);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (148, 'Y', now(), 1, now(), 1, 2, 148);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (149, 'Y', now(), 1, now(), 1, 2, 149);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (150, 'Y', now(), 1, now(), 1, 2, 150);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (151, 'Y', now(), 1, now(), 1, 2, 151);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (152, 'Y', now(), 1, now(), 1, 2, 152);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (153, 'Y', now(), 1, now(), 1, 2, 153);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (154, 'Y', now(), 1, now(), 1, 2, 154);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (155, 'Y', now(), 1, now(), 1, 2, 155);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (156, 'Y', now(), 1, now(), 1, 2, 156);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (157, 'Y', now(), 1, now(), 1, 2, 157);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (158, 'Y', now(), 1, now(), 1, 2, 158);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (159, 'Y', now(), 1, now(), 1, 2, 159);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (160, 'Y', now(), 1, now(), 1, 2, 160);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (161, 'Y', now(), 1, now(), 1, 2, 161);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (162, 'Y', now(), 1, now(), 1, 2, 162);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (163, 'Y', now(), 1, now(), 1, 2, 163);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (164, 'Y', now(), 1, now(), 1, 2, 164);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (165, 'Y', now(), 1, now(), 1, 2, 165);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (166, 'Y', now(), 1, now(), 1, 2, 166);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (167, 'Y', now(), 1, now(), 1, 2, 167);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (168, 'Y', now(), 1, now(), 1, 2, 168);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (169, 'Y', now(), 1, now(), 1, 2, 169);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (170, 'Y', now(), 1, now(), 1, 2, 170);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (171, 'Y', now(), 1, now(), 1, 2, 171);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (172, 'Y', now(), 1, now(), 1, 2, 172);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (173, 'Y', now(), 1, now(), 1, 2, 173);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (174, 'Y', now(), 1, now(), 1, 2, 174);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (175, 'Y', now(), 1, now(), 1, 2, 175);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (176, 'Y', now(), 1, now(), 1, 2, 176);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (177, 'Y', now(), 1, now(), 1, 2, 177);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (178, 'Y', now(), 1, now(), 1, 2, 178);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (179, 'Y', now(), 1, now(), 1, 2, 179);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (180, 'Y', now(), 1, now(), 1, 2, 180);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (181, 'Y', now(), 1, now(), 1, 2, 181);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (182, 'Y', now(), 1, now(), 1, 2, 182);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (183, 'Y', now(), 1, now(), 1, 2, 183);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (184, 'Y', now(), 1, now(), 1, 2, 184);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (185, 'Y', now(), 1, now(), 1, 2, 185);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (186, 'Y', now(), 1, now(), 1, 2, 186);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (187, 'Y', now(), 1, now(), 1, 2, 187);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (188, 'Y', now(), 1, now(), 1, 2, 188);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (189, 'Y', now(), 1, now(), 1, 2, 189);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (190, 'Y', now(), 1, now(), 1, 2, 190);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (191, 'Y', now(), 1, now(), 1, 2, 191);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (192, 'Y', now(), 1, now(), 1, 2, 192);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (193, 'Y', now(), 1, now(), 1, 2, 193);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (194, 'Y', now(), 1, now(), 1, 2, 194);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (195, 'Y', now(), 1, now(), 1, 2, 195);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (196, 'Y', now(), 1, now(), 1, 2, 196);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (197, 'Y', now(), 1, now(), 1, 2, 197);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (198, 'Y', now(), 1, now(), 1, 2, 198);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (199, 'Y', now(), 1, now(), 1, 2, 199);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (200, 'Y', now(), 1, now(), 1, 2, 200);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (201, 'Y', now(), 1, now(), 1, 2, 201);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (202, 'Y', now(), 1, now(), 1, 2, 202);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (203, 'Y', now(), 1, now(), 1, 2, 203);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (204, 'Y', now(), 1, now(), 1, 2, 204);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (205, 'Y', now(), 1, now(), 1, 2, 205);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (206, 'Y', now(), 1, now(), 1, 2, 206);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (207, 'Y', now(), 1, now(), 1, 2, 207);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (208, 'Y', now(), 1, now(), 1, 2, 208);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (209, 'Y', now(), 1, now(), 1, 2, 209);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (210, 'Y', now(), 1, now(), 1, 2, 210);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (211, 'Y', now(), 1, now(), 1, 2, 211);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (212, 'Y', now(), 1, now(), 1, 2, 212);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (213, 'Y', now(), 1, now(), 1, 2, 213);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (214, 'Y', now(), 1, now(), 1, 2, 214);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (215, 'Y', now(), 1, now(), 1, 2, 215);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (216, 'Y', now(), 1, now(), 1, 2, 216);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (217, 'Y', now(), 1, now(), 1, 2, 217);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (218, 'Y', now(), 1, now(), 1, 2, 218);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (219, 'Y', now(), 1, now(), 1, 2, 219);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (220, 'Y', now(), 1, now(), 1, 2, 220);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (221, 'Y', now(), 1, now(), 1, 2, 221);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (222, 'Y', now(), 1, now(), 1, 2, 222);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (223, 'Y', now(), 1, now(), 1, 2, 223);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (224, 'Y', now(), 1, now(), 1, 2, 224);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (225, 'Y', now(), 1, now(), 1, 2, 225);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (226, 'Y', now(), 1, now(), 1, 2, 226);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (227, 'Y', now(), 1, now(), 1, 2, 227);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (228, 'Y', now(), 1, now(), 1, 2, 228);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (229, 'Y', now(), 1, now(), 1, 2, 229);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (230, 'Y', now(), 1, now(), 1, 2, 230);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (231, 'Y', now(), 1, now(), 1, 2, 231);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (232, 'Y', now(), 1, now(), 1, 2, 232);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (233, 'Y', now(), 1, now(), 1, 2, 233);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (234, 'Y', now(), 1, now(), 1, 2, 234);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (235, 'Y', now(), 1, now(), 1, 2, 235);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (236, 'Y', now(), 1, now(), 1, 2, 236);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (237, 'Y', now(), 1, now(), 1, 2, 237);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (238, 'Y', now(), 1, now(), 1, 2, 238);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (239, 'Y', now(), 1, now(), 1, 2, 239);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (240, 'Y', now(), 1, now(), 1, 2, 240);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (241, 'Y', now(), 1, now(), 1, 2, 241);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (242, 'Y', now(), 1, now(), 1, 2, 242);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (243, 'Y', now(), 1, now(), 1, 2, 243);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (244, 'Y', now(), 1, now(), 1, 2, 244);
INSERT INTO role_resource (id, active, created_date, created_by, last_modified, modified_by, role_id, resource_id) VALUES (245, 'Y', now(), 1, now(), 1, 2, 245);

--Menu
--His
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (1, 'Front Office', NULL, 0, 1, 'icon-front-office', 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (2, 'Registration', 'front-office/registration', 1, 1, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (3, 'Patient Explorer', 'front-office/patient-explorer', 1, 2, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (4, 'Appointment', 'front-office/appointment', 1, 3, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);

INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (5, 'Accounts', NULL, 0, 2, 'icon-bill', 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (6, 'Billing', 'accounts/billing', 5, 1, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);


INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (7, 'Preferences', NULL, 0, 4, 'icon-settings', 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (8, 'Doctors', 'preferences/doctors', 7, 1, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (9, 'Service And Charges', 'preferences/services-and-charges', 7, 2, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (10, 'Speciality', 'preferences/speciality', 7, 5, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (11, 'Sub-Speciality', 'preferences/sub-speciality', 7, 6, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (12, 'Units', 'preferences/units', 7, 7, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (13, 'Service  Categories', 'preferences/service-categories', 7, 8, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (14, 'Specialisation', 'preferences/specialisation', 7, 9, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (15, 'Doctor Affiliation', 'preferences/doctor-affiliation', 7, 10, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (16, 'Insurance', 'preferences/insurance', 7, 11, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (17, 'Corporate', 'preferences/corporate', 7, 12, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (18, 'Blood Group', 'preferences/blood-group', 7, 13, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (19, 'Language', 'preferences/language', 7, 14, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (20, 'Marital Status', 'preferences/marital-status', 7, 15, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (21, 'RelationShip', 'preferences/relationship', 7, 16, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (22, 'Salutation', 'preferences/salutation', 7, 17, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (23, 'Price Book', 'preferences/price-book', 7, 18, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (24, 'Visit Purpose', 'preferences/visit-purpose', 7, 19, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (25, 'Referral Type', 'preferences/referral-type', 7, 20, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (26, 'Events', 'preferences/events', 7, 21, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);

INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (27, 'User Management', NULL, 0, 3, 'icon-user-management', 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (28, 'User Creation', 'user-management/user-creation',27, 1, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (29, 'User Role', 'user-management/user-role',27, 2, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);

INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (30, 'Receipts', 'accounts/receipt', 5, 2, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (31, 'Advance', 'accounts/advance', 5, 3, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (32, 'Credit Note', 'accounts/credit-note', 5, 4, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (33, 'Cash Counter', 'accounts/cash-counter', 5, 5, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (34, 'Cash Register', 'accounts/cash-register', 5, 6, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (35, 'Payment', 'accounts/payment', 5, 7, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (36, 'Worklist', 'front-office/worklist', 1, 4, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (37, 'Token Management', 'front-office/token-management', 1, 5, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);

INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (38, 'Reports', NULL, 0, 5, 'icon-report', 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (39, 'Day Book', 'reports/day-book', 38, 1, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (40, 'Sales Register', 'reports/sales-register', 38, 2, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (41, 'Report Configuration', 'preferences/report-configuration', 7, 22, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (42, 'Department', 'preferences/department', 7, 3, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES (43, 'Sub-Department', 'preferences/sub-department', 7, 4, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(101, 'Packages', 'preferences/packages',7, 23, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(102, 'Taxes', 'preferences/taxes',7, 24, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id)
VALUES(103, 'Print Specification', 'preferences/print-specification',7, 25, NULL, 'Y', current_timestamp, 1, current_timestamp, 1 , 1);

--Pharmacy
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(44,'Dashboard','NULL',0,1,'icon-dashboard','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(45,'Purchase','NULL',0,2,'icon-purchase','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(46,'Purchase Entry','purchase/purchase-entry',45,1,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(47,'Delivery Challan','purchase/delivery-challan',45,2,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(48,'Purchase Return','purchase/purchase-return',45,3,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(49,'Purchase History','purchase/purchase-history',45,4,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(50,'Sales','NULL',0,3,'icon-sales-tag','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(51,'Sales Entry','sales/sales-entry',50,1,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(52,'Provisional Sales','sales/provisional-sales',50,2,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(53,'Sales Return','sales/sales-return',50,3,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(54,'Sales History','sales/sales-history',50,4,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(55,'Stock Management','NULL',0,4,'icon-stock','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(56,'Stock Ledger','stock/stock-ledger',55,1,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(57,'Stock Transfer','stock/stock-transfer',55,2,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(58,'Stock Issue','stock/stock-issue',55,3,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(59,'Stock Receipt','stock/stock-receipt',55,4,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(60,'Stock Indent','stock/stock-indent',55,5,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(61,'Stagnant Stock','stock/stagnant-stock',55,6,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(62,'Short Expiry Stock','stock/short-expiry-stock',55,7,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(63,'Purchase Order','NULL',0,5,'icon-shopping','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(64,'Wanted List','purchase-order/wanted-list',63,1,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(65,'PO Processing','purchase-order/po-processing',63,2,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(66,'PO Generation','purchase-order/po-generation',63,3,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(67,'Accounts','NULL',0,6,'icon-bill','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(68,'Purchase Register','accounts/purchase-register',67,1,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(69,'Sales Register','accounts/sales-register',67,2,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(70,'Receipt','accounts/receipt',67,3,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(71,'Payment Process','accounts/payment-process',67,4,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(72,'Payment Approval','accounts/payment-approval',67,5,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(73,'Payment','accounts/payment',67,6,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(74,'Credit Note','accounts/credit-note',67,7,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(75,'Debit Note','accounts/debit-note',67,8,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(76,'Cash Counter','accounts/cash-counter',67,9,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(77,'Cash Register','accounts/cash-register',67,10,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(78,'Preferences','NULL',0,8,'icon-settings','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(79,'Account Settings','preferences/account-settings',78,1,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(80,'Product Groups','preferences/product-group',78,2,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(81,'Product Categories','preferences/product-category',78,3,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(82,'Divisions','preferences/division',78,4,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(83,'Duties and Taxes','preferences/duties-and-taxes',78,5,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(84,'Manufacturers','preferences/manufacturers',78,6,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(85,'Marketers','preferences/marketers',78,7,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(86,'Racks and Bins','preferences/rack-and-bin',78,8,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(87,'Compositions','preferences/molecule',78,9,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(88,'Hsn Codes','preferences/hsncode',78,10,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(89,'Doctors','preferences/doctors',78,11,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(90,'Specialities','preferences/speciality',78,12,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(91,'Locations','preferences/location',78,13,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(92,'Drug Classifications','preferences/drug-classification',78,14,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(93,'Products','preferences/product-management',78,15,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(94,'Suppliers','preferences/supplier-management',78,16,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(95,'Stock Units','preferences/stock-units',78,17,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(96,'Discount Schemas','preferences/discount-schema',78,18,'NULL','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) values(97,'Reports','NULL',0,9,'icon-report','Y',now(),1,now(),1,2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) VALUES(98, 'User Management', NULL, 0, 7, 'icon-user-management', 'Y', now(), 1, now(), 1 , 2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) VALUES(99, 'User Creation', 'user-management/user-creation',98, 1, NULL, 'Y', now(), 1, now(), 1 , 2);
INSERT INTO menu (id, name, url, parent_menu_id, jhi_order, icon, active, created_date, created_by, last_modified, modified_by, application_id) VALUES(100, 'User Role', 'user-management/user-role',98, 2, NULL, 'Y', now(), 1, now(), 1 , 2);
--Role menu
--His
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (1, 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 2);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (2, 'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 3);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (3,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 4);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (4,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 6);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (5,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 8);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (6,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 9);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (7,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 10);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (8,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 11);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (9,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 12);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (10,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 13);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (11,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 14);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (12,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 15);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (13,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 16);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (14,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 17);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (15,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 18);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (16,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 19);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (17,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 20);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (18,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 21);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (19,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 22);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (20,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 23);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (21,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 24);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (22,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 25);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (23,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 26);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (24,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 28);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (25,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 29);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (26,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 30);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (27,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 31);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (28,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 32);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (29,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 33);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (30,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 34);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (31,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 35);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (32,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 36);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (33,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 37);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (34,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 38);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (35,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 39);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (36,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 40);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (37,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 41);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (38,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 42);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (39,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 43);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (96,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 101);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (97,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 102);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (98,'FULL', 'Y', current_timestamp, 1, current_timestamp, 1, 1, 103);

--Pharmacy
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (40, 'FULL', 'Y', now(), 1,now(), 1,2,44);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (41, 'FULL', 'Y', now(), 1,now(), 1,2,45);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (42, 'FULL', 'Y', now(), 1,now(), 1,2,46);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (43, 'FULL', 'Y', now(), 1,now(), 1,2,47);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (44, 'FULL', 'Y', now(), 1,now(), 1,2,48);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (45, 'FULL', 'Y', now(), 1,now(), 1,2,49);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (46, 'FULL', 'Y', now(), 1,now(), 1,2,50);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (47, 'FULL', 'Y', now(), 1,now(), 1,2,51);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (48, 'FULL', 'Y', now(), 1,now(), 1,2,52);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (49, 'FULL', 'Y', now(), 1,now(), 1,2,53);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (50, 'FULL', 'Y', now(), 1,now(), 1,2,54);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (51, 'FULL', 'Y', now(), 1,now(), 1,2,55);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (52, 'FULL', 'Y', now(), 1,now(), 1,2,56);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (53, 'FULL', 'Y', now(), 1,now(), 1,2,57);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (54, 'FULL', 'Y', now(), 1,now(), 1,2,58);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (55, 'FULL', 'Y', now(), 1,now(), 1,2,59);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (56, 'FULL', 'Y', now(), 1,now(), 1,2,60);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (57, 'FULL', 'Y', now(), 1,now(), 1,2,61);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (58, 'FULL', 'Y', now(), 1,now(), 1,2,62);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (59, 'FULL', 'Y', now(), 1,now(), 1,2,63);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (60, 'FULL', 'Y', now(), 1,now(), 1,2,64);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (61, 'FULL', 'Y', now(), 1,now(), 1,2,65);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (62, 'FULL', 'Y', now(), 1,now(), 1,2,66);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (63, 'FULL', 'Y', now(), 1,now(), 1,2,67);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (64, 'FULL', 'Y', now(), 1,now(), 1,2,68);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (65, 'FULL', 'Y', now(), 1,now(), 1,2,69);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (66, 'FULL', 'Y', now(), 1,now(), 1,2,70);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (67, 'FULL', 'Y', now(), 1,now(), 1,2,71);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (68, 'FULL', 'Y', now(), 1,now(), 1,2,72);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (69, 'FULL', 'Y', now(), 1,now(), 1,2,73);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (70, 'FULL', 'Y', now(), 1,now(), 1,2,74);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (71, 'FULL', 'Y', now(), 1,now(), 1,2,75);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (72, 'FULL', 'Y', now(), 1,now(), 1,2,76);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (73, 'FULL', 'Y', now(), 1,now(), 1,2,77);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (74, 'FULL', 'Y', now(), 1,now(), 1,2,78);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (75, 'FULL', 'Y', now(), 1,now(), 1,2,79);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (76, 'FULL', 'Y', now(), 1,now(), 1,2,80);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (77, 'FULL', 'Y', now(), 1,now(), 1,2,81);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (78, 'FULL', 'Y', now(), 1,now(), 1,2,82);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (79, 'FULL', 'Y', now(), 1,now(), 1,2,83);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (80, 'FULL', 'Y', now(), 1,now(), 1,2,84);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (81, 'FULL', 'Y', now(), 1,now(), 1,2,85);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (82, 'FULL', 'Y', now(), 1,now(), 1,2,86);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (83, 'FULL', 'Y', now(), 1,now(), 1,2,87);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (84, 'FULL', 'Y', now(), 1,now(), 1,2,88);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (85, 'FULL', 'Y', now(), 1,now(), 1,2,89);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (86, 'FULL', 'Y', now(), 1,now(), 1,2,90);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (87, 'FULL', 'Y', now(), 1,now(), 1,2,91);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (88, 'FULL', 'Y', now(), 1,now(), 1,2,92);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (89, 'FULL', 'Y', now(), 1,now(), 1,2,93);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (90, 'FULL', 'Y', now(), 1,now(), 1,2,94);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (91, 'FULL', 'Y', now(), 1,now(), 1,2,95);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (92, 'FULL', 'Y', now(), 1,now(), 1,2,96);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (93, 'FULL', 'Y', now(), 1,now(), 1,2,97);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (94, 'FULL', 'Y', now(), 1,now(), 1,2,99);
INSERT INTO role_menu (id, permission_type, active, created_date, created_by, last_modified, modified_by, role_id, menu_id) VALUES (95, 'FULL', 'Y', now(), 1,now(), 1,2,100);

--Menu resource
--His
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (1, 'Y', current_timestamp, 1, current_timestamp, 1, 2, 1);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (2, 'Y', current_timestamp, 1, current_timestamp, 1, 2, 2);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (3, 'Y', current_timestamp, 1, current_timestamp, 1, 2, 3);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (4, 'Y', current_timestamp, 1, current_timestamp, 1, 2, 4);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (5, 'Y', current_timestamp, 1, current_timestamp, 1, 2, 5);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (6, 'Y', current_timestamp, 1, current_timestamp, 1, 2, 6);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (7, 'Y', current_timestamp, 1, current_timestamp, 1, 2, 16);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (8, 'Y', current_timestamp, 1, current_timestamp, 1, 3, 17);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (9, 'Y', current_timestamp, 1, current_timestamp, 1, 4, 18);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (10, 'Y', current_timestamp, 1, current_timestamp, 1, 4, 19);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (11, 'Y', current_timestamp, 1, current_timestamp, 1, 4, 20);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (12, 'Y', current_timestamp, 1, current_timestamp, 1, 4, 21);

INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (13, 'Y', current_timestamp, 1, current_timestamp, 1, 8, 1);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (14, 'Y', current_timestamp, 1, current_timestamp, 1, 8, 2);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (15, 'Y', current_timestamp, 1, current_timestamp, 1, 8, 3);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (16, 'Y', current_timestamp, 1, current_timestamp, 1, 8, 4);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (17, 'Y', current_timestamp, 1, current_timestamp, 1, 8, 5);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (18, 'Y', current_timestamp, 1, current_timestamp, 1, 8, 6);


INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (19, 'Y', current_timestamp, 1, current_timestamp, 1, 16, 1);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (20, 'Y', current_timestamp, 1, current_timestamp, 1, 16, 2);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (21, 'Y', current_timestamp, 1, current_timestamp, 1, 16, 3);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (22, 'Y', current_timestamp, 1, current_timestamp, 1, 16, 4);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (23, 'Y', current_timestamp, 1, current_timestamp, 1, 16, 5);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (24, 'Y', current_timestamp, 1, current_timestamp, 1, 16, 6);



INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (25, 'Y', current_timestamp, 1, current_timestamp, 1, 17, 1);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (26, 'Y', current_timestamp, 1, current_timestamp, 1, 17, 2);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (27, 'Y', current_timestamp, 1, current_timestamp, 1, 17, 3);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (28, 'Y', current_timestamp, 1, current_timestamp, 1, 17, 4);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (29, 'Y', current_timestamp, 1, current_timestamp, 1, 17, 5);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (30, 'Y', current_timestamp, 1, current_timestamp, 1, 17, 6);


--Doctor Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (31, 'Y', current_timestamp, 1, current_timestamp, 1, 8, 22);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (32, 'Y', current_timestamp, 1, current_timestamp, 1, 8, 23);

--Doctor Schedule Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (33, 'Y', current_timestamp, 1, current_timestamp, 1, 8, 24);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (34, 'Y', current_timestamp, 1, current_timestamp, 1, 8, 25);

--Service & Charges Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (35, 'Y', current_timestamp, 1, current_timestamp, 1, 9, 26);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (36, 'Y', current_timestamp, 1, current_timestamp, 1, 9, 27);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (37, 'Y', current_timestamp, 1, current_timestamp, 1, 9, 28);

--Speciality Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (38, 'Y', current_timestamp, 1, current_timestamp, 1, 10, 29);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (39, 'Y', current_timestamp, 1, current_timestamp, 1, 10, 30);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (40, 'Y', current_timestamp, 1, current_timestamp, 1, 10, 31);

--Sub Speciality Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (41, 'Y', current_timestamp, 1, current_timestamp, 1, 11, 32);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (42, 'Y', current_timestamp, 1, current_timestamp, 1, 11, 33);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (43, 'Y', current_timestamp, 1, current_timestamp, 1, 11, 34);

--Units Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (44, 'Y', current_timestamp, 1, current_timestamp, 1, 12, 35);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (45, 'Y', current_timestamp, 1, current_timestamp, 1, 12, 36);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (46, 'Y', current_timestamp, 1, current_timestamp, 1, 12, 37);

--Service Categories Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (47, 'Y', current_timestamp, 1, current_timestamp, 1, 13, 38);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (48, 'Y', current_timestamp, 1, current_timestamp, 1, 13, 39);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (49, 'Y', current_timestamp, 1, current_timestamp, 1, 13, 40);

--Specialisation Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (50, 'Y', current_timestamp, 1, current_timestamp, 1, 14, 41);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (51, 'Y', current_timestamp, 1, current_timestamp, 1, 14, 42);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (52, 'Y', current_timestamp, 1, current_timestamp, 1, 14, 43);

--Insurance Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (53, 'Y', current_timestamp, 1, current_timestamp, 1, 16, 44);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (54, 'Y', current_timestamp, 1, current_timestamp, 1, 16, 45);

--Corporate Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (55, 'Y', current_timestamp, 1, current_timestamp, 1, 17, 46);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (56, 'Y', current_timestamp, 1, current_timestamp, 1, 17, 47);

--Doctor Affiliation Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (57, 'Y', current_timestamp, 1, current_timestamp, 1, 15, 48);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (58, 'Y', current_timestamp, 1, current_timestamp, 1, 15, 49);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (59, 'Y', current_timestamp, 1, current_timestamp, 1, 15, 50);

--BloodGroup Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (60, 'Y', current_timestamp, 1, current_timestamp, 1, 18, 51);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (61, 'Y', current_timestamp, 1, current_timestamp, 1, 18, 52);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (62, 'Y', current_timestamp, 1, current_timestamp, 1, 18, 53);

--Languages Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (63, 'Y', current_timestamp, 1, current_timestamp, 1, 19, 54);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (64, 'Y', current_timestamp, 1, current_timestamp, 1, 19, 55);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (65, 'Y', current_timestamp, 1, current_timestamp, 1, 19, 56);

--Marital Status
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (66, 'Y', current_timestamp, 1, current_timestamp, 1, 20, 57);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (67, 'Y', current_timestamp, 1, current_timestamp, 1, 20, 58);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (68, 'Y', current_timestamp, 1, current_timestamp, 1, 20, 59);

--RelationShip
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (69, 'Y', current_timestamp, 1, current_timestamp, 1, 21, 60);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (70, 'Y', current_timestamp, 1, current_timestamp, 1, 21, 61);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (71, 'Y', current_timestamp, 1, current_timestamp, 1, 21, 62);

--Salutation Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (72, 'Y', current_timestamp, 1, current_timestamp, 1, 22, 63);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (73, 'Y', current_timestamp, 1, current_timestamp, 1, 22, 64);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (74, 'Y', current_timestamp, 1, current_timestamp, 1, 22, 65);

--Price Book Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (75, 'Y', current_timestamp, 1, current_timestamp, 1, 23, 66);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (76, 'Y', current_timestamp, 1, current_timestamp, 1, 23, 67);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (77, 'Y', current_timestamp, 1, current_timestamp, 1, 23, 68);

--Visit Purpose Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (78, 'Y', current_timestamp, 1, current_timestamp, 1, 24, 69);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (79, 'Y', current_timestamp, 1, current_timestamp, 1, 24, 70);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (80, 'Y', current_timestamp, 1, current_timestamp, 1, 24, 71);

--Referral Type
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (81, 'Y', current_timestamp, 1, current_timestamp, 1, 25, 72);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (82, 'Y', current_timestamp, 1, current_timestamp, 1, 25, 73);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (83, 'Y', current_timestamp, 1, current_timestamp, 1, 25, 74);

--Events
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (84, 'Y', current_timestamp, 1, current_timestamp, 1, 26, 75);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (85, 'Y', current_timestamp, 1, current_timestamp, 1, 26, 76);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (86, 'Y', current_timestamp, 1, current_timestamp, 1, 26, 77);

--Billing
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (87, 'Y', current_timestamp, 1, current_timestamp, 1, 6, 78);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (88, 'Y', current_timestamp, 1, current_timestamp, 1, 6, 79);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (89, 'Y', current_timestamp, 1, current_timestamp, 1, 6, 80);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (90, 'Y', current_timestamp, 1, current_timestamp, 1, 6, 81);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (91, 'Y', current_timestamp, 1, current_timestamp, 1, 6, 82);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (92, 'Y', current_timestamp, 1, current_timestamp, 1, 6, 83);

--Receipt
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (93, 'Y', current_timestamp, 1, current_timestamp, 1, 30, 84);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (94, 'Y', current_timestamp, 1, current_timestamp, 1, 30, 85);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (95, 'Y', current_timestamp, 1, current_timestamp, 1, 30, 86);

--Credit Note
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (96, 'Y', current_timestamp, 1, current_timestamp, 1, 32, 87);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (97, 'Y', current_timestamp, 1, current_timestamp, 1, 32, 88);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (98, 'Y', current_timestamp, 1, current_timestamp, 1, 32, 89);

--Cash Counter
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (99, 'Y', current_timestamp, 1, current_timestamp, 1, 33, 90);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (100, 'Y', current_timestamp, 1, current_timestamp, 1, 33, 91);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (101, 'Y', current_timestamp, 1, current_timestamp, 1, 33, 92);

--Cash Register
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (102, 'Y', current_timestamp, 1, current_timestamp, 1, 34, 93);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (103, 'Y', current_timestamp, 1, current_timestamp, 1, 34, 94);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (104, 'Y', current_timestamp, 1, current_timestamp, 1, 34, 95);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (105, 'Y', current_timestamp, 1, current_timestamp, 1, 34, 96);

--Billing
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (106, 'Y', current_timestamp, 1, current_timestamp, 1, 6, 97);

--Department Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (107, 'Y', current_timestamp, 1, current_timestamp, 1, 42, 98);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (108, 'Y', current_timestamp, 1, current_timestamp, 1, 42, 99);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (109, 'Y', current_timestamp, 1, current_timestamp, 1, 42, 100);

--Sub Department Resource
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (110, 'Y', current_timestamp, 1, current_timestamp, 1, 43, 101);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (111, 'Y', current_timestamp, 1, current_timestamp, 1, 43, 102);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id)
VALUES (112, 'Y', current_timestamp, 1, current_timestamp, 1, 43, 103);

--Pharmacy
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (113, 'Y', '2019-02-14', 1, '2019-02-14', 1,44,104);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (114, 'Y', '2019-02-14', 1, '2019-02-14', 1,46,105);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (115, 'Y', '2019-02-14', 1, '2019-02-14', 1,46,106);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (116, 'Y', '2019-02-14', 1, '2019-02-14', 1,46,107);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (117, 'Y', '2019-02-14', 1, '2019-02-14', 1,46,108);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (118, 'Y', '2019-02-14', 1, '2019-02-14', 1,47,109);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (119, 'Y', '2019-02-14', 1, '2019-02-14', 1,47,110);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (120, 'Y', '2019-02-14', 1, '2019-02-14', 1,47,111);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (121, 'Y', '2019-02-14', 1, '2019-02-14', 1,47,112);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (122, 'Y', '2019-02-14', 1, '2019-02-14', 1,47,113);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (123, 'Y', '2019-02-14', 1, '2019-02-14', 1,48,114);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (124, 'Y', '2019-02-14', 1, '2019-02-14', 1,48,115);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (125, 'Y', '2019-02-14', 1, '2019-02-14', 1,48,116);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (126, 'Y', '2019-02-14', 1, '2019-02-14', 1,48,117);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (127, 'Y', '2019-02-14', 1, '2019-02-14', 1,49,118);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (128, 'Y', '2019-02-14', 1, '2019-02-14', 1,49,119);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (129, 'Y', '2019-02-14', 1, '2019-02-14', 1,51,120);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (130, 'Y', '2019-02-14', 1, '2019-02-14', 1,51,121);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (131, 'Y', '2019-02-14', 1, '2019-02-14', 1,51,122);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (132, 'Y', '2019-02-14', 1, '2019-02-14', 1,51,123);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (133, 'Y', '2019-02-14', 1, '2019-02-14', 1,51,124);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (134, 'Y', '2019-02-14', 1, '2019-02-14', 1,51,125);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (135, 'Y', '2019-02-14', 1, '2019-02-14', 1,51,126);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (136, 'Y', '2019-02-14', 1, '2019-02-14', 1,52,127);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (137, 'Y', '2019-02-14', 1, '2019-02-14', 1,52,128);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (138, 'Y', '2019-02-14', 1, '2019-02-14', 1,52,129);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (139, 'Y', '2019-02-14', 1, '2019-02-14', 1,53,130);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (140, 'Y', '2019-02-14', 1, '2019-02-14', 1,53,131);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (141, 'Y', '2019-02-14', 1, '2019-02-14', 1,53,132);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (142, 'Y', '2019-02-14', 1, '2019-02-14', 1,53,133);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (143, 'Y', '2019-02-14', 1, '2019-02-14', 1,54,134);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (144, 'Y', '2019-02-14', 1, '2019-02-14', 1,54,135);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (145, 'Y', '2019-02-14', 1, '2019-02-14', 1,56,136);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (146, 'Y', '2019-02-14', 1, '2019-02-14', 1,56,137);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (147, 'Y', '2019-02-14', 1, '2019-02-14', 1,56,138);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (148, 'Y', '2019-02-14', 1, '2019-02-14', 1,56,139);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (149, 'Y', '2019-02-14', 1, '2019-02-14', 1,56,140);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (150, 'Y', '2019-02-14', 1, '2019-02-14', 1,57,141);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (151, 'Y', '2019-02-14', 1, '2019-02-14', 1,57,142);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (152, 'Y', '2019-02-14', 1, '2019-02-14', 1,57,143);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (153, 'Y', '2019-02-14', 1, '2019-02-14', 1,57,144);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (154, 'Y', '2019-02-14', 1, '2019-02-14', 1,58,145);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (155, 'Y', '2019-02-14', 1, '2019-02-14', 1,58,146);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (156, 'Y', '2019-02-14', 1, '2019-02-14', 1,58,147);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (157, 'Y', '2019-02-14', 1, '2019-02-14', 1,59,148);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (158, 'Y', '2019-02-14', 1, '2019-02-14', 1,59,149);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (159, 'Y', '2019-02-14', 1, '2019-02-14', 1,59,150);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (160, 'Y', '2019-02-14', 1, '2019-02-14', 1,59,151);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (161, 'Y', '2019-02-14', 1, '2019-02-14', 1,60,152);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (162, 'Y', '2019-02-14', 1, '2019-02-14', 1,60,153);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (163, 'Y', '2019-02-14', 1, '2019-02-14', 1,60,154);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (164, 'Y', '2019-02-14', 1, '2019-02-14', 1,61,155);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (165, 'Y', '2019-02-14', 1, '2019-02-14', 1,62,156);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (166, 'Y', '2019-02-14', 1, '2019-02-14', 1,62,157);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (167, 'Y', '2019-02-14', 1, '2019-02-14', 1,64,158);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (168, 'Y', '2019-02-14', 1, '2019-02-14', 1,64,159);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (169, 'Y', '2019-02-14', 1, '2019-02-14', 1,64,160);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (170, 'Y', '2019-02-14', 1, '2019-02-14', 1,65,161);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (171, 'Y', '2019-02-14', 1, '2019-02-14', 1,65,162);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (172, 'Y', '2019-02-14', 1, '2019-02-14', 1,65,163);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (173, 'Y', '2019-02-14', 1, '2019-02-14', 1,66,164);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (174, 'Y', '2019-02-14', 1, '2019-02-14', 1,66,165);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (175, 'Y', '2019-02-14', 1, '2019-02-14', 1,66,166);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (176, 'Y', '2019-02-14', 1, '2019-02-14', 1,68,167);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (177, 'Y', '2019-02-14', 1, '2019-02-14', 1,68,168);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (178, 'Y', '2019-02-14', 1, '2019-02-14', 1,68,169);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (179, 'Y', '2019-02-14', 1, '2019-02-14', 1,68,170);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (180, 'Y', '2019-02-14', 1, '2019-02-14', 1,68,171);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (181, 'Y', '2019-02-14', 1, '2019-02-14', 1,69,172);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (182, 'Y', '2019-02-14', 1, '2019-02-14', 1,69,173);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (183, 'Y', '2019-02-14', 1, '2019-02-14', 1,70,174);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (184, 'Y', '2019-02-14', 1, '2019-02-14', 1,70,175);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (185, 'Y', '2019-02-14', 1, '2019-02-14', 1,70,176);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (186, 'Y', '2019-02-14', 1, '2019-02-14', 1,71,177);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (187, 'Y', '2019-02-14', 1, '2019-02-14', 1,71,178);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (188, 'Y', '2019-02-14', 1, '2019-02-14', 1,71,179);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (189, 'Y', '2019-02-14', 1, '2019-02-14', 1,72,180);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (190, 'Y', '2019-02-14', 1, '2019-02-14', 1,72,181);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (191, 'Y', '2019-02-14', 1, '2019-02-14', 1,72,182);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (192, 'Y', '2019-02-14', 1, '2019-02-14', 1,73,183);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (193, 'Y', '2019-02-14', 1, '2019-02-14', 1,73,184);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (194, 'Y', '2019-02-14', 1, '2019-02-14', 1,73,185);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (195, 'Y', '2019-02-14', 1, '2019-02-14', 1,74,186);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (196, 'Y', '2019-02-14', 1, '2019-02-14', 1,75,187);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (197, 'Y', '2019-02-14', 1, '2019-02-14', 1,76,188);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (198, 'Y', '2019-02-14', 1, '2019-02-14', 1,76,189);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (199, 'Y', '2019-02-14', 1, '2019-02-14', 1,77,190);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (200, 'Y', '2019-02-14', 1, '2019-02-14', 1,77,191);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (201, 'Y', '2019-02-14', 1, '2019-02-14', 1,79,192);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (202, 'Y', '2019-02-14', 1, '2019-02-14', 1,79,193);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (203, 'Y', '2019-02-14', 1, '2019-02-14', 1,80,194);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (204, 'Y', '2019-02-14', 1, '2019-02-14', 1,80,195);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (205, 'Y', '2019-02-14', 1, '2019-02-14', 1,80,196);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (206, 'Y', '2019-02-14', 1, '2019-02-14', 1,81,197);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (207, 'Y', '2019-02-14', 1, '2019-02-14', 1,81,198);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (208, 'Y', '2019-02-14', 1, '2019-02-14', 1,81,199);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (209, 'Y', '2019-02-14', 1, '2019-02-14', 1,82,200);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (210, 'Y', '2019-02-14', 1, '2019-02-14', 1,82,201);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (211, 'Y', '2019-02-14', 1, '2019-02-14', 1,82,202);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (212, 'Y', '2019-02-14', 1, '2019-02-14', 1,83,203);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (213, 'Y', '2019-02-14', 1, '2019-02-14', 1,83,204);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (214, 'Y', '2019-02-14', 1, '2019-02-14', 1,83,205);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (215, 'Y', '2019-02-14', 1, '2019-02-14', 1,84,206);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (216, 'Y', '2019-02-14', 1, '2019-02-14', 1,84,207);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (217, 'Y', '2019-02-14', 1, '2019-02-14', 1,84,208);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (218, 'Y', '2019-02-14', 1, '2019-02-14', 1,85,209);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (219, 'Y', '2019-02-14', 1, '2019-02-14', 1,85,210);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (220, 'Y', '2019-02-14', 1, '2019-02-14', 1,85,211);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (221, 'Y', '2019-02-14', 1, '2019-02-14', 1,86,212);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (222, 'Y', '2019-02-14', 1, '2019-02-14', 1,76,213);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (223, 'Y', '2019-02-14', 1, '2019-02-14', 1,76,214);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (224, 'Y', '2019-02-14', 1, '2019-02-14', 1,87,215);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (225, 'Y', '2019-02-14', 1, '2019-02-14', 1,87,216);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (226, 'Y', '2019-02-14', 1, '2019-02-14', 1,87,217);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (227, 'Y', '2019-02-14', 1, '2019-02-14', 1,88,218);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (228, 'Y', '2019-02-14', 1, '2019-02-14', 1,88,219);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (229, 'Y', '2019-02-14', 1, '2019-02-14', 1,88,220);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (230, 'Y', '2019-02-14', 1, '2019-02-14', 1,89,221);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (231, 'Y', '2019-02-14', 1, '2019-02-14', 1,89,222);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (232, 'Y', '2019-02-14', 1, '2019-02-14', 1,89,223);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (233, 'Y', '2019-02-14', 1, '2019-02-14', 1,90,224);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (234, 'Y', '2019-02-14', 1, '2019-02-14', 1,90,225);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (235, 'Y', '2019-02-14', 1, '2019-02-14', 1,90,226);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (236, 'Y', '2019-02-14', 1, '2019-02-14', 1,91,227);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (237, 'Y', '2019-02-14', 1, '2019-02-14', 1,91,228);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (238, 'Y', '2019-02-14', 1, '2019-02-14', 1,91,229);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (239, 'Y', '2019-02-14', 1, '2019-02-14', 1,92,230);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (240, 'Y', '2019-02-14', 1, '2019-02-14', 1,92,231);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (241, 'Y', '2019-02-14', 1, '2019-02-14', 1,92,232);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (242, 'Y', '2019-02-14', 1, '2019-02-14', 1,93,233);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (243, 'Y', '2019-02-14', 1, '2019-02-14', 1,93,234);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (244, 'Y', '2019-02-14', 1, '2019-02-14', 1,93,235);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (245, 'Y', '2019-02-14', 1, '2019-02-14', 1,94,236);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (246, 'Y', '2019-02-14', 1, '2019-02-14', 1,94,237);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (247, 'Y', '2019-02-14', 1, '2019-02-14', 1,94,238);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (248, 'Y', '2019-02-14', 1, '2019-02-14', 1,95,239);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (249, 'Y', '2019-02-14', 1, '2019-02-14', 1,95,240);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (250, 'Y', '2019-02-14', 1, '2019-02-14', 1,95,241);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (251, 'Y', '2019-02-14', 1, '2019-02-14', 1,96,242);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (252, 'Y', '2019-02-14', 1, '2019-02-14', 1,96,243);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (253, 'Y', '2019-02-14', 1, '2019-02-14', 1,96,244);
INSERT INTO menu_resource (id, active, created_date, created_by, last_modified, modified_by, menu_id, resource_id) VALUES (254, 'Y', '2019-02-14', 1, '2019-02-14', 1,97,245);

--Users
--His
INSERT INTO users ( id, user_name, jhi_password, effective_from, ldap_id, active, created_date, created_by, last_modified, modified_by)
VALUES (1, 'raster', 'raster', current_timestamp, 'uid=raster,ou=users', 'Y', current_timestamp, NULL, current_timestamp, NULL);
--Pharmacy
INSERT INTO users ( id, user_name, jhi_password, effective_from, ldap_id, active, created_date, created_by, last_modified, modified_by)
VALUES (2, 'pharmacy', 'pharmacy', '2017-07-17 15:24:13.284', 'uid=pharmacy,ou=users', 'Y', NULL, NULL, NULL, NULL);

--User role
--His
INSERT INTO users_role (id, ldap_user_id, ldap_role_id, active, created_date, created_by, last_modified, modified_by, users_id, role_id)
VALUES (1, NULL, NULL, 'Y', current_timestamp, 1, current_timestamp, 1, 1, 1);
--Pharmacy
INSERT INTO users_role (id, ldap_user_id, ldap_role_id, active, created_date, created_by, last_modified, modified_by, users_id, role_id)
VALUES (2, NULL, NULL, 'Y', '2019-02-14', 1, '2019-02-14', 1, 2, 2);



