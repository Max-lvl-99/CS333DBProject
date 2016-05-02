Use MushVsGrump;
Go
CREATE TABLE UsernameToUserCharacters(
	Username varchar(20) Not Null,
	ChID int Not Null,
	PRIMARY KEY(Username, ChID),
	CONSTRAINT FK_Username_to_ChID FOREIGN KEY (ChID) REFERENCES
		Character (ChID) ON DELETE CASCADE ON UPDATE CASCADE,
)