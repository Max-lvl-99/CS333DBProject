Use MushVsGrump
Go

ALTER PROCEDURE [dbo].[Display Inventory]
	
AS

	SELECT ItName AS Name,ItDesc AS 'Description'
	FROM Item, Inventory
	Where Inventory.ItID = Item.ItID

GO