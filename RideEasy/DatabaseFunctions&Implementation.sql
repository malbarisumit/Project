/***********************************************SQL Views******************************************************/
--1. To view the Nearby cars while requesting for a ride.

CREATE VIEW RequestRide 
AS
(
	SELECT CarModel, CarRegistrationNumber, CarColor, Category, Capacity, LocationID
	FROM Cars
);
SELECT * FROM RequestRide;

--2. Admin can view all pending requests

CREATE VIEW UserPendingRequest
AS
(
	SELECT u.UserID, u.UserName, u.FirstName, u.LastName, u.Type, u.Street, u.Region,
	u.City, u.State, u.Country, u.PhoneNumber, u.EmailID, u.ApprovedByAdmin
	FROM UserProfile u
	WHERE [Type] != 'Rider'
	EXCEPT
	SELECT u.UserID, u.UserName, u.FirstName, u.LastName, u.Type, u.Street, u.Region,
	u.City, u.State, u.Country, u.PhoneNumber, u.EmailID, u.ApprovedByAdmin
	FROM UserProfile u
	FULL JOIN CarOwner o
	ON u.UserID = o.UserID
	WHERE o.ContractDuration != 0
	EXCEPT
	SELECT u.UserID, u.UserName, u.FirstName, u.LastName, u.Type, u.Street, u.Region,
	u.City, u.State, u.Country, u.PhoneNumber, u.EmailID, u.ApprovedByAdmin
	FROM UserProfile u
	FULL JOIN Driver d
	ON u.UserID = d.UserID
	WHERE d.LicenseNo IS NOT NULL
);
SELECT * FROM UserPendingRequest;

--3. To view all the trips till date

CREATE VIEW RiderTrip
AS
(
	SELECT r.RiderID, r.CarID, r.SourceLocationID, r.DestinationLocationID, t.Distance, r.EstimationAmount, r.PromoCode
	FROM Request r
	INNER JOIN Trip t
	ON r.RequestID = t.RequestID
);
SELECT * FROM RiderTrip;

--4. To view poplarity of methods of payments

CREATE VIEW vw_MOP
AS 
(
	SELECT p.MethodOfPayment, (COUNT(p.RiderID)*100)/
		(SELECT COUNT (*) FROM dbo.PaymentGateway) 
		[Percent of Users] 
	FROM dbo.PaymentGateway p
	GROUP BY MethodOfPayment
);
SELECT * FROM vw_MOP;

--5. To view poplarity of promo codes

CREATE VIEW vw_PromoCodes 
AS
(
	SELECT Promocode,(COUNT(RequestID) * 100.0 /
			(SELECT COUNT(RequestID)
			FROM dbo.Request
			WHERE Promocode IS NOT NULL))PromocodeUsage
	FROM dbo.Request
	WHERE Promocode IS NOT NULL
	GROUP BY Promocode
);
SELECT * FROM vw_PromoCodes;

--6. To view supply and demand

CREATE VIEW Supply 
AS
(
	SELECT COUNT(UserID) AS NumberOfDrivers, [State], [Type] 
	FROM UserProfile
	GROUP BY [State],[Type]
	HAVING [Type] = 'Driver'
);
SELECT [State], NumberOfDrivers FROM Supply;

CREATE VIEW Demand AS
(
	SELECT COUNT(RequestID) AS NumberOfRides, [State], [Status] 
	FROM Request
	GROUP BY [State], [Status]
	HAVING [Status] = 'Approved'
);
SELECT [State], NumberOfRides FROM Demand;


--7. To calculate total revenue and total earnings of drivers

CREATE VIEW TotalRevenue 
AS
(
	SELECT t1.Revenue, t2.TotalDriverEarning, t3.TotalCarOwnerEarning 
	FROM 
		(SELECT SUM(Price) AS Revenue FROM Invoice) AS t1, 
		(SELECT SUM(TotalEarnings) AS TotalDriverEarning FROM Driver) AS t2, 
		(SELECT SUM(TotalEarnings) AS TotalCarOwnerEarning FROM CarOwner) AS t3
);
SELECT * FROM TotalRevenue;

--8. To view top 3 states with positive customer feedback 

CREATE VIEW StatewiseCustomerStatisfaction 
AS 
(
	SELECT RANK() OVER (ORDER BY Avg(OverAllRating) DESC) AS Ranking, 
	Avg(OverAllRating) AS AvgOverAllRating, [State], [Type] 
	FROM  UserProfile
	WHERE [Type] = 'Driver' 
	GROUP BY [State], [Type]
);
SELECT [State], AvgOverAllRating FROM StatewiseCustomerStatisfaction
WHERE Ranking Between 1 and 3;

--9. To view top 3 states with negative customer feedback

