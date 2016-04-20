USE [MushVsGrump]
GO

/****** Object:  Table [dbo].[Level]    Script Date: 4/20/2016 9:26:25 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Level](
	[Level_num] [tinyint] NOT NULL,
	[Exp] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[Level_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

