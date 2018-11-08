USE [111]
GO

/****** Object:  Table [dbo].[pldmDocumentHeaders]    Script Date: 2018-10-23 16:24:12 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[pldmDocumentHeaders](
	[ID] [int] NOT NULL,
	[DocumentType] [varchar](10) NOT NULL,
	[DocumentNumber] [int] NULL,
	[DocumentDate] [datetime] NOT NULL,
	[Warehouse] [varchar](4) NOT NULL,
	[IncomeIssue] [varchar](1) NULL,
	[Account] [varchar](9) NULL,
	[CostCenter] [varchar](8) NULL,
	[CostUnit] [varchar](8) NULL,
	[Project] [varchar](30) NULL,
	[Prefix] [varchar](10) NULL,
	[Description] [varchar](30) NULL,
	[Journalized] [varchar](5) NULL,
	[TypeDescription] [varchar](30) NULL,
	[YourRef] [varchar](30) NULL,
	[Confirmed] [varchar](1) NULL,
	[LastJournalizeDate] [datetime] NULL,
	[HeaderGUID] [uniqueidentifier] NULL,
	[ResID] [int] NULL,
	[CreatedBy] [varchar](30) NULL,
	[CreationDate] [datetime] NULL,
	[ClientCode] [varchar](20) NULL,
	[ClientName] [varchar](50) NULL,
	[Approved] [int] NULL,
	[ApprovedBy] [varchar](30) NULL,
	[ApproveDate] [datetime] NULL
) ON [PRIMARY]
GO


