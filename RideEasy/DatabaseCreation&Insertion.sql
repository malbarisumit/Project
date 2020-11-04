/********************** DATABASE CREATION ************************/
/********************* DDL **************************************/
CREATE DATABASE Group5_Project_RideEasy;
GO
USE Group5_Project_RideEasy;

CREATE TABLE [Admin] (
  [AdminID] varchar(10) NOT NULL PRIMARY KEY,
  [Password] varbinary(250) NOT NULL,
  [StationNo] varchar(5) NOT NULL,
  [FirstName] varchar(40) NOT NULL,
  [LastName] varchar(40) NOT NULL
);

CREATE TABLE [UserProfile] (
  [ID] INT IDENTITY(1,1) NOT NULL,
  [UserID] AS 'UID' + RIGHT('00000000' + CAST(ID AS VARCHAR(8)), 8) PERSISTED PRIMARY KEY,
  [ApprovedByAdmin] varchar(10) REFERENCES Admin(AdminID),
  [UserName] varchar(50) NOT NULL UNIQUE,
  [Password] varbinary(250) NOT NULL,
  [FirstName] varchar(50) NOT NULL,
  [LastName] varchar(50) NOT NULL,
  [Street] varchar(50) NOT NULL,
  [Region] varchar(50) NOT NULL,
  [City] varchar(50) NOT NULL,
  [State] varchar(50) NOT NULL,
  [Country] varchar(50) NOT NULL,
  [PhoneNumber] varchar(10) NOT NULL,
  [EmailID] varchar(50) NOT NULL,
  [Type] varchar(30) NOT NULL,
  [OverAllRating] decimal(2,1) NOT NULL DEFAULT 0
);

CREATE TABLE [Rider] (
  [ID] INT IDENTITY(1,1) NOT NULL,
  [RiderID] AS 'RID' + RIGHT('00000000' + CAST(ID AS VARCHAR(8)), 8) PERSISTED PRIMARY KEY,
  [UserID] varchar(11) NOT NULL REFERENCES UserProfile(UserID),
  [AmountDue] decimal(10,2) NOT NULL DEFAULT 0
);

CREATE TABLE [Driver] (
  [ID] INT IDENTITY(1,1) NOT NULL,
  [DriverID] AS 'DRI' + RIGHT('00000000' + CAST(ID AS VARCHAR(8)), 8) PERSISTED PRIMARY KEY,
  [UserID] varchar(11) NOT NULL REFERENCES UserProfile(UserID),
  [LicenseNo] varchar(20),
  [Gender] char,
  [TotalEarnings] decimal(10,2) NOT NULL DEFAULT 0
);

CREATE TABLE [CarOwner] (
  [ID] INT IDENTITY(1,1) NOT NULL,
  [OwnerID] AS 'CRO' + RIGHT('00000000' + CAST(ID AS VARCHAR(8)), 8) PERSISTED PRIMARY KEY,
  [UserID] varchar(11) NOT NULL REFERENCES UserProfile(UserID),
  [CompanyName] varchar(30),
  [ContractType] varchar(20),
  [TotalEarnings] decimal(10,2) NOT NULL DEFAULT 0,
  [ContractDuration] int NOT NULL DEFAULT 0
);

CREATE TABLE [FeedBack] (
  [ID] INT IDENTITY(1,1) NOT NULL,
  [FeedBackID] AS 'FDB' + RIGHT('00000000' + CAST(ID AS VARCHAR(8)), 8) PERSISTED PRIMARY KEY,
  [UserID] varchar(11) NOT NULL REFERENCES UserProfile(UserID),
  [Rating] decimal(2,1) NOT NULL DEFAULT 0,
  [Remarks] varchar(100)
);

CREATE TABLE [PromoCode] (
  [PromoCode] varchar(50) NOT NULL PRIMARY KEY,
  [Offer] varchar(100) NOT NULL,
  [DiscountAmount] decimal(5,2) NOT NULL DEFAULT 0
);

CREATE TABLE [Location] (
  [LocationID] int NOT NULL PRIMARY KEY,
  [Street] varchar(100) NOT NULL,
  [Region] varchar(50) NOT NULL,
  [City] varchar(50) NOT NULL,
  [State] varchar(50) NOT NULL,
  [Country] varchar(50) NOT NULL,
  [Latitude] decimal(10,6) NOT NULL,
  [Longitude] decimal(10,6) NOT NULL
);

