using System;
using System.Diagnostics;
using System.IO;
using System.Windows.Forms;

namespace LicenseGen.RSA
{
    /// <summary>
    /// 基于文件的密钥提供类
    /// </summary>
    public class FileRSAKeyProvider : IRSAKeyProvider
    {
        /// <summary>
        /// 私钥文件名
        /// </summary>
        const string PRIVATE_FILE = "private.key";

        /// <summary>
        /// 公钥文件名
        /// </summary>
        const string PUBLIC_FILE = "public.key";

        public string GetPrivateKey()
        {
            try
            {
                return File.ReadAllText(GetPrivateFile());
            }
            catch(Exception ex)
            {
                EventLog.WriteEntry("LicenseGen.FileRSAKeyProvider.GetPrivateKey()", ex.Message);
                throw new ApplicationException("读取私钥文件失败");
            }            
        }

        public string GetPublicKey()
        {
            try
            {
                return File.ReadAllText(GetPublicFile());
            }
            catch (Exception ex)
            {
                EventLog.WriteEntry("LicenseGen.FileRSAKeyProvider.GetPublicKey()", ex.Message);
                throw new ApplicationException("读取公钥文件失败");
            }
        }

        private string GetPrivateFile()
        {
            return Path.Combine(Application.StartupPath, PRIVATE_FILE);
        }

        private string GetPublicFile()
        {
            return Path.Combine(Application.StartupPath, PUBLIC_FILE);
        }
    }
}
