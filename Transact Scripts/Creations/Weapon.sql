USE [MushVsGrump]
GO

/****** Object:  Table [dbo].[Weapon]    Script Date: 4/20/2016 9:12:42 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Weapon](
	[WeID] [int] NOT NULL,
	[WeName] [varchar](20) NULL,
	[Attack_Name] [varchar](30) NULL,
	[Base_Damage] [real] NULL,
PRIMARY KEY CLUSTERED 
(
	[WeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

