
alter proc displayWeapons
	(@InID int)
AS
	SELECT ItName AS Name, num
	FROM Item, Inventory
	Where Inventory.ItID = Item.ItID AND Inventory.InID = @InID And 
	Type = 'W';
GO