CREATE TABLE [Cars] (
  [ID] INT IDENTITY(1,1) NOT NULL,
  [CarID] AS 'CAR' + RIGHT('00000000' + CAST(ID AS VARCHAR(8)), 8) PERSISTED PRIMARY KEY,
  [OwnerID] varchar(11) NOT NULL REFERENCES CarOwner(OwnerID),
  [DriverID] varchar(11) REFERENCES Driver(DriverID),
  [CarModel] varchar(50) NOT NULL,
  [CarRegistrationNumber] varchar(10) NOT NULL,
  [CarColor] varchar(30) NOT NULL,
  [LocationID] int NOT NULL REFERENCES Location(LocationID),
  [Capacity] int NOT NULL,
  [Category] varchar(25) NOT NULL
);

CREATE TABLE [TripCharges] (
  [State] varchar(50) NOT NULL PRIMARY KEY,
  [Tax] decimal(5,3) NOT NULL DEFAULT 0,
  [ChargePerKilometer] decimal(5,3) NOT NULL DEFAULT 0
);

CREATE TABLE [Request] (
  [ID] INT IDENTITY(1,1) NOT NULL,
  RequestID AS 'REQ' + RIGHT('00000000' + CAST(ID AS VARCHAR(8)), 8) PERSISTED PRIMARY KEY,
  [RiderID] varchar(11) NOT NULL REFERENCES Rider(RiderID),
  [CarID] varchar(11) NOT NULL REFERENCES Cars(CarID),
  [SourceLocationID] int NOT NULL REFERENCES Location(LocationID),
  [DestinationLocationID] int NOT NULL REFERENCES Location(LocationID),
  [Status] varchar(50) NOT NULL DEFAULT 'Pending Approval',
  [State] AS dbo.RetrieveState(SourceLocationID),
  [PromoCode] varchar(50) REFERENCES PromoCode(PromoCode) DEFAULT NULL,
  [ReasonForCancellation] varchar(100),
  [EstimationAmount] AS dbo.EstimateTripAmount(SourceLocationID, DestinationLocationID, PromoCode)
);

CREATE TABLE [Trip] (
  [RequestID] varchar(11) NOT NULL REFERENCES Request(RequestID),
  [Distance] decimal(5,3) NOT NULL,
  [Status] varchar(50) NOT NULL DEFAULT 'On the Way',
  [StartTimestamp] datetime NOT NULL DEFAULT GETDATE(),
  [DropTimestamp] datetime NOT NULL DEFAULT GETDATE()
);

CREATE TABLE [Invoice] (
  [ID] INT IDENTITY(1,1) NOT NULL,
  InvoiceID AS 'INV' + RIGHT('00000000' + CAST(ID AS VARCHAR(8)), 8) PERSISTED PRIMARY KEY,
  [RequestID] varchar(11) NOT NULL REFERENCES Request(RequestID),
  [Distance] decimal(5,2) NOT NULL,
  [Price] decimal(10,3) NOT NULL
);

CREATE TABLE [PaymentGateway] (
  [ID] INT IDENTITY(1,1) NOT NULL,
  PaymentID AS 'PAY' + RIGHT('00000000' + CAST(ID AS VARCHAR(8)), 8) PERSISTED PRIMARY KEY,
  [InvoiceID] varchar(11) NOT NULL REFERENCES Invoice(InvoiceID),
  [RiderID] varchar(11) NOT NULL REFERENCES Rider(RiderID),
  [MethodOfPayment] varchar(25) NOT NULL,
  [Amount] decimal(10,2) NOT NULL,
  [Status] varchar(15) NOT NULL
);

/**************************DATABASE INSERTION**********************/
/********************* DML ***************************************/

INSERT INTO [dbo].[Admin] 
	([AdminID],[Password],[StationNo],[FirstName],[LastName])
	VALUES
           ('A1',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'akshay123'),'S1','Akshay','Khandelwal'),
           ('A2',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'avinash123'),'S2','Avinash','Patti'),
           ('A3',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'sumit123'),'S3','Sumit','Malbari'),
           ('A4',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'saurabh123'),'S4','Saurabh','Ambardekar'),
           ('A5',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'varada123'),'S5','Varada','Kulkarni'),
           ('A6',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'zarana123'),'S6','Zarana','Bhadricha'),
           ('A7',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'gal123'),'S7','Gal','Gadot'),
           ('A8',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'robin123'),'S8','Robin','Wright'),
           ('A9',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'alia123'),'S9','Alia','Bhatt'),
           ('A10',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'andrea123'),'S10','Andrea','Edie');

