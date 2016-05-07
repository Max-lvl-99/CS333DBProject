USE [MushVsGrump]
GO

/****** Object:  Table [dbo].[Has]    Script Date: 4/19/2016 5:01:12 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Has](
	[ChId] [int] NOT NULL,
	[WeID] [int] NOT NULL,
	Number int default 1
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[Has]  WITH CHECK ADD  CONSTRAINT [FK_Has_Character] FOREIGN KEY([ChId])
REFERENCES [dbo].[Character] ([ChID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Has] CHECK CONSTRAINT [FK_Has_Character]
GO

ALTER TABLE [dbo].[Has]  WITH CHECK ADD  CONSTRAINT [FK_Has_Weapon] FOREIGN KEY([WeID])
REFERENCES [dbo].[Weapon] ([WeID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Has] CHECK CONSTRAINT [FK_Has_Weapon]
GO

