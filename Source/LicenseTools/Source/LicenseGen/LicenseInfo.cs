using LicenseGen.Common;
using Newtonsoft.Json;
using System;

namespace LicenseGen
{
    public class LicenseInfo
    {
        [JsonProperty("id")]
        public string ID
        {
            get;
            set;
        }

        [JsonProperty("machineCode")]
        public string MachineCode
        {
            get;
            set;
        }

        [JsonProperty("begin")]
        public DateTime Begin
        {
            get;
            set;
        }

        [JsonProperty("end")]
        public DateTime End
        {
            get;
            set;
        }

        public void Validate()
        {
            if (String.IsNullOrWhiteSpace(MachineCode))
            {
                throw new ApplicationException("机器码不能为空");
            }

            if(Begin >= End)
            {
                throw new ApplicationException("生效时间不能大于失效时间");
            }
        }

        public string ToJson()
        {
            this.Begin = this.Begin.Date;
            this.End = this.End.Date.AddDays(1).AddSeconds(-1);

            return Utils.ToJson(this);
        }
    }
}
