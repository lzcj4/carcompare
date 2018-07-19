using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DatabaseInitializer.Core
{
    public class Settings
    {
        public string DbHost
        {
            get;set;
        }

        public string DbUserId
        {
            get;set;
        }

        public string DbPassword
        {
            get;set;
        }

        public string DbSource
        {
            get;set;
        }

        public string DbTarget
        {
            get;set;
        }
    }
}
