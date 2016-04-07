-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema health_care
--

CREATE DATABASE IF NOT EXISTS health_care;
USE health_care;

--
-- Definition of table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `appoint_id` int(10) unsigned NOT NULL auto_increment,
  `patient_id` int(10) unsigned default '0',
  `doctor_id` int(10) unsigned default NULL,
  `doctor_sch_id` int(10) unsigned default NULL,
  `appoint_date` date default NULL,
  `is_visited` int(10) unsigned default NULL,
  `role` int(10) unsigned default NULL,
  PRIMARY KEY  USING BTREE (`appoint_id`),
  KEY `FK_appointment_patient` (`patient_id`),
  KEY `FK_appointment_doctor` (`doctor_id`),
  KEY `FK_appointment_doc_Sche` (`doctor_sch_id`),
  CONSTRAINT `FK_appointment_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor_info` (`doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_appointment_doc_Sche` FOREIGN KEY (`doctor_sch_id`) REFERENCES `doctor_schedule` (`doctor_sch_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_appointment_patient` FOREIGN KEY (`patient_id`) REFERENCES `patient_info` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment`
--

/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` (`appoint_id`,`patient_id`,`doctor_id`,`doctor_sch_id`,`appoint_date`,`is_visited`,`role`) VALUES 
 (1,2013091501,1,1,'2013-10-09',NULL,NULL),
 (2,2013100610,1,2,'2013-11-17',NULL,NULL),
 (3,2013100613,2,1,'2013-11-07',NULL,NULL),
 (4,2013100614,5,2,'2013-11-07',NULL,NULL),
 (5,2013100615,3,1,'2013-11-07',NULL,NULL),
 (6,2013100617,5,1,'2013-11-07',NULL,NULL),
 (7,2013100618,4,1,'2013-11-07',NULL,NULL),
 (8,2013100619,5,2,'2013-11-07',NULL,NULL),
 (9,2013100621,6,1,'2013-11-07',NULL,NULL),
 (10,2013100623,6,1,'2013-11-07',NULL,NULL),
 (11,2013100624,5,2,'2013-11-07',NULL,NULL);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;


--
-- Definition of table `diagnostic_laboratory`
--

DROP TABLE IF EXISTS `diagnostic_laboratory`;
CREATE TABLE `diagnostic_laboratory` (
  `diagnostic_id` int(10) unsigned NOT NULL auto_increment,
  `diag_name` varchar(45) NOT NULL,
  `address` varchar(245) NOT NULL,
  `reg_date` varchar(45) NOT NULL,
  `test_list` varchar(1545) default NULL,
  `govt_reg_no` int(10) unsigned NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY  (`diagnostic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `diagnostic_laboratory`
--

/*!40000 ALTER TABLE `diagnostic_laboratory` DISABLE KEYS */;
INSERT INTO `diagnostic_laboratory` (`diagnostic_id`,`diag_name`,`address`,`reg_date`,`test_list`,`govt_reg_no`,`password`) VALUES 
 (20130001,'Popular Diagnostics Ltd','23, Kazi Nazrul Islam Avenue, Kawran Bazar, Dhaka','2013-10-07','WAPS,Urine,Stool,ECG,Eco Cardiography,Mamography',4521980,'123'),
 (20130002,'United Laboratories Ltd','167, Rajuk Avenue, Dilkusha, Dhaka','2013-10-05','Titenus, Cancer, Blood, Urine, Brain, ECG',98231,'124');
/*!40000 ALTER TABLE `diagnostic_laboratory` ENABLE KEYS */;


--
-- Definition of table `diagonosis`
--

DROP TABLE IF EXISTS `diagonosis`;
CREATE TABLE `diagonosis` (
  `diag_id` int(10) unsigned NOT NULL auto_increment,
  `patient_id` int(10) unsigned default NULL,
  `doctor_id` int(10) unsigned default NULL,
  `diag_date` date default NULL,
  PRIMARY KEY  (`diag_id`),
  KEY `FK_diagonosis_doctor` (`doctor_id`),
  KEY `FK_diagonosis_patient` (`patient_id`),
  CONSTRAINT `FK_diagonosis_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor_info` (`doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_diagonosis_patient` FOREIGN KEY (`patient_id`) REFERENCES `patient_info` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `diagonosis`
--

/*!40000 ALTER TABLE `diagonosis` DISABLE KEYS */;
INSERT INTO `diagonosis` (`diag_id`,`patient_id`,`doctor_id`,`diag_date`) VALUES 
 (1,2013091502,1,'2013-09-30'),
 (2,2013091502,1,'2013-09-30'),
 (3,2013091502,1,'2013-09-30'),
 (4,2013091501,1,'2013-09-30'),
 (5,2013091502,1,'2013-09-30'),
 (6,2013091501,1,'2013-09-30'),
 (7,2013091502,1,'2013-09-30'),
 (8,2013100614,1,'2013-11-07'),
 (9,2013100615,7,'2013-11-07'),
 (10,2013100616,3,'2013-11-07'),
 (11,2013100617,4,'2013-11-07'),
 (12,2013100618,4,'2013-11-07'),
 (13,2013100619,5,'2013-11-07'),
 (14,2013100624,5,'2013-11-05');
/*!40000 ALTER TABLE `diagonosis` ENABLE KEYS */;


--
-- Definition of table `diagonosis_line`
--

DROP TABLE IF EXISTS `diagonosis_line`;
CREATE TABLE `diagonosis_line` (
  `diag_line_id` int(10) unsigned NOT NULL auto_increment,
  `diag_id` int(10) unsigned default NULL,
  `test_id` int(10) unsigned default NULL,
  `report` varchar(45) default NULL,
  `test_date` date default NULL,
  `report_date` date default NULL,
  PRIMARY KEY  (`diag_line_id`),
  KEY `FK_diagonosis_line_diagonosis` (`diag_id`),
  KEY `FK_diagonosis_line_test` (`test_id`),
  CONSTRAINT `FK_diagonosis_line_diagonosis` FOREIGN KEY (`diag_id`) REFERENCES `diagonosis` (`diag_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_diagonosis_line_test` FOREIGN KEY (`test_id`) REFERENCES `test` (`test_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `diagonosis_line`
--

/*!40000 ALTER TABLE `diagonosis_line` DISABLE KEYS */;
INSERT INTO `diagonosis_line` (`diag_line_id`,`diag_id`,`test_id`,`report`,`test_date`,`report_date`) VALUES 
 (1,6,2,'Not Good','2013-11-05','2013-11-05'),
 (2,6,3,'Good','2013-11-01','2013-11-01'),
 (3,6,4,'Less of himoglobin','2013-10-06','2013-10-06'),
 (4,7,2,'Not Good','2013-11-05','2013-11-05'),
 (5,7,3,'Good','2013-11-01','2013-11-01'),
 (6,8,2,'Not Good','2013-11-05','2013-11-05'),
 (7,9,2,'Not Good','2013-11-05','2013-11-05'),
 (8,9,2,'Not Good','2013-11-05','2013-11-05'),
 (9,10,2,'Not Good','2013-11-05','2013-11-05'),
 (10,10,3,'Good','2013-11-01','2013-11-01'),
 (11,10,3,'Good','2013-11-01','2013-11-01'),
 (12,10,3,'Good','2013-11-01','2013-11-01'),
 (13,11,8,NULL,NULL,NULL),
 (14,11,2,'Not Good','2013-11-05','2013-11-05'),
 (15,11,6,'Bod whet','2013-11-05','2013-11-05'),
 (16,11,5,'Normal','2013-11-05','2013-11-05'),
 (17,12,6,'Bod whet','2013-11-05','2013-11-05'),
 (18,13,2,'Not Good','2013-11-05','2013-11-05'),
 (19,13,4,NULL,NULL,NULL),
 (20,14,19,'negative.','2013-11-05','2013-11-05'),
 (21,14,5,'Normal','2013-11-05','2013-11-05');
/*!40000 ALTER TABLE `diagonosis_line` ENABLE KEYS */;


--
-- Definition of table `doctor_assistant`
--

DROP TABLE IF EXISTS `doctor_assistant`;
CREATE TABLE `doctor_assistant` (
  `doctor_assistant_id` int(10) unsigned NOT NULL auto_increment,
  `assistant_name` varchar(45) NOT NULL,
  `mobile#` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `doctor_id` int(10) unsigned NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY  (`doctor_assistant_id`),
  KEY `FK_doctor_assistant_1` (`doctor_id`),
  CONSTRAINT `FK_doctor_assistant_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor_info` (`doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_assistant`
--

/*!40000 ALTER TABLE `doctor_assistant` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor_assistant` ENABLE KEYS */;


--
-- Definition of table `doctor_department`
--

DROP TABLE IF EXISTS `doctor_department`;
CREATE TABLE `doctor_department` (
  `department_id` int(10) unsigned NOT NULL auto_increment,
  `department_name` varchar(45) NOT NULL,
  PRIMARY KEY  (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_department`
--

/*!40000 ALTER TABLE `doctor_department` DISABLE KEYS */;
INSERT INTO `doctor_department` (`department_id`,`department_name`) VALUES 
 (1,'ENT'),
 (2,'Oncology'),
 (3,'Gastroenterology'),
 (4,'Nephrology'),
 (5,'Gynaecology & Obstetrics'),
 (6,'Cardiology'),
 (7,'Orthopedic'),
 (8,'Psychiatry'),
 (9,'Ophthalmology'),
 (10,'Dentistry'),
 (11,'Anesthesiology'),
 (12,'Clinical Biochemistry'),
 (13,'Dermatology & Venereology'),
 (14,'Endocrinology & Metabolism'),
 (15,'Hematology'),
 (16,'Hepatology'),
 (17,'Medicine'),
 (18,'Neonatology'),
 (19,'Urology'),
 (20,'Pediatrics'),
 (21,'Neurology'),
 (22,'Child Health'),
 (23,'Surgery');
/*!40000 ALTER TABLE `doctor_department` ENABLE KEYS */;


--
-- Definition of table `doctor_info`
--

DROP TABLE IF EXISTS `doctor_info`;
CREATE TABLE `doctor_info` (
  `doctor_id` int(10) unsigned NOT NULL auto_increment,
  `doctor_fname` varchar(45) NOT NULL,
  `doctor_lname` varchar(45) NOT NULL,
  `doctor_gender` varchar(10) NOT NULL,
  `doctor_dob` date default NULL,
  `doctor_address` varchar(100) default NULL,
  `doctor_email` varchar(45) default NULL,
  `doctor_cell` varchar(45) default NULL,
  `doctor_designation` varchar(45) default NULL,
  `qualification` varchar(45) default NULL,
  `department_id` int(10) unsigned default NULL,
  `doctor_photo` longblob,
  PRIMARY KEY  (`doctor_id`),
  KEY `FK_doctor_info_dept` (`department_id`),
  CONSTRAINT `FK_doctor_info_dept` FOREIGN KEY (`department_id`) REFERENCES `doctor_department` (`department_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_info`
--

/*!40000 ALTER TABLE `doctor_info` DISABLE KEYS */;
INSERT INTO `doctor_info` (`doctor_id`,`doctor_fname`,`doctor_lname`,`doctor_gender`,`doctor_dob`,`doctor_address`,`doctor_email`,`doctor_cell`,`doctor_designation`,`qualification`,`department_id`,`doctor_photo`) VALUES 
 (1,'Abul','Kasem','Male','1975-01-14','Dhaka','asdf@gmail.com','01741214578','Associate Professor','MBBS, FCPS',2,NULL),
 (2,'Rana','Boumik','Male','1968-02-15','82, Nayapaltan, Dhaka-1000','rana@yahoo.com','01745825652','Professor','MBBS,FCPS',1,NULL),
 (3,'Galeb','Chowdhury','Male','1974-02-12','Dhaka','galeb@yah00.com','01745487454','Associate Professor','MBBS',20,NULL),
 (4,'Sumi','Khan','Female','1980-12-04','Dhaka','sumi@yahoo.com','01741321456','Assistant Professor','MBBS',1,NULL),
 (5,'Arnob','Cakraborty','Male','1988-08-23','Dhruba, Sugandha R/A, Chittagong','drgc@live.com','01911483672','Surgeon','MBBS, DCH',23,NULL),
 (6,'Hamid','Hossain','Male','1981-04-14','Khulna','hamidh@gmail.com','01532678450','Lecturer','MBBS, FRCS',16,NULL),
 (7,'Badrul','Ahsan','Male','1979-11-25','24, Court Road, Kotoaly, Ctg','bahsan@gmail.com','01679324225','Professor','MBBS, DCH, FRCS',10,NULL),
 (8,'Halima','Khatun','Female','1986-10-22','104, Jhonson Road, Dhaka-1100','halimakh@popularlaboratories.com','01815754689','Assistant Professor','MBBS, FCPS',4,NULL);
/*!40000 ALTER TABLE `doctor_info` ENABLE KEYS */;


--
-- Definition of table `doctor_schedule`
--

DROP TABLE IF EXISTS `doctor_schedule`;
CREATE TABLE `doctor_schedule` (
  `doctor_sch_id` int(10) unsigned NOT NULL auto_increment,
  `doctor_id` int(10) unsigned default NULL,
  `doctor_sch_time` varchar(30) default NULL,
  `doctor_sch_day` varchar(45) default NULL,
  `doctor_fee` int(10) unsigned default NULL,
  PRIMARY KEY  (`doctor_sch_id`),
  KEY `FK_doctor_schedule_doctor` (`doctor_id`),
  CONSTRAINT `FK_doctor_schedule_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor_info` (`doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_schedule`
--

/*!40000 ALTER TABLE `doctor_schedule` DISABLE KEYS */;
INSERT INTO `doctor_schedule` (`doctor_sch_id`,`doctor_id`,`doctor_sch_time`,`doctor_sch_day`,`doctor_fee`) VALUES 
 (1,1,'4PM-8PM','Friday',500),
 (2,5,'9AM-12PM','Monday',500),
 (3,3,'9.0AM-1.0PM','Saturday',600),
 (4,2,'2.0pm-8.0pm','Monday',500),
 (5,3,'2.0pm-8.0pm','Tuesday',500),
 (6,4,'2.0pm-8.0pm','Wednesday',500),
 (7,8,'9.0AM-1.0PM','Sunday',500);
/*!40000 ALTER TABLE `doctor_schedule` ENABLE KEYS */;


--
-- Definition of table `medicine_group`
--

DROP TABLE IF EXISTS `medicine_group`;
CREATE TABLE `medicine_group` (
  `med_group_id` int(10) unsigned NOT NULL auto_increment,
  `med_group_name` varchar(45) default NULL,
  `med_group_description` varchar(400) default NULL,
  PRIMARY KEY  (`med_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicine_group`
--

/*!40000 ALTER TABLE `medicine_group` DISABLE KEYS */;
INSERT INTO `medicine_group` (`med_group_id`,`med_group_name`,`med_group_description`) VALUES 
 (1,'Paracetamol','Paracetamol'),
 (2,'Rabeprazole','Rabeprazole Sodium'),
 (3,'Amlodipine','Amlodipine + Benazepril Hydrochloride'),
 (4,'Dexibuprofen','Dexibuprofen'),
 (5,'Vitamin B complex','Nicotinamide +Pyridoxine Hydrochloride + Riboflavin + Vitamin B1'),
 (6,'Multi-Vitamin & Mineral','Ascorbic Acid+ Calcium Pantothenate+ Cupric Sulphate+ Cyanocobalamin+ Ferrous Sulphate+Folic Acid'),
 (7,'Cetirizine','Cetirizine Dihydrochloride'),
 (8,'Fexofenadine','Fexofenadine Hydrochloride'),
 (9,'Azithromycin','Azithromycin'),
 (10,'Fluticasone','Fluticasone Propionate+ Salmeterol'),
 (11,'Calcium','Calcium Carbonate'),
 (12,'Cephradine','Cephradine'),
 (13,'Tetracycline','Tetracycline Hydrochloride'),
 (14,'Ampicillin','Ampicillin'),
 (15,'Dextrose','Dextrose'),
 (16,'Diclofenac','Diclofenac Sodium'),
 (17,'Econazole','Econazole Nitrate+ Triamcinolone Acetonide'),
 (18,'Zaleplone','Zaleplone'),
 (19,'Levofloxacin','Levofloxacin'),
 (20,'Mefenamide','Mefenamic Acid '),
 (21,'Metronidazole','Metronidazole'),
 (22,'Gentamicin','Gentamicin'),
 (23,'Lidocaine','Lidocaine Hydrochloride'),
 (24,'Sulphamethoxazole','Sulphamethoxazole+ Trimethoprim'),
 (25,'Ranitidine','Ranitidine'),
 (26,'Lisinopril','Lisinopril'),
 (27,'Aspirin','Aspirin+ Clopidogrel '),
 (28,'Fluconazole','Fluconazole'),
 (29,'Omeprazole','Omeprazole'),
 (30,'Salicylide','Salicylic Acid'),
 (31,'Polyethylene Glycol','Polyethylene Glycol+ Propylene Glycol'),
 (32,'Oxybutynin','Oxybutynin Hydrochloride'),
 (33,'Aceclofenac','Aceclofenac'),
 (34,'Pancreatin','Pancreatin'),
 (35,'Ketoprofen','Ketoprofen'),
 (37,'Lacidipine','Lacidipine'),
 (38,'Salbutamol ','Salbutamol '),
 (39,'Levamisole','Levamisole'),
 (40,'Diazepam','Diazepam'),
 (41,'Pheniramine','Pheniramine'),
 (42,'Piracetam','Piracetam'),
 (43,'Ketoconazole','Ketoconazole'),
 (44,'Flunarizine','Flunarizine'),
 (45,'Ambroxol','Ambroxol'),
 (46,'Thiopentone','Thiopentone Sodium'),
 (47,'Promethazine','Promethazine Hydrochloride'),
 (48,'Betamethasone','Betamethasone'),
 (49,'Bisoprolol','Bisoprolol Hemifumarate'),
 (50,'Procyclidine','Procyclidine Hydrochloride'),
 (51,'Loratadine','Loratadine'),
 (52,'Naproxen','Naproxen'),
 (53,'Griseofulvin','Griseofulvin'),
 (54,'Chlorhexidine Gluconate','Chlorhexidine Gluconate'),
 (55,'Disposable Syringe','Disposable Syringe'),
 (56,'Valsartan ','Valsartan '),
 (57,'Cefixime','Cefixime'),
 (58,'Ketorolac','Ketorolac'),
 (59,'Zinc','Zinc');
/*!40000 ALTER TABLE `medicine_group` ENABLE KEYS */;


--
-- Definition of table `medicine_list`
--

DROP TABLE IF EXISTS `medicine_list`;
CREATE TABLE `medicine_list` (
  `med_id` int(10) unsigned NOT NULL auto_increment,
  `med_group_id` int(10) unsigned NOT NULL,
  `med_type_id` int(10) unsigned NOT NULL,
  `med_name` varchar(45) NOT NULL,
  `vendor_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`med_id`),
  KEY `FK_medicine_list_1` (`vendor_id`),
  KEY `FK_medicine_list_2` (`med_group_id`),
  KEY `FK_medicine_list_3` (`med_type_id`),
  CONSTRAINT `FK_medicine_list_1` FOREIGN KEY (`vendor_id`) REFERENCES `vendor_list` (`vendor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_medicine_list_2` FOREIGN KEY (`med_group_id`) REFERENCES `medicine_group` (`med_group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_medicine_list_3` FOREIGN KEY (`med_type_id`) REFERENCES `medicine_type` (`med_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicine_list`
--

/*!40000 ALTER TABLE `medicine_list` DISABLE KEYS */;
INSERT INTO `medicine_list` (`med_id`,`med_group_id`,`med_type_id`,`med_name`,`vendor_id`) VALUES 
 (1,2,1,' 	ACIFIX',29),
 (2,1,1,'NapaPlus',29),
 (3,1,4,'AcePlus',1),
 (4,5,4,'Aristoplex',29),
 (5,3,1,'Amdocal PLUS',29),
 (6,6,1,'Aristovit M',29),
 (7,7,4,'Atrizin',29),
 (8,8,16,'Axodin',29),
 (9,9,2,'Azithrocin',29),
 (10,18,2,'Eplon 10',29),
 (11,26,1,'Neopril 20',29),
 (12,23,3,'Xylobex 2% ',29),
 (13,19,1,'Evo 500 ',29),
 (14,22,10,'Gentosep',29),
 (15,4,1,'A - Flam 400',1),
 (16,16,1,'Decafen 50',2),
 (17,15,20,'Saline R',2),
 (18,27,1,'Combiplat ',37),
 (19,6,1,'Alion',37),
 (20,11,1,'Protebon 500',37),
 (21,25,1,'Gastroloc 150 ',37),
 (22,21,21,'Varizil 500',33),
 (23,12,3,'Dicef 250',33),
 (24,8,1,'Fexofast 180',33),
 (25,10,22,'Flutiderm 0.005%',33),
 (26,37,1,'L-Cardin 4 ',33),
 (27,7,4,'Cetirizine',34),
 (28,38,1,'Salbutamol 4',34),
 (29,39,1,'Levamisole 40',34),
 (30,40,1,'Diazepam 5',34),
 (31,41,4,'Amarin',16),
 (32,28,2,'Flucon 50',16),
 (33,35,2,'Ketop 200',16),
 (34,44,1,'Minium 10 ',16),
 (35,45,4,'Ambolyt',8),
 (36,46,3,'Anestho IV 0.5 gm',8),
 (37,47,4,'Antagon',8),
 (38,48,22,'Betaderm',8),
 (39,49,1,' 	Bisopro 2.5 ',8),
 (40,50,3,'Cyclid',8),
 (41,51,16,'Alaron',3),
 (42,21,16,'Amotrex ',3),
 (43,52,14,'Anaflex 500',3),
 (44,53,1,'Fulcinex',3),
 (45,54,23,'Hexisol',3),
 (46,55,24,'Disposable Syringe 5 ',10),
 (47,55,24,'Disposable Syringe 1',10),
 (48,6,25,'Atoz Junior ',19),
 (49,56,1,'V-Sart ',19),
 (50,57,2,'Rofixim 200',19),
 (51,58,3,'Toradolin 30',19),
 (52,9,2,'Xolide 500',19),
 (53,15,20,'ORSaline N ',25),
 (54,15,20,'Ors Saline',25),
 (55,59,1,'SMC Zinc 20',25);
/*!40000 ALTER TABLE `medicine_list` ENABLE KEYS */;


--
-- Definition of table `medicine_purchase`
--

DROP TABLE IF EXISTS `medicine_purchase`;
CREATE TABLE `medicine_purchase` (
  `med_pur_id` int(10) unsigned NOT NULL auto_increment,
  `med_pur_date` date default NULL,
  `med_pur_amount` int(11) default NULL,
  `med_pur_ref` varchar(100) default NULL,
  PRIMARY KEY  (`med_pur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicine_purchase`
--

/*!40000 ALTER TABLE `medicine_purchase` DISABLE KEYS */;
INSERT INTO `medicine_purchase` (`med_pur_id`,`med_pur_date`,`med_pur_amount`,`med_pur_ref`) VALUES 
 (1,'2012-10-10',20,'aa'),
 (2,'2012-10-10',52,'aa'),
 (3,'2012-10-10',52,'aa'),
 (4,'2012-10-10',52,'aa'),
 (5,'2012-10-10',52,'aa'),
 (6,'2012-10-10',52,'aa'),
 (7,'2012-10-10',52,'aa'),
 (8,'2012-10-10',52,'aa'),
 (9,'2012-10-10',52,'aa'),
 (10,'2012-10-10',5412,'aa'),
 (11,'2012-10-10',5412,'aa'),
 (12,'2012-10-10',5412,'aa'),
 (13,'2012-10-10',48,'aa'),
 (14,'2012-10-10',48,'aa'),
 (15,'2012-10-10',48,'aa'),
 (16,'2012-10-10',48,'aa'),
 (17,'2012-10-10',48,'aa'),
 (18,'2012-10-10',48,'aa'),
 (19,'2012-10-10',48,'aa'),
 (20,'2012-10-10',48,'aa'),
 (21,'2012-10-10',1536,'aa'),
 (22,'2012-10-10',60,'aa'),
 (23,'2012-10-10',5964,'aa'),
 (24,'2013-10-10',0,''),
 (25,'2013-10-10',92,''),
 (26,'2013-10-10',92,''),
 (27,'2013-10-10',92,''),
 (28,'2013-11-05',540,'Rana');
/*!40000 ALTER TABLE `medicine_purchase` ENABLE KEYS */;


--
-- Definition of table `medicine_purchase_line`
--

DROP TABLE IF EXISTS `medicine_purchase_line`;
CREATE TABLE `medicine_purchase_line` (
  `med_pur_line_id` int(10) unsigned NOT NULL auto_increment,
  `med_pur_id` int(10) unsigned default NULL,
  `med_id` int(10) unsigned default NULL,
  `med_quantity` int(10) unsigned default NULL,
  `vendor_id` int(10) unsigned default NULL,
  `med_pur_price` double default NULL,
  PRIMARY KEY  (`med_pur_line_id`),
  KEY `FK_medicine_purchase_line_id` (`med_pur_id`),
  KEY `FK_medicine_purchase_line_med_id` (`med_id`),
  KEY `FK_medicine_purchase_line_vendor_id` (`vendor_id`),
  CONSTRAINT `FK_medicine_purchase_line_id` FOREIGN KEY (`med_pur_id`) REFERENCES `medicine_purchase` (`med_pur_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_medicine_purchase_line_med_id` FOREIGN KEY (`med_id`) REFERENCES `medicine_list` (`med_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_medicine_purchase_line_vendor_id` FOREIGN KEY (`vendor_id`) REFERENCES `vendor_list` (`vendor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicine_purchase_line`
--

/*!40000 ALTER TABLE `medicine_purchase_line` DISABLE KEYS */;
INSERT INTO `medicine_purchase_line` (`med_pur_line_id`,`med_pur_id`,`med_id`,`med_quantity`,`vendor_id`,`med_pur_price`) VALUES 
 (1,1,2,4,2,5),
 (2,2,2,4,2,5),
 (3,2,2,4,2,8),
 (4,3,2,4,2,5),
 (5,3,2,4,2,8),
 (6,4,2,4,2,5),
 (7,4,2,4,2,8),
 (8,5,2,4,2,5),
 (9,5,2,4,2,8),
 (10,6,2,4,2,5),
 (11,6,2,4,2,8),
 (12,7,2,4,2,5),
 (13,7,2,4,2,8),
 (14,8,2,4,2,5),
 (15,8,2,4,2,8),
 (16,9,2,4,2,5),
 (17,9,2,4,2,8),
 (18,19,2,4,2,12),
 (19,20,2,4,2,12),
 (20,21,2,12,1,123),
 (21,21,1,5,2,12),
 (22,22,2,5,2,12),
 (23,23,2,452,2,12),
 (24,23,3,45,2,12),
 (25,25,2,4,2,23),
 (26,26,2,4,2,23),
 (27,27,2,4,2,23),
 (28,27,2,0,1,0),
 (29,27,2,0,1,0),
 (30,28,23,20,1,2),
 (31,28,4,200,1,2.5);
/*!40000 ALTER TABLE `medicine_purchase_line` ENABLE KEYS */;


--
-- Definition of table `medicine_sale`
--

DROP TABLE IF EXISTS `medicine_sale`;
CREATE TABLE `medicine_sale` (
  `med_sale_id` int(10) unsigned NOT NULL auto_increment,
  `med_sale_date` date default NULL,
  `med_sale_amount` double default NULL,
  `med_sale_ref` varchar(45) default NULL,
  PRIMARY KEY  (`med_sale_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicine_sale`
--

/*!40000 ALTER TABLE `medicine_sale` DISABLE KEYS */;
INSERT INTO `medicine_sale` (`med_sale_id`,`med_sale_date`,`med_sale_amount`,`med_sale_ref`) VALUES 
 (1,'2013-10-09',48,'tuhin'),
 (2,NULL,48,''),
 (3,'2013-10-09',749,''),
 (4,NULL,749,''),
 (5,NULL,749,''),
 (6,NULL,749,'Satr'),
 (7,NULL,749,'fdfd'),
 (8,'2013-11-05',320,''),
 (9,NULL,320,'Hasan');
/*!40000 ALTER TABLE `medicine_sale` ENABLE KEYS */;


--
-- Definition of table `medicine_sale_line`
--

DROP TABLE IF EXISTS `medicine_sale_line`;
CREATE TABLE `medicine_sale_line` (
  `med_sale_line_id` int(10) unsigned NOT NULL auto_increment,
  `med_sale_id` int(10) unsigned default NULL,
  `med_id` int(10) unsigned default NULL,
  `med_quantity` int(10) unsigned default NULL,
  `vendor_id` int(10) unsigned default NULL,
  `med_sale_price` double default NULL,
  PRIMARY KEY  (`med_sale_line_id`),
  KEY `FK_medicine_sale_line_sale_id` (`med_sale_id`),
  KEY `FK_medicine_sale_line_med_id` (`med_id`),
  KEY `FK_medicine_sale_line_vendor_id` (`vendor_id`),
  CONSTRAINT `FK_medicine_sale_line_med_id` FOREIGN KEY (`med_id`) REFERENCES `medicine_list` (`med_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_medicine_sale_line_sale_id` FOREIGN KEY (`med_sale_id`) REFERENCES `medicine_sale` (`med_sale_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_medicine_sale_line_vendor_id` FOREIGN KEY (`vendor_id`) REFERENCES `vendor_list` (`vendor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicine_sale_line`
--

/*!40000 ALTER TABLE `medicine_sale_line` DISABLE KEYS */;
INSERT INTO `medicine_sale_line` (`med_sale_line_id`,`med_sale_id`,`med_id`,`med_quantity`,`vendor_id`,`med_sale_price`) VALUES 
 (1,1,2,4,2,48),
 (2,3,2,4,2,48),
 (3,3,2,4,2,48),
 (4,3,2,5,2,5),
 (5,3,2,54,2,648),
 (6,8,4,10,29,320);
/*!40000 ALTER TABLE `medicine_sale_line` ENABLE KEYS */;


--
-- Definition of table `medicine_stock`
--

DROP TABLE IF EXISTS `medicine_stock`;
CREATE TABLE `medicine_stock` (
  `stock_id` int(10) unsigned NOT NULL auto_increment,
  `med_pur_id` int(10) unsigned default NULL,
  `med_id` int(10) unsigned default NULL,
  `stock_amount` int(10) unsigned default NULL,
  PRIMARY KEY  (`stock_id`),
  KEY `FK_medicine_stock_pur_id` (`med_pur_id`),
  KEY `FK_medicine_stock_med_id` (`med_id`),
  CONSTRAINT `FK_medicine_stock_med_id` FOREIGN KEY (`med_id`) REFERENCES `medicine_list` (`med_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_medicine_stock_pur_id` FOREIGN KEY (`med_pur_id`) REFERENCES `medicine_purchase` (`med_pur_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicine_stock`
--

/*!40000 ALTER TABLE `medicine_stock` DISABLE KEYS */;
INSERT INTO `medicine_stock` (`stock_id`,`med_pur_id`,`med_id`,`stock_amount`) VALUES 
 (3,21,1,17),
 (5,23,2,452),
 (6,23,3,45),
 (7,28,23,20),
 (8,28,4,190);
/*!40000 ALTER TABLE `medicine_stock` ENABLE KEYS */;


--
-- Definition of table `medicine_type`
--

DROP TABLE IF EXISTS `medicine_type`;
CREATE TABLE `medicine_type` (
  `med_type_id` int(10) unsigned NOT NULL auto_increment,
  `med_type_name` varchar(45) default NULL,
  PRIMARY KEY  (`med_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicine_type`
--

/*!40000 ALTER TABLE `medicine_type` DISABLE KEYS */;
INSERT INTO `medicine_type` (`med_type_id`,`med_type_name`) VALUES 
 (1,'Tablet '),
 (2,'Capsule'),
 (3,'Injection'),
 (4,'Syrup'),
 (5,'Oral liquid'),
 (6,'Respirator solution for use in nebulizers'),
 (7,'I/V fluid'),
 (8,'Eye ointment'),
 (9,'Powder'),
 (10,'Cream'),
 (11,'Solution'),
 (12,'Ampoule'),
 (13,'Nasal drops'),
 (14,'Suppository'),
 (15,'Inhalation'),
 (16,'Oral Suspension '),
 (17,'Topical'),
 (18,'Ear drop'),
 (19,'Eye drops'),
 (20,'Oral Saline'),
 (21,'IV Infusion'),
 (22,'Ointment'),
 (23,'Hand Rub'),
 (24,'Syringe'),
 (25,'Chewable Tablet');
/*!40000 ALTER TABLE `medicine_type` ENABLE KEYS */;


--
-- Definition of table `patient_info`
--

DROP TABLE IF EXISTS `patient_info`;
CREATE TABLE `patient_info` (
  `patient_id` int(10) unsigned NOT NULL auto_increment,
  `patient_name` varchar(45) default NULL,
  `gender` varchar(45) default NULL,
  `patient_address` varchar(200) default NULL,
  `patient_cell` varchar(45) default NULL,
  `patient_marital_status` varchar(45) default NULL,
  `patient_email` varchar(45) default NULL,
  `patient_blood_group` varchar(45) default NULL,
  `regidate` date default NULL,
  `symptom` varchar(45) default NULL,
  `second_contuct` varchar(200) default NULL,
  `pass` varchar(45) default NULL,
  `patient_dob` date default NULL,
  `age` int(11) default NULL,
  PRIMARY KEY  (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_info`
--

/*!40000 ALTER TABLE `patient_info` DISABLE KEYS */;
INSERT INTO `patient_info` (`patient_id`,`patient_name`,`gender`,`patient_address`,`patient_cell`,`patient_marital_status`,`patient_email`,`patient_blood_group`,`regidate`,`symptom`,`second_contuct`,`pass`,`patient_dob`,`age`) VALUES 
 (20131008,'Zaman','male','Dhaka','0174859685','single',NULL,NULL,NULL,NULL,NULL,NULL,NULL,14),
 (2013091501,'Rahim','Male','Maghbazar','01942795016','single','null','null','2013-09-15','null','null','123','2013-10-08',22),
 (2013091502,'Hasan','Male','Ramna','01912428562','single','null','null','2013-09-15','null','null','123','2013-10-10',54),
 (2013091503,'Taman','Male','Dhaka','01912457854','Single','asdf@yahoo.com','A(+Ve)','2013-01-16','Headace','01854758699','123','1980-11-20',12),
 (2013091504,'Hafeza','Female','Dhaka','01914748596','merrite','tamanna@gmail.com','O(-Ve)','2013-09-29','Blood','01748596544','123','1978-12-16',53),
 (2013100601,'Nasir','Male','Dhaka','01914253675','Unmerried','nasir@gmail.com','AB(+Ve)','2013-10-06','Virul infection','01714253698','1243','1982-12-03',13),
 (2013100602,'Galeb','Male','Dhaka','01912457854','merried',NULL,NULL,NULL,NULL,NULL,NULL,NULL,43),
 (2013100603,'Firij','Male','Sylhet','01745124512','Single','null','null',NULL,'null','null',NULL,'2013-10-08',23),
 (2013100604,'Zia','Male','Mymenshingh','01234567895','Single',NULL,NULL,NULL,NULL,NULL,NULL,NULL,43),
 (2013100605,'Rabid','male','Mymenshingh','017485965','merried',NULL,NULL,NULL,NULL,NULL,NULL,NULL,14),
 (2013100606,'Hasan','male','Mymenshingh','0175456452','Single',NULL,NULL,NULL,NULL,NULL,NULL,NULL,65),
 (2013100607,'Rana','Male','Rajshahi','0174859685','merried',NULL,NULL,NULL,NULL,NULL,NULL,NULL,65),
 (2013100608,'Rana','Male','Rajshahi','0174859685','Single',NULL,NULL,NULL,NULL,NULL,NULL,NULL,43),
 (2013100609,'Razib','Male','Rangpur','01748596845','Other','st@yahoo.com','A+',NULL,'Vaticular','120424',NULL,'1977-11-07',36),
 (2013100610,'asd','male','','123124','Married','','',NULL,'','',NULL,'2013-10-10',65),
 (2013100611,'Halima Akter','Male','Mymenshingh','014785269','Single',NULL,NULL,NULL,NULL,NULL,NULL,NULL,22),
 (2013100612,'Hamid','Male','Rajshahi','01912457845','single',NULL,NULL,NULL,NULL,NULL,NULL,NULL,18),
 (2013100613,'Nahidul Islam','null','Dhaka','012','Other','dt@yahoo.com','A+',NULL,'Grastic','012454',NULL,'1987-11-07',32),
 (2013100614,'hasan','Male','Hasanpur','01924512','Married','sit@yaoo.con','O+',NULL,'Nai','01245',NULL,'1991-11-08',22),
 (2013100615,'Hasina','Female','Dhaka','012345','Single',NULL,'AB+',NULL,NULL,NULL,NULL,NULL,26),
 (2013100616,'Jamila','Female','Rajshahi','0124521','single','tui@yahoo.com','O-',NULL,NULL,NULL,NULL,NULL,25),
 (2013100617,'habib','Male','Mymenshingh','121','single','hti@yahoo.com','B+',NULL,NULL,NULL,NULL,NULL,55),
 (2013100618,'Sarif','Male','Mymenshingh','01241244','Single','pars@yahoo.com','B+',NULL,NULL,NULL,NULL,NULL,55),
 (2013100619,'Farhan','Male','Dhaka','1131','Married','sfs@yahoo.com','O-',NULL,'Feber','04242424',NULL,'2013-11-05',22),
 (2013100620,'Hasina mamataj','Female','Dhaka','017458451245','Married','Nai@yahoo.com','AB-',NULL,'Liber Pain','01745845454',NULL,'2013-11-07',15),
 (2013100621,'Jahan','Male','Rajshahi','1234','Single','pin@hotmail.com','A+',NULL,NULL,NULL,NULL,NULL,19),
 (2013100622,'Rana','Male','Khulna','2323232','Married','saf@gmail.com','AB-',NULL,'Feber','0124521144',NULL,'1987-11-07',21),
 (2013100623,'Zamshed','Male','dafa','1234','Married','sit@yahoo.com','AB+',NULL,'Feber','120',NULL,'1977-11-08',36),
 (2013100624,'Farnan','Male','Dhaka','01924099345','Married','si@yahoo.com','A+',NULL,'Fever','Nai',NULL,'1987-11-07',22),
 (2013100625,'Mr. Asadul Islam','Male','Ramna, Dhaka','01987458575','Married','asadul@yahoo.com','AB+',NULL,'Liber Pain','01924088547',NULL,'1980-11-05',28);
/*!40000 ALTER TABLE `patient_info` ENABLE KEYS */;


--
-- Definition of table `pharmacy`
--

DROP TABLE IF EXISTS `pharmacy`;
CREATE TABLE `pharmacy` (
  `pharmacy_id` int(10) unsigned NOT NULL auto_increment,
  `pharmacy_name` varchar(95) NOT NULL,
  `address` varchar(545) NOT NULL,
  `mobile#` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `med_group_id` int(10) unsigned NOT NULL,
  `stock_amount` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`pharmacy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacy`
--

/*!40000 ALTER TABLE `pharmacy` DISABLE KEYS */;
/*!40000 ALTER TABLE `pharmacy` ENABLE KEYS */;


--
-- Definition of table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription` (
  `pres_id` int(10) unsigned NOT NULL auto_increment,
  `patient_id` int(10) unsigned default NULL,
  `doctor_id` int(10) unsigned default NULL,
  `pres_date` date default NULL,
  `next_consult_date` date default NULL,
  `remarks` varchar(100) default NULL,
  PRIMARY KEY  (`pres_id`),
  KEY `FK_prescription_patient` (`patient_id`),
  KEY `FK_prescription_doctor` (`doctor_id`),
  CONSTRAINT `FK_prescription_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor_info` (`doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_prescription_patient` FOREIGN KEY (`patient_id`) REFERENCES `patient_info` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescription`
--

/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` (`pres_id`,`patient_id`,`doctor_id`,`pres_date`,`next_consult_date`,`remarks`) VALUES 
 (1,2013091502,2,'2013-09-28','2013-10-15',NULL),
 (2,2013091502,2,'2013-09-30','2013-10-14',NULL),
 (3,2013091503,1,'2013-10-01','2013-10-01',NULL),
 (4,2013091503,1,'2013-10-02','2013-10-01',NULL),
 (5,2013091501,4,'2013-10-03','2013-10-01',NULL),
 (6,2013091501,4,'2013-10-04','2013-10-01',NULL),
 (7,2013091501,1,'2013-10-05','2013-10-01',NULL),
 (8,2013100619,1,'2013-11-05','2013-11-05','Good'),
 (9,2013100624,5,'2013-11-05','2013-11-05','Avoid meat, only take vegetable for next 15 days');
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;


--
-- Definition of table `prescription_line`
--

DROP TABLE IF EXISTS `prescription_line`;
CREATE TABLE `prescription_line` (
  `pres_line_id` int(10) unsigned NOT NULL auto_increment,
  `pres_id` int(10) unsigned NOT NULL,
  `med_id` int(10) unsigned NOT NULL,
  `douse` varchar(45) NOT NULL,
  `douse_schedule` varchar(45) NOT NULL,
  PRIMARY KEY  (`pres_line_id`),
  KEY `FK_prescription_line_prescription` (`pres_id`),
  KEY `FK_prescription_line_medicine` (`med_id`),
  CONSTRAINT `FK_prescription_line_medicine` FOREIGN KEY (`med_id`) REFERENCES `medicine_list` (`med_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_prescription_line_prescription` FOREIGN KEY (`pres_id`) REFERENCES `prescription` (`pres_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescription_line`
--

/*!40000 ALTER TABLE `prescription_line` DISABLE KEYS */;
INSERT INTO `prescription_line` (`pres_line_id`,`pres_id`,`med_id`,`douse`,`douse_schedule`) VALUES 
 (1,2,2,'8','1-1-1'),
 (2,2,3,'8','1-1-1-1'),
 (3,2,2,'8','1-0-0'),
 (4,3,2,'45','0-0-1'),
 (5,4,2,'5','0-0-1'),
 (6,5,3,'5','1-1-1-1'),
 (7,5,2,'5','0-0-1'),
 (8,5,2,'54','0-0-1'),
 (9,7,2,'7','0-0-1'),
 (10,7,3,'45','1-0-0'),
 (11,8,2,'7','1-0-1'),
 (12,9,14,'12','1-1-1'),
 (13,9,20,'16','1-1-1'),
 (14,9,6,'7','1-1-1'),
 (15,9,31,'15','1-1-1');
/*!40000 ALTER TABLE `prescription_line` ENABLE KEYS */;


--
-- Definition of table `test`
--

DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `test_id` int(10) unsigned NOT NULL auto_increment,
  `test_name` varchar(45) default NULL,
  `test_type` varchar(45) default NULL,
  `test_cost` varchar(45) default NULL,
  PRIMARY KEY  (`test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test`
--

/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`test_id`,`test_name`,`test_type`,`test_cost`) VALUES 
 (1,'Hepatitis A','infectious','120'),
 (2,'Hepatitis B','infectious','350'),
 (3,'HIV ','infectious','120'),
 (4,'Tuberculosis','infectious','60'),
 (5,'Pneumonia','infectious','480'),
 (6,'Malaria','infectious','480'),
 (7,'Dengue fever','infectious','480'),
 (8,'Cholera','infectious','360'),
 (9,'Alcoholism','neurological','690'),
 (10,'Bilirubin','Blood','280'),
 (12,'CT Scans','Brain Scan','400'),
 (13,'MRI','Brain Scan','1600'),
 (14,'CT angiography','Brain Scan','120'),
 (15,'Electroencephalogram (EEG)','Electrical Brain Scan','978'),
 (16,'Angiography','X-Ray','450'),
 (17,'Duplex Scans','Cardiac Ultrasound Scan','387'),
 (18,'Magnetic resonance angiography (MRA)','Radio test of Blood','488'),
 (19,'Echocardiography (ECHO) ','Cardiac Sound Test','540');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;


--
-- Definition of table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `user` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `role` int(10) unsigned NOT NULL,
  PRIMARY KEY  USING BTREE (`id`,`user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userinfo`
--

/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` (`id`,`user`,`pass`,`role`) VALUES 
 (1,'admin','123',1),
 (2,'doctor','123',2),
 (3,'diagnosis','123',4),
 (4,'pharmacy','123',3),
 (5,'kamal','123',2),
 (6,'dac','12345',5),
 (7,'B','12345',3),
 (8,'C','12345',4),
 (9,'D','12345',2),
 (10,'H','12131',5),
 (11,'2013100624','123',6),
 (12,'2013100620','123',6),
 (13,'2013100622','123',6),
 (14,'2013100613','123',6),
 (15,'2013100623','123',6),
 (17,'2013100609','123456789',6),
 (19,'2013100614','12345',6);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;


--
-- Definition of table `vendor_list`
--

DROP TABLE IF EXISTS `vendor_list`;
CREATE TABLE `vendor_list` (
  `vendor_id` int(10) unsigned NOT NULL auto_increment,
  `vendor_name` varchar(125) NOT NULL,
  `vendor_address` varchar(200) default NULL,
  `vendor_email` varchar(45) default NULL,
  `web _address` varchar(45) default NULL,
  `vendor_mobile` varchar(45) default NULL,
  `vendor_comp_name` varchar(45) NOT NULL,
  PRIMARY KEY  (`vendor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vendor_list`
--

/*!40000 ALTER TABLE `vendor_list` DISABLE KEYS */;
INSERT INTO `vendor_list` (`vendor_id`,`vendor_name`,`vendor_address`,`vendor_email`,`web _address`,`vendor_mobile`,`vendor_comp_name`) VALUES 
 (1,'Square Pharmaceuticals Ltd. Gazipur','Kaliakair, Gazipur,','','','','235, 460'),
 (2,'Renata Limited','Sec-11, Mirpur, Dhaka','','','','45, 197'),
 (3,'ACI Limited','Godnail, Narayangonj, , 7, Haziganj Road','info@aci-bd.com','www.aci-bd.com','','51, 213'),
 (4,'Acme Laboratories Ltd.','Dhamrai, Dhaka',NULL,NULL,NULL,'115, 250'),
 (5,'Gonoshasthaya Pharmaceuticals Ltd','Nayarhat, Savar, Dhaka',NULL,NULL,NULL,'117, 381'),
 (6,'Globe Pharmaceuticals Ltd','Begumganj, Noakhali',NULL,NULL,NULL,'182, 50'),
 (7,'Ibn Sina Pharmaceutical Ind. Ltd','Kaliakair, Gazipur',NULL,NULL,NULL,'150, 405'),
 (8,'Incepta Pharmaceuticals Ltd','Zirabo, Savar, Dhaka, Dewan Idris Road, Bara Rangamatia','incepta@inceptapharma.com','www.inceptapharma.com','880-2-8891688-703','193, 108'),
 (9,'Jayson Pharmaceuticals Ltd','231, Tejgoan I/A, Dhaka',NULL,NULL,NULL,'3, 82'),
 (10,'JMI Syringes & Medical Devices Ltd','Rajendrapur, Chaowddhagram, Comilla',NULL,NULL,NULL,'237'),
 (11,'Libra Pharmaceuticls Ltd','1/7, Section-2, Mirpur, Dhaka',NULL,NULL,NULL,'140'),
 (12,'Modern Pharmaceuticals Ltd','Mordern Para, Jhenaidah',NULL,NULL,NULL,'131, 315'),
 (13,'Novartis (Bangladesh) Ltd','Squibb Road, Tongi, Gazipur.',NULL,NULL,NULL,'188, 427'),
 (14,'Opso Saline Ltd','Bogra Road, Barisal',NULL,NULL,NULL,'158'),
 (15,'Orbit Pharmaceuticals Ltd','Shahmirpur, Karnaphuly, Chittagong, , Shahmirpur, Karnaphuly',NULL,NULL,NULL,'253, 204'),
 (16,'Opsonin Pharma Limited','Bogra Road, Barisal',NULL,NULL,NULL,'12, 80'),
 (17,'Orion Pharma Ltd',' 154, Tejgaon I/A',NULL,NULL,NULL,'46, 179'),
 (18,'Prime Pharmaceuticals Ltd','Tapirpara, Shesu Palli, Sreepur, Gazipur, Tapirpara, Shesu Palli, Sreepur, Gazipur',NULL,NULL,NULL,'225, 454'),
 (19,'Radiant Pharmaceutical Ltd','B-46, Bscic, Tongi',NULL,NULL,NULL,'66, 247'),
 (20,'Rephco Pharmaceuticals Ltd','Mathuranath Public School Road, Nutan Bazar, Bari',NULL,NULL,NULL,'58, 229'),
 (21,'Rid Pharma Ltd','BSCIC Shilpanagory, Nondonpur, B. Baria',NULL,NULL,NULL,'257, 476'),
 (22,'Roche Bangladesh Pharmaceutical','Rajendrapur, Gazipur',NULL,NULL,NULL,'233, 458'),
 (23,'Sanofi Aventis (BD) Ltd','Tongi I/A, Gazipur',NULL,NULL,NULL,'176, 39'),
 (24,'Seema Pharmaceuticals Ltd.','Shahid Court, Savar ,Dhaka',NULL,NULL,NULL,'111, 231'),
 (25,'Social Marketing Company','Zamirdia, Bhaluka',NULL,NULL,NULL,'470'),
 (26,'Square Pharmaceuticals Ltd. Pabna','Pabna',NULL,NULL,NULL,'33, 114'),
 (27,'Standard Laboratories Ltd','970, East Nasirabad, Chittagong',NULL,NULL,NULL,'30, 55'),
 (28,'Techno Drugs Ltd','Satipara, Narshingdi,',NULL,NULL,NULL,'211, 446'),
 (29,'Beximco Pharmaceuticals Ltd','Tongi I/A, Gazipur,',NULL,NULL,NULL,'119, 379'),
 (30,'Bio Pharma Laboratories Ltd','A-116, Tongi, Gazipur.',NULL,NULL,NULL,'81, 322'),
 (31,'Aristopharma Limited','Plot No. 21, Road 11, Shampur, Kadamtali I/A, Dhak,',NULL,NULL,NULL,'171, 308'),
 (32,'Ambee Pharmaceuticals Ltd','184/1 Tejgaon I/A, Dhaka',NULL,NULL,NULL,'383, 124'),
 (33,'Drug International Ltd','Tongi I/A, Gazipur',NULL,NULL,NULL,'398, 127'),
 (34,'EDCL (Bogra)','Thonthonia, Bogra',NULL,NULL,NULL,'146, 389'),
 (35,'Eskayef Bangladesh Ltd., Tongi','400 Tongi/ I/A. Squibb Road, Gazipur',NULL,NULL,NULL,'215, 449'),
 (36,'GlaxoSmithKline Bangladesh Ltd','Fauzderhat I/A, Chittagong',NULL,NULL,NULL,'42, 182'),
 (37,'Beacon Pharmaceuticals Ltd','Kathali, Bhaluka',NULL,NULL,NULL,'258, 477');
/*!40000 ALTER TABLE `vendor_list` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
