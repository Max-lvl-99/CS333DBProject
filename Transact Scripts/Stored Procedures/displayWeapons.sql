
alter proc displayWeapons
	(@InID int)
AS
	SELECT WeName AS Name, num
	FROM Item, Inventory, Weapon
	Where Weapon.WeID = Item.ItID and Inventory.ItID = Item.ItID AND Inventory.InID = @InID And 
	Type = 'W';
GO