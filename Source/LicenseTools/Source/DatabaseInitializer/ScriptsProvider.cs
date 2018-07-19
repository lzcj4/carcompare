

using System;
using System.IO;
using System.Text;
using System.Windows.Forms;

namespace DatabaseInitializer
{
    public class ScriptsProvider
    {
        private const string FILE_NAME = "init.sql";

        public string GetScripts()
        {           
            if (!File.Exists(GetFile()))
            {
                throw new ApplicationException(string.Format("脚本文件{0}不存在", FILE_NAME));
            }

            StringBuilder sb = new StringBuilder();

            var lines = File.ReadAllLines(GetFile());
            foreach (var line in lines)
            {
                if (string.IsNullOrWhiteSpace(line))
                {
                    continue;
                }

                sb.Append(line);
            }

            return sb.ToString();
        }

        private string GetFile()
        {
            return Path.Combine(Application.StartupPath, "SQL", FILE_NAME);
        }
    }
}