CREATE VIEW StatewiseNegativeFeedback 
AS 
(
	SELECT RANK() OVER (ORDER BY Avg(OverAllRating)) AS Ranking, 
	Avg(OverAllRating) AS AvgOverAllRating, [State], [Type] 
	FROM  UserProfile
	WHERE [Type] = 'Driver' 
	GROUP BY [State], [Type]
);
SELECT [State], AvgOverAllRating FROM StatewiseNegativeFeedback
WHERE Ranking Between 1 and 3;

--10. To view reasons for request cancellation

CREATE VIEW vw_ReasonForCancellations
AS 
(
	SELECT ReasonForCancellation,
			(COUNT(RequestID) * 100.0 /
			(SELECT COUNT(RequestID)
			 FROM dbo.Request
			 WHERE ReasonForCancellation IS NOT NULL)
			)ReasonPercentage
	FROM dbo.Request
	WHERE reasonForCancellation IS NOT NULL
	GROUP BY ReasonForCancellation
);
SELECT * FROM vw_ReasonForCancellations;


/*******************************Table-level CHECK Constraints based on a function**********************************/

--1. Banning the requests for futher rides if rating is low.

CREATE FUNCTION RequestBan
(@riderID varchar(30))
RETURNS varchar(5)
AS
BEGIN
	DECLARE @out varchar(5);
	DECLARE @userID varchar(30)
	DECLARE @count int;

	SELECT @userID = UserID FROM Rider WHERE RiderID = @riderID

	SELECT @count = count(*) FROM dbo.FeedBack
	WHERE UserID = @userID and Rating = 1.0

	IF @count > 5
	SET @out = 'true';

	RETURN @out
END;

ALTER TABLE Request ADD CONSTRAINT BanRequest CHECK (dbo.RequestBan(RiderID)!= 'true');


/*********************************Computed Columns based on a function********************************************/

--1. Distance Calculation based on SourceLocationID and DestinationLocationID (Latitude and Longitude)

CREATE FUNCTION CalculateDistance
(
@sourceLocationID int,
@destinationLocationID int
)
RETURNS decimal(10,6)
AS
BEGIN
DECLARE @Distance decimal(10,6);
DECLARE @sourceLatitude decimal(10,6);
DECLARE @sourceLongitude decimal(10,6);
DECLARE @destinationLatitude decimal(10,6);
DECLARE @destinationLongitude decimal(10,6);

	SELECT  @sourceLatitude = Latitude, @sourceLongitude = Longitude 
	FROM Location WHERE LocationID = @sourceLocationID

	SELECT  @destinationLatitude = Latitude, @destinationLongitude = Longitude 
	FROM Location WHERE LocationID = @destinationLocationID

	SET @Distance = SQRT(POWER(69.1 * ( @destinationLatitude - @sourceLatitude),2) 
					+ POWER(69.1 * ( @sourceLongitude - @destinationLongitude )  
					* COS(@destinationLatitude / 57.3), 2));
RETURN @Distance;
END;
--Example 
select dbo.CalculateDistance(10001,10009);

--2. To Calculate Estimate Trip Amount using distance and Tripcharges entity and then storing it in Request Entity

CREATE FUNCTION EstimateTripAmount
(
@sourceLocationID int,
@destinationLocationID int,
@promoCode varchar(50)
)
RETURNS decimal(10,3)
AS
BEGIN
DECLARE @EstimateTripAmount decimal(10,3);
DECLARE @Distance decimal(10,6);
DECLARE @State varchar(50);
DECLARE @Tax decimal(5,3);
DECLARE @ChargePerKilometer decimal(5,3);
DECLARE @DiscountAmount decimal(5,2);

	SELECT  @State = State FROM Location WHERE LocationID = @sourceLocationID

	SELECT @Tax = Tax, @ChargePerKilometer = ChargePerKilometer 
	FROM TripCharges WHERE State = @State

	IF @promoCode IS NOT NULL
		BEGIN
			SELECT @DiscountAmount = DiscountAmount 
			FROM PromoCode WHERE PromoCode = @promoCode
		END
	ELSE
		BEGIN
			SET @DiscountAmount = 0;
		END

	SET @Distance = dbo.CalculateDistance(@sourceLocationID,@destinationLocationID);

	SET @EstimateTripAmount = ROUND((@Distance * @ChargePerKilometer * (1 + (@Tax/100))) - @DiscountAmount,3);

RETURN @EstimateTripAmount;
END;
--Example
 select dbo.EstimateTripAmount(10001,10009,'EasyFirstRide');

--3. The ApprovedByAdmin Attribute in UserProfile Entity can be updated using the below function which takes the user state as input and correspondingly sends request to particular Admin for Approval

