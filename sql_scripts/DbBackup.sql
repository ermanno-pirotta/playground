SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Piro84
-- Create date: 13/10/2013
-- Description:	Backups a database, and stores it under the specified directory. By convention, the backup file name will be the @DatabaseName_CURRENTDAY (i.e. TESTDB_12-10-2013)
-- It creates the directory on the system running the SQL Server instance if necessary.
--				It requires sysadmin credentials.
-- params: @DatabaseName    the name of the database to backup
--		   @BackupDirectory the destination directory where to store the backup
-- Usage example:
-- EXECUTE [DBNAME].[dbo].[dbBackup] 'DBNAME','c:\dbBackups','test backup from dbBackup stored procedure'
-- =============================================
CREATE PROCEDURE dbBackup
	-- Add the parameters for the stored procedure here
	@DatabaseName VARCHAR(256),
	@BackupDirectory VARCHAR(256),
	@BackupDescription VARCHAR(256)	 
AS
BEGIN
	DECLARE @CurrentDate VARCHAR(10)
	SELECT @CurrentDate  = CONVERT(VARCHAR, CURRENT_TIMESTAMP, 105);

	DECLARE @BackupFileName VARCHAR(267)
	SET @BackupFileName=@DatabaseName + '_' + @CurrentDate;

	DECLARE @BackupFileFullPath VARCHAR(524)
	SET @BackupFileFullPath= @BackupDirectory+ '\' + @BackupFileName;

	SET @BackupDirectory=''+ @BackupDirectory + ''
	SET @BackupDescription=''+ @BackupDescription + '';

	--create the backup directory if it does not exist
	EXEC master.dbo.xp_create_subdir @BackupDirectory

	BACKUP DATABASE @DatabaseName
	TO DISK = @BackupFileFullPath
	   WITH FORMAT,
	   SKIP,
	   NAME = @BackupDescription;

END
GO