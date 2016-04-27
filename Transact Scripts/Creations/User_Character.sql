Use MushVsGrump;
GO
IF OBJECT_ID('dbo.User_Character', 'U') IS NOT NULL 
	DROP TABLE User_Character;
CREATE TABLE User_Character(
	ChID int,
	InID int Not Null,
	Exp float Default 0,
	Username nvarchar(20) Not Null,
	PRIMARY KEY(ChID),
	CONSTRAINT UCh_to_Ch FOREIGN KEY (ChID) REFERENCES
		Character (ChID) ON DELETE CASCADE ON UPDATE CASCADE,
	)