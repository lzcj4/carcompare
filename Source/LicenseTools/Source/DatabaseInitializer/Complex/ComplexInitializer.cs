using Common;
using DatabaseInitializer.Core;
using MySql.Data.MySqlClient;
using System;
using System.IO;

namespace DatabaseInitializer.Complex
{
    /// <summary>
    /// 复杂初始化器
    /// </summary>
    public class ComplexInitializer : IInitializer
    {
        private const string LOG_SOURCE = "DatabaseInitializer";

        private Settings settings;
        private ScriptsProvider scriptsProvider;

        public event Action<string> ProgressChanged;

        public ComplexInitializer(Settings settings, ScriptsProvider scriptsProvider)
        {
            this.settings = settings;
            this.scriptsProvider = scriptsProvider;
        }

        public void Initialize()
        {
            RaiseProgressChanged("正在备份源数据库...");
            string filePath = Backup();

            RaiseProgressChanged("正在创建目标数据库...");
            CreateDBIfNotExists();

            RaiseProgressChanged("正在还原目标数据库...");
            Restore(filePath);

            RaiseProgressChanged("正在删除备份文件...");
            DeleteBackup(filePath);

            RaiseProgressChanged("正在执行初始化脚本...");
            ExecuteScripts();

            RaiseProgressChanged("初始化完成...");
        }

        /// <summary>
        /// 备份数据库
        /// </summary>
        /// <returns></returns>
        private string Backup()
        {
            String filePath = GetFile();

            String command = BuildBackupCommand(filePath);

            //获取mysqldump.exe所在路径
            String appDirecroty = GetMySQLDumpPath();

            CmdResult result = CMD.StartCmd(appDirecroty, command);

            if (result.HasError)
            {
                Logger.Error(LOG_SOURCE, result.Error);
                throw new ApplicationException("备份数据库失败");
            }

            return filePath;
        }

        /// <summary>
        /// 还原数据库
        /// </summary>
        private void Restore(string filePath)
        {
            String command = BuildRestoreCommand(filePath);

            //获取mysqldump.exe所在路径
            String appDirecroty = GetMySQLDumpPath();

            CmdResult result = CMD.StartCmd(appDirecroty, command);

            if (result.HasError)
            {
                Logger.Error(LOG_SOURCE, result.Error);
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

        /// <summary>
        /// 创建数据库（如果不存在）
        /// </summary>
        private void CreateDBIfNotExists()
        {
            string connectionString = string.Format("Data Source={0};Persist Security Info=yes;UserId={1}; PWD={2};",
                this.settings.DbHost,
                this.settings.DbUserId,
                this.settings.DbPassword);

            string command = string.Format("CREATE DATABASE IF NOT EXISTS {0};", this.settings.DbTarget);

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

        /// <summary>
        /// 执行初始化脚本
        /// </summary>
        private void ExecuteScripts()
        {
            var scripts = this.scriptsProvider.GetScripts();

            var connectionString = string.Format("Data Source={0};port=3306;Initial Catalog={1};user id={2};password={3};charset=utf8;", 
                this.settings.DbHost,
                this.settings.DbTarget,
                this.settings.DbUserId,
                this.settings.DbPassword);

            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();
                using (MySqlCommand cmd = new MySqlCommand("", conn))
                {
                    MySqlTransaction transaction = conn.BeginTransaction();
                    cmd.Transaction = transaction;

                    cmd.CommandText = scripts;

                    try
                    {
                        int res = cmd.ExecuteNonQuery();
                        cmd.Transaction.Commit();
                    }
                    catch (Exception ex)
                    {
                        cmd.Transaction.Rollback();
                        Logger.Error(ex.StackTrace);
                    }
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

        private void RaiseProgressChanged(string text)
        {
            if (ProgressChanged != null)
            {
                ProgressChanged(text);
            }
        }
    }
}
