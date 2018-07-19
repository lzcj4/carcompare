using Common;
using Microsoft.Win32;
using System;
using System.Management;
using System.Net.NetworkInformation;
using System.Security.Cryptography;

namespace MachineCodeGen
{
    /// <summary>
    /// 机器码生成类
    /// </summary>
    public class CodeGenerator
    {
        const string PREFIX = "COMPUTER";

        const string DISK_DRIVE = "C";

        public static string Generate()
        {
            string cpu = GetCPUNumber();
            string disk = GetDiskNumber();
            string mac = GetMac();

            string code = String.Concat(PREFIX, ".", cpu, ".", disk, ".", mac);

            //Logger.Error(string.Format("机器码：{0}", code));

            return MD5(code);
        }

        /// <summary>
        /// 获得CPU的序列号
        /// </summary>        
        private static string GetCPUNumber()
        {
            string number = null;
            ManagementClass myCpu = new ManagementClass("win32_Processor");
            ManagementObjectCollection myCpuConnection = myCpu.GetInstances();
            foreach (ManagementObject myObject in myCpuConnection)
            {
                number = myObject.Properties["Processorid"].Value.ToString();
                break;
            }
            return number.ToUpper();
        }

        /// <summary>
        /// 获取硬盘序列号
        /// </summary>
        /// <returns></returns>
        private static string GetDiskNumber()
        {
            //ManagementClass mc = new ManagementClass("Win32_NetworkAdapterConfiguration");
            //ManagementObject disk = new ManagementObject(string.Format("win32_logicaldisk.deviceid=\"{0}:\"", DISK_DRIVE));
            //disk.Get();
            //string hex = disk.GetPropertyValue("VolumeSerialNumber").ToString();

            //return Convert.ToInt64(hex, 16).ToString().ToUpper();

            //由于.NET 和 JAVA对于部分服务器获取的硬盘ID不一致，所以暂时不使用

            return string.Empty;
        }

        /// <summary>
        /// 获取物理网卡Mac地址
        /// </summary>
        /// <returns></returns>
        public static string GetMac()
        {
            string key = "SYSTEM\\CurrentControlSet\\Control\\Network\\{4D36E972-E325-11CE-BFC1-08002BE10318}\\";
            string macAddress = string.Empty;
            try
            {
                NetworkInterface[] nics = NetworkInterface.GetAllNetworkInterfaces();
                foreach (NetworkInterface adapter in nics)
                {
                    if (adapter.NetworkInterfaceType != NetworkInterfaceType.Ethernet
                        || adapter.GetPhysicalAddress().ToString().Length == 0)
                    {
                        continue;
                    }

                    string fRegistryKey = key + adapter.Id + "\\Connection";
                    RegistryKey rk = Registry.LocalMachine.OpenSubKey(fRegistryKey, false);
                    if (rk == null)
                    {
                        continue;
                    }

                    string fPnpInstanceID = rk.GetValue("PnpInstanceID", "").ToString();
                    int fMediaSubType = Convert.ToInt32(rk.GetValue("MediaSubType", 0));
                    if (fPnpInstanceID.Length > 3 && fPnpInstanceID.Substring(0, 3) == "PCI")
                    {
                        macAddress = adapter.GetPhysicalAddress().ToString();
                        for (int i = 1; i < 6; i++)
                        {
                            macAddress = macAddress.Insert(3 * i - 1, "-");
                        }
                        break;
                    }
                }
            }
            catch
            {
                return "unknow";
            }

            return macAddress.ToUpper();
        }        

        /// <summary>
        /// MD5加密
        /// </summary>
        /// <param name="s"></param>
        /// <returns></returns>
        private static string MD5(String s)
        {
            char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
            try
            {
                byte[] btInput = System.Text.Encoding.UTF8.GetBytes(s);
                // 获得MD5摘要算法的 MessageDigest 对象
                MD5 mdInst = System.Security.Cryptography.MD5.Create();
                // 使用指定的字节更新摘要
                mdInst.ComputeHash(btInput);
                // 获得密文
                byte[] md = mdInst.Hash;
                // 把密文转换成十六进制的字符串形式
                int j = md.Length;
                char[] str = new char[j * 2];
                int k = 0;
                for (int i = 0; i < j; i++)
                {
                    byte byte0 = md[i];
                    str[k++] = hexDigits[(int)(((byte)byte0) >> 4) & 0xf];
                    str[k++] = hexDigits[byte0 & 0xf];
                }
                return new string(str);
            }
            catch
            {
                return string.Empty;
            }
        }
    }
}