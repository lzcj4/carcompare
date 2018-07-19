using System.Configuration;

namespace LrcTools
{
    class AppSettings
    {
        public static string LicenseImporter
        {
            get
            {
                return string.Format("LicenseImporter/{0}", ConfigurationManager.AppSettings.Get("LicenseImporter"));
            }
        }

        public static string MachineCodeGen
        {
            get
            {
                return string.Format("MachineCodeGen/{0}", ConfigurationManager.AppSettings.Get("MachineCodeGen"));
            }
        }
    }
}
