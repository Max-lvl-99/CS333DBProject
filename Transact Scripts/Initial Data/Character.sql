Use MushVsGrump;
GO
delete from Character;
DBCC CHECKIDENT ('Character', RESEED, 0);
go
INSERT INTO Character (ChName, Base_HP)
	VALUES ('User', 10);
INSERT INTO Character (ChName, Base_HP)
	VALUES ('Maniacal Lawyer', 8);
INSERT INTO Character (ChName, Base_HP)
	VALUES ('Violent Redneck', 9);
INSERT INTO Character (ChName, Base_HP)
	VALUES ('Cynical CEO', 13);
INSERT INTO Character (ChName, Base_HP)
	VALUES ('Potent Pencil Pusher', 10);
--INSERT INTO Character (ChName, Base_HP)
	--VALUES ('Abominable Accountant', 11);