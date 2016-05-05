Use MushVsGrump
Go

ALTER PROCEDURE [dbo].[InsertIntoInventory]
	(@InID [int],
	@ItID [int],
	@quantity [int] = 1)
AS

	INSERT INTO Inventory
		(InID, ItID, num)
	VALUES(@InID, @ItId, @quantity)		

GO