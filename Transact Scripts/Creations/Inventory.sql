Use MushVsGrump;
GO
--IF OBJECT_ID('dbo.Inventory', 'U') IS NOT NULL 
	--DROP TABLE Inventory;
CREATE TABLE Inventory(
	ItID int,
	num int Not Null,
	PRIMARY KEY(ItID),
	Constraint num_items_constraint	Check (num > -1),
	CONSTRAINT Inv_to_Item FOREIGN KEY (ItID) REFERENCES
		Item (ItID) ON DELETE CASCADE ON UPDATE CASCADE
	)