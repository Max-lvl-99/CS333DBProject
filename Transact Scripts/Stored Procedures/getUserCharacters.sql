Alter Proc [getUserCharacters]
	(@Username	varchar(20))
AS
Declare @ChIDs Table (ChIDT int);
Insert Into @ChIDs (ChIDT) 
	Select ChID From UsernameToUserCharacters Where Username = @Username;
Declare @Usernames Table (UsernameT varchar(20));
DECLARE @MyCursor CURSOR;
Declare @UserN varchar(20);
BEGIN
    SET @MyCursor = CURSOR FOR
    select ChIDT From @ChIDs; 
    OPEN @MyCursor 
    FETCH NEXT FROM @MyCursor Into @UserN; 
    Insert Into @Usernames (UsernameT)
	Values (@UserN);
    WHILE @@FETCH_STATUS = 0
    BEGIN
		FETCH NEXT FROM @MyCursor Into @UserN;
		Insert Into @Usernames (UsernameT)
		Values (@UserN); 
    END; 
    CLOSE @MyCursor;
    DEALLOCATE @MyCursor;
END;
Select UsernameT From @Usernames;
return;