using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LicenseImporter
{
    public class Consts
    {
        /// <summary>
        /// 许可证文件名
        /// </summary>
        public const string LICENSE_FILE_NAME = "license.lrc";

        /// <summary>
        /// 服务器时间记录文件路径+名称
        /// </summary>
        public const string SVR_TIME_FILE = @"c:\windows\system32\svrtime.dll";

        /// <summary>
        /// 许可证版本记录文件路径+名称
        /// </summary>
        public const string LRC_VER_FILE = @"c:\windows\iccplrcver.dll";

        /// <summary>
        /// 车型网TOMCAT注册表路径
        /// </summary>
        public const string TOMCAT_REG_PATH = @"HKEY_LOCAL_MACHINE\Software\Wow6432Node\Microsoft\Windows\CurrentVersion\Uninstall\{B7B24E8E-4F03-41B6-9784-3432A6C8F3F7}_is1";
    }
}