CREATE FUNCTION AdminApprovals
(@State varchar(50))
RETURNS varchar(10)
AS
BEGIN
DECLARE @Admin varchar(10);
SELECT @Admin = 
	CASE
		WHEN @State IN ('Andaman and Nicobar Islands','Andhra Pradesh','Arunachal Pradesh','Assam','Bihar','Chandigarh') 
			THEN 'A1'
		WHEN @State IN ('Chhattisgarh','Dadra and Nagar Haveli','Daman and Diu','Delhi','Goa','Gujarat') 
			THEN 'A2'
		WHEN @State IN ('Haryana','Himachal Pradesh','Jammu and Kashmir','Jharkhand','Karnataka','Kerala') 
			THEN 'A3'
		WHEN @State IN ('Lakshadweep','Madhya Pradesh','Maharashtra','Manipur','Meghalaya','Mizoram') 
			THEN 'A4'
		WHEN @State IN ('Nagaland','Odisha','Puducherry','Punjab','Rajasthan','Sikkim') 
			THEN 'A5'
		WHEN @State IN ('Tamil Nadu','Tripura','Uttar Pradesh','Uttarakhand','West Bengal') 
			THEN 'A6'
	ELSE 'Unknown'
END;
RETURN @Admin;
END;
--Example
select dbo.AdminApprovals('Maharashtra');

--4. To retrieve State from given LocationID

CREATE FUNCTION RetrieveState
(@LocationId int)
RETURNS varchar(50)
AS
BEGIN
	DECLARE @State varchar(50);

	SELECT @State = State FROM Location WHERE LocationID = @LocationId;

RETURN @State
END;
--Example
select dbo.RetrieveState(10001);

--5. To get Nearby Rides based on a given LocationID

CREATE FUNCTION GetNearbyRides
(@LocationID int)
RETURNS TABLE
AS
RETURN
(
	SELECT * FROM RequestRide
	WHERE LocationID = @LocationID
);


/************************************Column Data Encryption & Decryption************************************/

-- Create Database Master Key
create master key encryption by password = 'group5pass';

-- Create certificate to protect symmetric key	
create certificate RideEasyCertificate with subject = 'group5 project rideeasy', expiry_date = '2025-12-31';

-- Create symmetric key 
create symmetric key RideEasySymmetricKey with algorithm = AES_128 encryption by certificate RideEasyCertificate;

-- Open symmetric key 
open symmetric key RideEasySymmetricKey decryption by certificate RideEasyCertificate;

-- Close symmetric key
close symmetric key RideEasySymmetricKey;


/********************************************Triggers*********************************************************/

--1. To trigger whenever a new feedback is given to any User and to calculate Overall Rating based on average of ratings given in feedback for a particular User and then updating the OverallRating in UserProfile Entity.

CREATE TRIGGER OverAllRatingUpdate
ON FeedBack
AFTER INSERT
AS
BEGIN

	SET NOCOUNT ON;
	DECLARE @countAvgRating decimal(2,1);
	DECLARE @userID varchar(11);

	SELECT @userID = i.UserID FROM inserted i

	SELECT @countAvgRating = AVG(Rating) FROM FeedBack WHERE UserID = @userID

	UPDATE UserProfile SET OverallRating = @countAvgRating 
	FROM UserProfile u
	INNER JOIN inserted i
	ON u.UserID = i.UserID

END;

--2. To trigger whenever a Request is Accepted by a Driver, a trip is generated and so the trip details are entered in the Trip table. Also if the source and destination state does not match, it displays equivalent error message.

CREATE TRIGGER RequestApproved_TripGenerated
ON Request
AFTER INSERT,UPDATE
AS
BEGIN
	SET NOCOUNT ON;
	DECLARE @sourceLocationID int;
	DECLARE @destinationLocationID int;
	DECLARE @sourceState varchar(50);
	DECLARE @destinationState varchar(50);
	DECLARE @Status varchar(50);  
	DECLARE @Distance decimal(10,6);
	DECLARE @RequestID varchar(11);

	SELECT @RequestID = RequestID, @sourceLocationID = SourceLocationID, 
		   @destinationLocationID = DestinationLocationID, @Status = Status
	FROM inserted i

	SELECT @sourceState = State FROM Location WHERE LocationID = @sourceLocationID

	SELECT @destinationState = State FROM Location WHERE LocationID = @destinationLocationID

	IF (@sourceState != @destinationState)
		BEGIN
			PRINT 'Source and Destination location too far. Please select Destination location from same state.'
		END

	IF (@sourceState = @destinationState)
		BEGIN
			SET @Distance = dbo.CalculateDistance(@sourceLocationID,@destinationLocationID);
			IF @Status = 'Approved'
				BEGIN
					IF NOT EXISTS( SELECT * FROM Trip WHERE RequestID = @RequestID)
						BEGIN
							INSERT INTO Trip(RequestID,Distance,StartTimestamp) values(@RequestID,@Distance,GETDATE())
						END
				END
		END
