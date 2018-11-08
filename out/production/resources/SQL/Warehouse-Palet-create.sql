USE [Warehouse]
GO

/****** Object:  Table [dbo].[Palets]    Script Date: 2018-10-23 16:25:15 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Palets](
	[id] [int] IDENTITY(11,1) NOT NULL,
	[item_id] [varchar](40) NULL,
	[system_id] [varchar](10) NULL,
	[best_before] [datetime] NULL,
	[weight] [numeric](10, 2) NULL,
	[lot] [varchar](30) NULL,
	[sscc] [varchar](40) NULL,
	[palet_id] [int] NULL,
	[count] [numeric](10, 2) NULL,
	[content_id] [varchar](10) NULL,
	[_active] [int] NULL,
	[batchLot] [varchar](60) NULL,
	[description] [varchar](60) NULL,
	[engDescription] [varchar](60) NULL,
	[toBePrinter] [int] NULL,
	[eanCode] [varchar](20) NULL,
	[stamp] [datetime] NULL,
	[workOrder] [varchar](50) NULL
) ON [PRIMARY]
GO


