Create Proc [registerNewUser]
	(@Username	varchar(20),
	@Password	nvarchar(40))
AS
--Validate parameters (make sure Username doesn't already exist
If (Select Count(*) From Login Where Username = @Username) > 0
Begin
	Print 'The username ' + Convert(varchar(30), @Username) + ' already exists'
	return 1;
End
Insert Into Login (Username, Password)
	Values (@Username, @Password);
return 0;
Go