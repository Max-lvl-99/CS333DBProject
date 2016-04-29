Create Proc [checkUNameAndPass]
	(@Username	varchar(20),
	@Password	nvarchar(40))
AS
If (Select Count(*) From Login Where Username = @Username
	AND Password = @Password) < 1
Begin
	Print 'This username and password combination does not exist in ' +
	'this database'
	return 1;
End
return 0;
Go