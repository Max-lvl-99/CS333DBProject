Use MushVsGrump;
GO
IF OBJECT_ID('dbo.Item', 'U') IS NOT NULL 
	DROP TABLE Item;
CREATE TABLE Item(
	ItID int Identity(1,1),
	ItName varchar(40),
	--This is the item description the user will see.
	ItDesc varchar(600),
	PRIMARY KEY(ItID)
	)

IF OBJECT_ID('dbo.Inventory', 'U') IS NOT NULL 
	DROP TABLE Inventory;
CREATE TABLE Inventory(
	InID int Identity(1,1),
	ItID int,
	PRIMARY KEY(InID),
	CONSTRAINT Inv_to_Item FOREIGN KEY (ItID) REFERENCES
		Item (ItID) ON DELETE CASCADE ON UPDATE CASCADE
	)

IF OBJECT_ID('dbo.Character', 'U') IS NOT NULL 
	DROP TABLE Character;
CREATE TABLE Character(
	ChID int IDENTITY(1,1),
	ChName varchar(20),
	Exp float,
	Base_HP float(24),
	PRIMARY KEY(ChID) 
	)

CREATE TABLE User_Character(
	ChID int,
	InID int,
	PRIMARY KEY(ChID),
	CONSTRAINT UCh_to_Ch FOREIGN KEY (ChID) REFERENCES
		Character (ChID) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT UCh_to_Inv FOREIGN KEY (InID) REFERENCES 
		Inventory (InID) ON DELETE CASCADE ON UPDATE CASCADE
	)