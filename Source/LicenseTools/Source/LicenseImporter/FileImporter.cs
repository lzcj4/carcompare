using Common;
using Microsoft.Win32;
using System;
using System.IO;

namespace LicenseImporter
{
    class FileImporter
    {
        private WinServiceHelper winServiceHelper;        

        public FileImporter(WinServiceHelper winServiceHelper)
        {
            this.winServiceHelper = winServiceHelper;
        }

        public void Execute(string file)
        {
            CheckFile(file);

            TryStopWinService();

            CheckWinService();

            CopyFile(file);
            WriteVersion(file);
            ClearSvrTimeFile();

            TryStartWinService();
        }

        private void TryStopWinService()
        {
            if (!AppSettings.Automation)
            {
                return;
            }

            this.winServiceHelper.Stop();
        }

        private void TryStartWinService()
        {
            if (!AppSettings.Automation)
            {
                return;
            }

            this.winServiceHelper.Start();
        }

        /// <summary>
        /// 检查车型网服务状态
        /// </summary>
        private void CheckWinService()
        {
            if (!this.winServiceHelper.IsStoped())
            {
                throw new ApplicationException("请先停止车型网服务");
            }
        }        

        /// <summary>
        /// 检查文件
        /// </summary>
        /// <param name="file"></param>
        private void CheckFile(string file)
        {
            if (string.IsNullOrWhiteSpace(file))
            {
                throw new ApplicationException("请选择一个许可证文件");
            }

            if (!File.Exists(file))
            {
                throw new ApplicationException("许可证文件不存在");
            }

            var lrcContent = File.ReadAllText(file);

            bool isExistsLrcVerFile = File.Exists(Consts.LRC_VER_FILE);
            if (isExistsLrcVerFile)
            {
                var lrcVerFileContent = File.ReadAllText(Consts.LRC_VER_FILE);
                if (lrcVerFileContent.IndexOf(lrcContent) > -1)
                {
                    throw new ApplicationException("许可证文件已失效，请重新申请");
                }
            }     
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="file"></param>
        private void CopyFile(string file)
        {
            var licenseDir = GetLicenseDir();
            if (licenseDir == null)
            {
                throw new ApplicationException("请先安装车型网");
            }

            try
            {
                string sourceFile = file;                
                string destFile = Path.Combine(GetLicenseDir(), new FileInfo(file).Name);
                File.Copy(sourceFile, destFile, true);
            }
            catch(Exception ex)
            {
                Logger.Error(ex.StackTrace);
                throw new ApplicationException("文件导入失败");
            }
        }                       

        /// <summary>
        /// 记录文件版本
        /// </summary>
        private void WriteVersion(string file)
        {
            var isLrcVerFileExists = File.Exists(Consts.LRC_VER_FILE);
            if (!isLrcVerFileExists)
            {
                var fileStream = File.Create(Consts.LRC_VER_FILE);
                fileStream.Close();
                fileStream.Dispose();
                fileStream = null;

                File.SetAttributes(Consts.LRC_VER_FILE, FileAttributes.Hidden);
            }

            var lrcContent = File.ReadAllText(file);

            File.AppendAllText(Consts.LRC_VER_FILE, lrcContent);
        }

        /// <summary>
        /// 清除服务器时间记录文件
        /// </summary>
        private void ClearSvrTimeFile(int retryCount = 0)
        {
            if (retryCount > 3)
            {
                return;
            }

            try
            {
                retryCount++;
                File.Delete(Consts.SVR_TIME_FILE);
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message);
                ClearSvrTimeFile(retryCount);
            }
        }

        /// <summary>
        /// 获取License存放路径
        /// </summary>
        /// <returns></returns>
        private string GetLicenseDir()
        {
            object value = Registry.GetValue(Consts.TOMCAT_REG_PATH, "InstallLocation", null);
            if (value == null)
            {
                return null;
            }

            return Path.Combine(value.ToString(), @"webapps\ROOT\WEB-INF\classes");
        }        
    }
}
