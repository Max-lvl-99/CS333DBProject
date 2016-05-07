
alter proc displayWeapons
	(@ChID int)
AS
	SELECT WeName AS Name, Number
	FROM Has as H, Weapon As W
	Where W.WeID = H.WeID and H.ChId = @ChID;
GO