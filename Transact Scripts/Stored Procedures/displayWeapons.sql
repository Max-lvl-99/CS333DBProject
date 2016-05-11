
alter proc displayWeapons
	(@ChID int)
AS
	SELECT WeName AS Name, H.ChId, H.WeID
	FROM Has as H, Weapon As W, Weapon_Changer as wc, Inventory as i
	Where W.WeID = H.WeID and H.ChId = @ChID
	group by H.WeID, H.ChId, W.Base_Damage, WeName;
GO