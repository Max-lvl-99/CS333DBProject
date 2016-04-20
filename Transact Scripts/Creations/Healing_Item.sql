USE [MushVsGrump]
GO

/****** Object:  Table [dbo].[Healing_Item]    Script Date: 4/19/2016 4:53:58 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Healing_Item](
	[ItID] [int] NOT NULL,
	[HP_Healed] [int] NOT NULL
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[Healing_Item]  WITH CHECK ADD  CONSTRAINT [FK_Healing_Item_Item] FOREIGN KEY([ItID])
REFERENCES [dbo].[Item] ([ItID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Healing_Item] CHECK CONSTRAINT [FK_Healing_Item_Item]
GO