INSERT INTO [dbo].[UserProfile]
	([ApprovedByAdmin],[UserName],[Password],[FirstName],[LastName],[Street],[Region],[City],[State],[Country],[PhoneNumber],[EmailID],[Type],[OverAllRating])
     VALUES
			('A1','AlexandrinaChery',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'929865yt'),'Alexandrina','Chery','CobraVaddo','CalungateBagaRoad','Calangute','Goa','India','9999999000','Chery@gmail.com','CarOwner',5),
			('A3','AbbieCherye',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'jonothepoop1'),'Abbie','Cherye','8180Street','ArakashanRoad','Paharganj','Delhi','India','9999999001','Cherye@gmail.com','CarOwner',4),
			('A2','AbbotCheryl',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'838188linh'),'Abbot','Cheryl','GurudwaraRoad','KarolBagh','KarolBagh','Delhi','India','9999999002','Cheryl@gmail.com','CarOwner',5),
			('A3','AbbottCheslie',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'kVczcljg4OA25Aeb'),'Abbott','Cheslie','ACRoad','Alleppey','Alleppey','Kerala','India','9999999003','Cheslie@gmail.com','CarOwner',4),
			('A1','AbbyChiarra',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'ass359'),'Abby','Chiarra','CentralAvenue','KalyaniNagar','Pune','Maharashtra','India','9999999004','Chiarra@gmail.com','CarOwner',5),
			('A4','AbdelChickie',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'icap12'),'Abdel','Chickie','15thCross','JPNagar','Bangalore','Karnataka','India','9999999005','Chickie@gmail.com','CarOwner',5),
			('A2','AbdulChicky',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'2akira2'),'Abdul','Chicky','E-Road','Koyambedu','Chennai','TamilNadu','India','9999999006','Chicky@gmail.com','CarOwner',4),
			('A5','AbdulkarimChiquita',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'znbl5tj1'),'Abdulkarim','Chiquita','JawaharlalNehruRoad','Vadapalani','Chennai','TamilNadu','India','9999999007','Chiquita@gmail.com','CarOwner',5),
			('A8','AbdullahChloe',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'123maxbala'),'Abdullah','Chloe','PeriyarHighRoad','Periamet','Chennai','TamilNadu','India','9999999008','Chloe@gmail.com','CarOwner',4),
			('A9','AbeChloette',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'64959rodro'),'Abe','Chloette','CandolimBeachRoad','Calungate','Calangute','Goa','India','9999999009','Chloette@gmail.com','CarOwner',5),
			('A10','AbelChloris',encryptbykey(KEY_GUID(N'TestSymmetricKey'),'farrukhcse12'),'Abel','Chloris','SouthCity','Sector41','Gurgaon','Delhi','India','9999999010','Chloris@gmail.com','CarOwner',5);

INSERT INTO [dbo].[FeedBack]
     VALUES
            ('UID00000001',5,'Well maintained Cars'),
			('UID00000002',4,''),
			('UID00000003',5,'Well maintained Cars'),
			('UID00000004',4,''),
			('UID00000005',5,'Well maintained Cars'),
			('UID00000006',5,'Well maintained Cars'),
			('UID00000007',4,''),
			('UID00000008',5,'Well maintained Cars'),
			('UID00000009',4,''),
			('UID00000010',5,'Well maintained Cars');

INSERT INTO [dbo].[PromoCode]
     VALUES
            ('EasyFirstRide','50 bucks off on your first EasyRide trip',50),
			('EasyChristmas','Festive discount on holiday week',30),
			('EasyDiwali','Festive discount in Diwali week',30),
			('EasyRide25','25 bucks off',25),
			('EasyRide20','20 bucks off',20),
			('EasyRide15','15 bucks off',15),
			('EasyRide10','10 bucks off',10),
			('EasyCash50','50 bucks off on cash payment',50),
			('EasyPaytm10','10 bucks off on UPI',10),
			('EasyFreeRide','200 bucks off on a ride',200),
			('EasyPremier','50 bucks for on Premium ride',50);

INSERT INTO [dbo].[Rider]
     VALUES
            ('UID00000041',0),
			('UID00000042',0),
			('UID00000043',0),
			('UID00000044',0),
			('UID00000045',0),
			('UID00000046',0),
			('UID00000047',0),
			('UID00000048',0),
			('UID00000049',0),
			('UID00000050',0),
			('UID00000051',0),
			('UID00000052',0);

