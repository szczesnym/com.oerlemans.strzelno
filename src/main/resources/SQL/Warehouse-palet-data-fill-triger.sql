USE [Warehouse]
GO
/****** Object:  Trigger [dbo].[update_palet_data]    Script Date: 2018-10-17 16:32:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER TRIGGER [dbo].[update_palet_data]
   ON  [dbo].[Palets]
   AFTER INSERT
AS 
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	declare @mfgItem varchar(60);
	declare @bestBefore datetime;
	declare @description varchar(80);
	declare @engDescription varchar(80);
	declare @itemCount int;
	declare @itemEan varchar(30);
	declare @_itemId varchar(40);
	declare @paletNo varchar(7);
	
	set @_itemId = (select item_id from inserted);
	set @paletNo = (select count(id) from palets);


	select @mfgItem = UserField_02,
			@description = Description,
			@engDescription = UserField_04,
			@itemEan = UserField_03,
			@itemCount = UserNumber_08,
			@bestBefore = dateadd(month,UserNumber_04, getdate()) 
		 from [111].[dbo].[Items] where ItemCode = @_itemId;

	update Palets set content_id = @mfgItem,
						 description=@description, 
						 engDescription=@engDescription, 
						 eanCode=@itemEan,
						 count = @itemCount,
						 palet_id = cast('7' + [Warehouse].dbo.CIntToChar(@paletNo,6) as int),
						 best_before = @bestBefore,
						 stamp = getdate(),
						 _active = 1,
						 toBePrinter = 1
				 where id = (select id from inserted);

    -- Insert statements for trigger here

END
