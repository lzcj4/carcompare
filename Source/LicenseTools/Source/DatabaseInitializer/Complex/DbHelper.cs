using DatabaseInitializer.Core;
using MySql.Data.MySqlClient;
using System;
using System.Diagnostics;
using System.IO;

namespace DatabaseInitializer.Complex
{
    public class DbHelper
    {
        private const string LOG_SOURCE = "DatabaseInitializer";

        private Settings settings;

        public DbHelper(Settings settings)
        {
            this.settings = settings;
        }

        public void CreateNewDB()
        {
            string filePath = Backup();

            CreateDBIfNotExists();

            Restore(filePath);

            DeleteBackup(filePath);
        }

        /// <summary>
        /// 备份数据库
        /// </summary>
        /// <returns></returns>
        private string Backup()
        {
            try
            {
                String filePath = GetFile();

                String command = BuildBackupCommand(filePath);

                //获取mysqldump.exe所在路径
                String appDirecroty = GetMySQLDumpPath();

                CMD.StartCmd(appDirecroty, command);

                return filePath;
            }
            catch (Exception ex)
            {
                EventLog.WriteEntry(LOG_SOURCE, ex.StackTrace);

                throw new ApplicationException("备份数据库失败");
            }
        }

        /// <summary>
        /// 还原数据库
        /// </summary>
        private void Restore(string filePath)
        {
            try
            {
                String command = BuildRestoreCommand(filePath);

                //获取mysqldump.exe所在路径
                String appDirecroty = GetMySQLDumpPath();

                CMD.StartCmd(appDirecroty, command);
            }
            catch (Exception ex)
            {
                EventLog.WriteEntry(LOG_SOURCE, ex.StackTrace);

                throw new ApplicationException("还原数据库失败");
            }
        }

        /// <summary>
        /// 删除备份数据库
        /// </summary>
        private void DeleteBackup(string filePath)
        {
            FileInfo fileInfo = new FileInfo(filePath);
            fileInfo.Delete();
        }

        private void CreateDBIfNotExists()
        {
            string connectionString = string.Format("Data Source={0};Persist Security Info=yes;UserId={1}; PWD={2};",
                this.settings.DbHost,
                this.settings.DbUserId, 
                this.settings.DbPassword);

            string command = string.Format("CREATE DATABASE {0};", this.settings.DbTarget);

            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();
                using (MySqlCommand cmd = new MySqlCommand(command, conn))
                {
                    cmd.ExecuteNonQuery();
                }
                conn.Close();
            }
        }

        private string GetFile()
        {
            return Path.GetTempFileName();
        }

        private string GetMySQLDumpPath()
        {
            return Path.Combine(System.Configuration.ConfigurationManager.AppSettings.Get("MySQLInstallLocation"), "bin");
        }

        /// <summary>
        /// 构建备份数据库命令
        /// </summary>
        /// <param name="filePath"></param>
        /// <returns></returns>
        private string BuildBackupCommand(string filePath)
        {
            return string.Format("mysqldump --host={0} --default-character-set=utf8 --lock-tables  --routines --force --port=3306 --user={1} --password={2} --quick  {3} > {4}", 
                this.settings.DbHost, 
                this.settings.DbUserId, 
                this.settings.DbPassword, 
                this.settings.DbSource,
                filePath);
        }

        /// <summary>
        /// 构建还原数据库命令
        /// </summary>
        /// <param name="filePath"></param>
        /// <returns></returns>
        private string BuildRestoreCommand(string filePath)
        {
            return string.Format("mysql --host={0} --default-character-set=utf8 --port=3306 --user={1} --password={2} {3} < {4}",
                this.settings.DbHost,
                this.settings.DbUserId,
                this.settings.DbPassword,
                this.settings.DbTarget,
                filePath);
        }
    }
}
