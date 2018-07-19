using System.Diagnostics;

namespace Common
{
    public class Logger
    {
        private static bool enable;

        public static void Enable()
        {
            enable = true;
        }

        public static void Error(string message)
        {
            Error("LicenseTools", message);
        }

        public static void Error(string source, string message)
        {
            if (!enable)
            {
                return;
            }

            EventLog.WriteEntry(source, message, EventLogEntryType.Error);
        }
    }
}