END;

--3. To trigger when A trip is Completed, so that an invoice is generated for that particular trip and the car location gets updated in the Cars Entity.

CREATE TRIGGER InvoiceGeneration_TripCompletion
ON Trip
AFTER INSERT,UPDATE
AS
BEGIN
	SET NOCOUNT ON;
	DECLARE @Price decimal(10,3);  
	DECLARE @Status varchar(50);  
	DECLARE @Distance decimal(10,6);
	DECLARE @RequestID varchar(11);
	DECLARE @destinationLocationID int;
	DECLARE @carID varchar(11);

	SELECT @RequestID = RequestID, @Status = Status, @Distance = Distance
	FROM inserted i

	SELECT @Price = EstimationAmount, @destinationLocationID = DestinationLocationID, @carID = CarID
	FROM Request WHERE RequestID = @RequestID

	IF @Status = 'Completed'
		BEGIN
			IF NOT EXISTS( SELECT * FROM Invoice WHERE RequestID = @RequestID)
				BEGIN
					INSERT INTO Invoice(RequestID,Distance,Price) values(@RequestID,@Distance,@Price)
					UPDATE Trip SET DropTimestamp = GETDATE() WHERE RequestID = @RequestID
					UPDATE Cars SET LocationID = @destinationLocationID WHERE CarID = @carID
				END
		END
END;

--4. To trigger whenever a new request is raised by a rider.

CREATE TRIGGER RequestingRide
ON Request
AFTER INSERT
AS
BEGIN
	SET NOCOUNT ON;
	DECLARE @sourecLocationID int;
	SELECT @sourecLocationID = SourecLocationID
	FROM inserted i 
	SELECT * FROM
	dbo.GetNearbyRides(@sourecLocationID);
END;


/********************************************Stored Procedures********************************************/

--1. New user registration using stored procedure

CREATE PROCEDURE CreateNewUser
( 
@UserName varchar(50),
@Password varchar(25),
@FirstName varchar(50),
@LastName varchar(50),
@Street varchar(50),
@Region varchar(50),
@City varchar(50),
@State varchar(50),
@Country varchar(50),
@PhoneNumber varchar(10),
@EmailID varchar(50),
@Type varchar(30)
)
AS BEGIN
	DECLARE @ApprovedByAdmin varchar(10);
	DECLARE @uid varchar(11);
	SET @ApprovedByAdmin = dbo.AdminApprovals(@State);
	INSERT INTO [dbo].[UserProfile]
		VALUES (@ApprovedByAdmin, @UserName, @Password, @FirstName, @LastName, 
				@Street, @Region, @City, @State, @Country, @PhoneNumber, @EmailID, @Type, 0)
	 SELECT @uid = UserID FROM UserProfile WHERE UserName = @UserName
	IF @Type = 'Rider'
		BEGIN
			INSERT INTO [dbo].[Rider] ([UserID], [AmountDue])
						VALUES (@uid, 0.0)
		END
	IF @Type = 'CarOwner'
		BEGIN
			INSERT INTO [dbo].[CarOwner]([UserID], [CompanyName], [ContractType], [TotalEarnings], [ContractDuration])
						VALUES (@uid, NULL, NULL, 0, 0)
			PRINT 'PLEASE PROVIDE CONTRACT DETAILS'
		END
	IF @Type = 'Driver'
		BEGIN
			INSERT INTO [dbo].[Driver] ([UserID], [LicenseNo], [Gender], [TotalEarnings])
						VALUES (@uid, NULL, NULL, 0)
			PRINT 'PLEASE PROVIDE LICENSE NUMBER AND GENDER'
		END
END;

--2. To update Car Owner Profile for entering Contract Details

CREATE PROCEDURE CarOwnerApproval
(
 @UserName varchar(50),
 @CompanyName varchar(30),
 @ContractType varchar(20),
 @Contractduration int
 ) 
AS BEGIN
	DECLARE @Earnings decimal(10,2);

	SET @Earnings = 250 * @Contractduration;

	UPDATE CarOwner SET CompanyName = @CompanyName, ContractType = @ContractType, TotalEarnings = @Earnings, ContractDuration = @Contractduration
	FROM CarOwner c
	INNER JOIN UserProfile u
	ON c.UserID = u.UserID
	WHERE u.UserName = @UserName

END;

--3. To update Driver Profile for entering License DETAILS

CREATE PROCEDURE DriverApproval
(
 @UserName varchar(50),
 @LicenseNo varchar(20),
 @Gender char
) 
AS BEGIN

	UPDATE Driver SET LicenseNo = @LicenseNo, Gender = @Gender
	FROM Driver c
	INNER JOIN UserProfile u
	ON c.UserID = u.UserID
	WHERE u.UserName = @UserName

END;