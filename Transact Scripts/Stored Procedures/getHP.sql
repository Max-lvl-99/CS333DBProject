USE [MushVsGrump]
GO
/****** Object:  StoredProcedure [dbo].[getHP]    Script Date: 5/10/2016 10:53:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[getHP]
	-- Add the parameters for the stored procedure here
	@Id int,
	@HP real OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT @HP = Actual_hp FROM User_Character WHERE ChId = @Id
	RETURN
END
