Use MushVsGrump;
GO
IF OBJECT_ID('dbo.Character', 'U') IS NOT NULL 
	DROP TABLE Character;
CREATE TABLE Character(
	ChID int IDENTITY(1,1),
	ChName varchar(20),
	Exp float,
	Base_HP float(24),
	PRIMARY KEY(ChID) 
	)