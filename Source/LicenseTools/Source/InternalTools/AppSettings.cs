using System.Configuration;

namespace InternalTools
{
    class AppSettings
    {
        public static string LicenseGen
        {
            get
            {
                return string.Format("LicenseGen/{0}", "LicenseGen");
            }
        }

        public static string DatabaseInitializer
        {
            get
            {
                return string.Format("DatabaseInitializer/{0}", "DatabaseInitializer");
            }
        }
    }
}
