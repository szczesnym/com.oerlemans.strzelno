USE [111]
GO

/****** Object:  Table [dbo].[pldmDocumentLines]    Script Date: 2018-10-23 16:24:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[pldmDocumentLines](
	[ID] [int] NOT NULL,
	[DocumentType] [varchar](10) NOT NULL,
	[DocumentNumber] [int] NOT NULL,
	[DocumentDate] [datetime] NOT NULL,
	[LineNumber] [int] NULL,
	[ItemCode] [varchar](30) NULL,
	[ItemDescription] [varchar](60) NULL,
	[Quantity] [decimal](13, 5) NULL,
	[WarehouseLocation] [varchar](60) NULL,
	[MeasureUnit] [varchar](10) NULL,
	[Amount] [money] NULL,
	[StockAccount] [varchar](9) NULL,
	[BatchNr] [varchar](20) NULL,
	[QuantityRealized] [decimal](13, 5) NULL,
	[QuantityToRealize] [decimal](13, 5) NULL,
	[LastRealizedQuantity] [decimal](13, 5) NULL,
	[Notes] [varchar](25) NULL,
	[HeaderGUID] [uniqueidentifier] NULL,
	[LineGUID] [uniqueidentifier] NULL,
	[CostCenter] [varchar](8) NULL,
	[Project] [varchar](30) NULL,
	[ResID] [int] NULL,
	[CreatedBy] [varchar](30) NULL,
	[CreationDate] [datetime] NULL,
	[CostAccount] [varchar](9) NULL,
	[CostUnit] [varchar](8) NULL
) ON [PRIMARY]
GO


