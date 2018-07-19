using System.Configuration;

namespace LicenseImporter
{
    class AppSettings
    {
        public static string ServiceName
        {
            get
            {
                return ConfigurationManager.AppSettings.Get("ServiceName");
            }
        }

        public static bool Automation
        {
            get
            {
                return ConfigurationManager.AppSettings.Get("Automation") == "True";
            }
        }
    }
}
