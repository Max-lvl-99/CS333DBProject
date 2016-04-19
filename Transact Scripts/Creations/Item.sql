Use MushVsGrump;
GO
IF OBJECT_ID('dbo.Item', 'U') IS NOT NULL 
	DROP TABLE Item;
CREATE TABLE Item(
	ItID int,
	ItName varchar(20),
	PRIMARY KEY(ItID)
	)