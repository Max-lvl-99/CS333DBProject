USE [MushVsGrump]
GO
/****** Object:  Trigger [dbo].[addFist]    Script Date: 5/13/2016 8:21:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
drop TRIGGER [dbo].[addFist] ON [dbo].[User_Character] AFTER INSERT AS
	DECLARE @id int
	SELECT @id = InId FROM inserted
	BEGIN
		INSERT INTO Inventory(InID, ItID, num, Type)
		VALUES (@id, 1, 1, 'W')
	END