INSERT INTO [dbo].[Driver]
     VALUES
            ('UID00000016','L1234','M',0),
			('UID00000017','L2340','M',0),
			('UID00000018','L7643','M',0),
			('UID00000019','L3244','M',0),
			('UID00000020','L2341','M',0),
			('UID00000021','L6454','M',0),
			('UID00000022','L1223','M',0),
			('UID00000023','L5341','M',0),
			('UID00000024','L6423','M',0),
			('UID00000025','L9001','F',0),
			('UID00000026','L9391','F',0),
			('UID00000027','L2451','F',0),
			('UID00000028','L8128','F',0);

INSERT INTO [dbo].[CarOwner]
     VALUES
            ('UID00000001','X Automobiles','X001',0,5),
			('UID00000002','Y Cars','X002',0,6),
			('UID00000003','Z Cars','X003',0,7),
			('UID00000004','M Vehicles','X004',0,10),
			('UID00000005','N CarTaxi','X005',0,8),
			('UID00000006','O CarRentals','X006',0,5),
			('UID00000007','K CarServices','X007',0,4),
			('UID00000008','Cars Pro','X008',0,6),
			('UID00000009','Best Car Rent','X009',0,8),
			('UID00000010','S Cabs','X010',0,10),
			('UID00000011','C Cars','X011',0,6),
			('UID00000012','New Cars','X012',0,8),
			('UID00000013','My Car rent','X013',0,4),
			('UID00000014','Take Car','X014',0,5),
			('UID00000015','Car Book','X015',0,8);

INSERT INTO [dbo].[Location]
     VALUES
            (10001,'Hiranandani Gardens','Powai','Mumbai','Maharashtra','India',18.987807,72.836447),
			(10002,'Chandani Chowk','NewDelhi','Delhi','Delhi','India',28.651952,77.231495),
			(10003,'North 24 Parganas','Howrah','Kolkata','West Bengal','India',22.562627,88.363044),
			(10004,'Sardar Patel Marg','Ambattur','Chennai','Tamil Nadu','India',13.084622,80.248357),
			(10005,'Church Street','Marathahalli','Bengaluru','Karnataka','India',12.977063,77.587106),
			(10006,'Housing Colony','Venkatagiri','Hyderabad','Andhra Pradesh','India',17.384052,78.456355),
			(10007,'9/ Nehru Road','Shahpur','Ahmadabad','Gujarat','India',23.025793,72.587265),
			(10008,'Cobra Vaddo','Siparia','Haora','West Bengal','India',22.576882,88.318566),
			(10009,'8180 Street','AambyValley','Pune','Maharashtra','India',18.513271,73.849852),
			(10010,'Gurudwara Road','Kadodara','Surat','Gujarat','India',21.195944,72.830232);

INSERT INTO [dbo].[Cars]
     VALUES
            ('CRO00000001','DRI00000001','Tata Nano','ABC001','Grey',10001,4,'Micro'),
			('CRO00000001','DRI00000002','Etios Liva','ABC002','White',10089,4,'Micro'),
			('CRO00000001','DRI00000003','Maruti Ritz','ABC003','Red',10091,4,'Micro'),
			('CRO00000004','DRI00000004','Maruti WagonR','ABC004','Black',10035,4,'Micro'),
			('CRO00000004','DRI00000005','Tata Indica Vista','ABC005','Blue',10002,4,'Micro'),
			('CRO00000005','DRI00000006','Toyota Innova','ABC006','Navy Blue',10001,6,'MaxXL'),
			('CRO00000005','DRI00000007','Maruti Suzuki XL','ABC007','Black',10001,6,'MaxXL'),
			('CRO00000002','DRI00000008','GMC Yukon XL','ABC008','Pearl White',10200,6,'MaxXL'),
			('CRO00000002','DRI00000009','Nissan Terrano','ABC009','Grey',10010,6,'MaxXL'),
			('CRO00000003','DRI00000010','Nissan Sunny','ABC010','White',10036,6,'MaxXL'),
			('CRO00000007','DRI00000011','Hyundai Santro','ABC011','Red',10025,4,'Mini'),
			('CRO00000007','DRI00000012','Honda Amaze','ABC012','Black',10084,4,'Mini'),
			('CRO00000008','DRI00000013','Honda Civic','ABC013','Blue',10027,4,'Mini'),
			('CRO00000008','DRI00000014','Honda Amaze','ABC014','Navy Blue',10050,4,'Mini'),
			('CRO00000009','DRI00000015','Maruti Swift Dzire','ABC015','Black',10050,4,'Mini'),
			('CRO00000010','DRI00000016','Jaguar XE','ABC016','Pearl White',10006,4,'Premium');

