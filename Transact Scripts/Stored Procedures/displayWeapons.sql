
alter proc displayWeapons
	(@ChID int)
AS
	SELECT WeName AS Name, num As Number
	FROM Has as H, Weapon As W
	Where W.WeID = H.ChId and H.ChId = @ChID;
GO