Use MushVsGrump;
GO
CREATE TABLE User_Character(
	ChID int,
	InID int Identity(1,1),
	Floor int default 1,
	Room int default 1,
	Exp float Default 0,
	PRIMARY KEY(ChID),
	CONSTRAINT UCh_to_Ch FOREIGN KEY (ChID) REFERENCES
		Character (ChID) ON DELETE CASCADE ON UPDATE CASCADE,
	)