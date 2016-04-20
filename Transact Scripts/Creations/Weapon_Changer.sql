USE [MushVsGrump]
GO

/****** Object:  Table [dbo].[Weapon_Changer]    Script Date: 4/19/2016 4:58:46 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Weapon_Changer](
	[ItID] [int] NOT NULL,
	[Amt_Changed] [int] NOT NULL,
	[Weapon_Type] [varchar](12) NOT NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[Weapon_Changer]  WITH CHECK ADD  CONSTRAINT [FK_Weapon_Changer_Item] FOREIGN KEY([ItID])
REFERENCES [dbo].[Item] ([ItID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Weapon_Changer] CHECK CONSTRAINT [FK_Weapon_Changer_Item]
GO

ALTER TABLE [dbo].[Weapon_Changer]  WITH CHECK ADD  CONSTRAINT [FK_Weapon_Changer_WeaponType] FOREIGN KEY([Weapon_Type])
REFERENCES [dbo].[WeaponType] ([WeaponType])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Weapon_Changer] CHECK CONSTRAINT [FK_Weapon_Changer_WeaponType]
GO