INSERT INTO [dbo].[TripCharges]
     VALUES
			('Andaman and Nicobar Islands',12.8,6.5),
			('Andhra Pradesh',23.6,7),
			('Arunachal Pradesh',23.5,7.5),
			('Assam',13,6.5),
			('Bihar',27.5,7.15),
			('Chandigarh',23.52,8.25),
			('Chhattisgarh',25.3,7.25),
			('Dadra and Nagar Haveli',16.85,6.5),
			('Daman and Diu',13.52,7.25),
			('Delhi',41.25,12.5),
			('Goa',36.75,9.25),
			('Gujarat',35.45,7.25),
			('Haryana',20.5,6.25),
			('Himachal Pradesh',19.5,7.25),
			('Jammu and Kashmir',10.5,5.5);

INSERT INTO [dbo].[Request]
     VALUES
			('RID00000001','CAR00000003',10126,10139,'Approved','EasyFirstRide',NULL),
			('RID00000002','CAR00000024',10002,10115,'Approved', NULL,NULL),
			('RID00000003','CAR00000017',10017,10079,'Approved','EasyPremier',NULL),
			('RID00000004','CAR00000002',10035,10031,'Approved','EasyRide15',NULL),
			('RID00000005','CAR00000004',10068,10029,'Approved', NULL,NULL),
			('RID00000006','CAR00000005',10001,10009,'Cancelled',NULL,'Driver Cancelled'),
			('RID00000007','CAR00000022',10121,10184,'Approved','EasyCash50',NULL),
			('RID00000008','CAR00000016',10111,10036,'Approved','EasyRide15',NULL),
			('RID00000009','CAR00000005',10210,10092,'Approved','EasyFreeRide',NULL),
			('RID00000010','CAR00000008',10005,10050,'Approved',NULL,NULL);

INSERT INTO [dbo].[Trip]
	VALUES
			('REQ00000001',16.44,'Completed','2020-07-01 15:01:59.820','2020-07-01 16:30:59.820'),
			('REQ00000002',10.08,'Completed','2020-07-02 15:06:59.820','2020-07-02 16:14:59.820'),
			('REQ00000003',15.72,'Completed','2020-07-06 15:02:59.820','2020-07-06 16:02:59.820'),
			('REQ00000004',23.48,'Completed','2020-07-09 15:02:59.820','2020-07-09 16:08:59.820'),
			('REQ00000005',32.24,'Completed','2020-07-08 15:02:59.820','2020-07-08 16:55:59.820'),
			('REQ00000007',10.76,'Completed','2020-07-15 16:06:59.820','2020-07-15 17:06:59.820'),
			('REQ00000008',70.71,'Completed','2020-07-31 16:06:59.820','2020-07-31 17:08:59.820'),
			('REQ00000009',15.23,'Completed','2020-07-25 16:06:59.820','2020-07-25 17:16:59.820'),
			('REQ00000010',16.16,'Completed','2020-07-22 16:06:59.820','2020-07-22 17:20:59.820');

INSERT INTO [dbo].[Invoice]
	VALUES
			('REQ00000001',16.44,526),
			('REQ00000002',10.08,110.5),
			('REQ00000003',15.72,457),
			('REQ00000004',23.48,326),
			('REQ00000005',32.24,290.5),
			('REQ00000007',10.76,410.5),
			('REQ00000008',70.71,168.5),
			('REQ00000009',15.23,300),
			('REQ00000010',16.16,270.5);

INSERT INTO [dbo].[PaymentGateway]
	VALUES
			('INV00000001','RID00000001','UPI',526,'Successful'),
			('INV00000002','RID00000002','Cash',110.5,'Successful'),
			('INV00000003','RID00000003','Card',457,'Successful'),
			('INV00000004','RID00000004','UPI',326,'Successful'),
			('INV00000005','RID00000005','UPI',290.5,'Failed'),
			('INV00000006','RID00000007','Card',410.5,'Pending'),
			('INV00000007','RID00000008','Card',168.5,'Successful'),
			('INV00000008','RID00000009','Cash',300,'Successful'),
			('INV00000009','RID00000010','UPI',270.5,'Failed'),
			('INV00000010','RID00000011','Cash',330.5,'Successful');