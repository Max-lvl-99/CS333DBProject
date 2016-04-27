Use MushVsGrump;
GO
IF OBJECT_ID('dbo.Character', 'U') IS NOT NULL 
	DROP TABLE Character;
Create TABLE Character(
	ChID int IDENTITY(1,1),
	ChName varchar(20) UNIQUE CLUSTERED,
	Base_HP float(24) Not Null,
	PRIMARY KEY(ChID) 
	)

ALTER TABLE [dbo].[Character]  WITH CHECK ADD  CONSTRAINT [CK_Character] CHECK  (([BASE_HP]>(0)))
GO

ALTER TABLE [dbo].[Character] CHECK CONSTRAINT [CK_Character]
GO