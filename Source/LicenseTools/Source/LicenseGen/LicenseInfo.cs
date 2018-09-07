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

        /// <summary>
        /// 机器码
        /// </summary>
        [JsonProperty("machineCode")]
        public string MachineCode
        {
            get;
            set;
        }

        /// <summary>
        /// 授权开始时间
        /// </summary>
        [JsonProperty("begin")]
        public DateTime Begin
        {
            get;
            set;
        }

        /// <summary>
        /// 授权结束时间
        /// </summary>
        [JsonProperty("end")]
        public DateTime End
        {
            get;
            set;
        }

        /// <summary>
        /// 授权用户数
        /// </summary>
        [JsonProperty("userCount")]
        public int UserCount
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

            if (UserCount <= 0)
            {
                throw new ApplicationException("授权用户数必须大于0");
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
