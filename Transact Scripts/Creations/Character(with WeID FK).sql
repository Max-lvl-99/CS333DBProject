USE [MushVsGrump]
GO

/****** Object:  Table [dbo].[Character]    Script Date: 5/4/2016 11:24:26 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Character](
	[ChID] [int] IDENTITY(1,1) NOT NULL,
	[ChName] [varchar](20) NULL,
	[CurrentlyWielding] [int] NULL,
	[Base_HP] [real] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ChID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[Character] ADD  DEFAULT ((0)) FOR [CurrentlyWielding]
GO

ALTER TABLE [dbo].[Character]  WITH CHECK ADD FOREIGN KEY([CurrentlyWielding])
REFERENCES [dbo].[Weapon] ([WeID])
GO

ALTER TABLE [dbo].[Character]  WITH CHECK ADD  CONSTRAINT [FK_Is_Weapon] FOREIGN KEY([CurrentlyWielding])
REFERENCES [dbo].[Weapon] ([WeID])
GO

ALTER TABLE [dbo].[Character] CHECK CONSTRAINT [FK_Is_Weapon]
GO

ALTER TABLE [dbo].[Character]  WITH CHECK ADD  CONSTRAINT [CK_Character] CHECK  (([BASE_HP]>(0)))
GO

ALTER TABLE [dbo].[Character] CHECK CONSTRAINT [CK_Character]
GO


