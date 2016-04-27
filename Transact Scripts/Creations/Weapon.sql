USE [MushVsGrump]
GO

/****** Object:  Table [dbo].[Weapon]    Script Date: 4/21/2016 4:57:14 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Weapon](
	[WeID] [int] IDENTITY(1,1) NOT NULL,
	[WeName] [varchar](20) NOT NULL,
	[Attack_Name] [varchar](30) NOT NULL,
	[Base_Damage] [real] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[WeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALter Table Weapon Add Constraint We_dmg_pos Check (Base_Damage >= 0);

SET ANSI_PADDING OFF
GO

