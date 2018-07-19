using System.ComponentModel;

namespace LicenseGen
{
    /// <summary>
    /// 许可证授权时间枚举
    /// 枚举值代表月数（方便后续计算）
    /// </summary>
    public enum LicenseTimes
    {
        /// <summary>
        /// 自定义
        /// </summary>
        [Description("自定义")]
        Custom = 0,

        /// <summary>
        /// 一个月
        /// </summary>
        [Description("一个月")]
        OneMonth = 1,

        /// <summary>
        /// 三个月
        /// </summary>
        [Description("三个月")]
        ThreeMonths = 3,

        /// <summary>
        /// 一年
        /// </summary>
        [Description("一年")]
        OneYear = 12,

        /// <summary>
        /// 永久
        /// </summary>
        [Description("永久")]
        Forever = 2400
    }
}
