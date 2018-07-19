using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Reflection;

namespace LicenseGen.Common
{
    public class Utils
    {
        /// <summary>
        /// 从一个对象信息生成Json串
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>        
        public static string ToJson(object obj)
        {
            IsoDateTimeConverter timeFormat = new IsoDateTimeConverter();
            timeFormat.DateTimeFormat = "yyyy-MM-dd HH:mm:ss";
            return JsonConvert.SerializeObject(obj, timeFormat);
        }

        /// <summary>
        /// 从一个Json串生成对象信息  
        /// </summary>
        /// <param name="jsonString"></param>
        /// <param name="obj"></param>
        /// <returns></returns>
        public static object ToObject(string jsonString, object obj)
        {
            return JsonConvert.DeserializeObject(jsonString, obj.GetType());
        }

        /// <summary>
        /// 获取枚举值和描述
        /// </summary>
        /// <param name="enumType"></param>
        /// <returns></returns>
        public static Dictionary<Int32, String> EnumToDictionary(Type enumType)
        {
            if (!enumType.IsEnum)
            {
                throw new ArgumentException("传入的参数必须是枚举类型！", "enumType");
            }
            Dictionary<Int32, String> enumDic = new Dictionary<int, string>();
            Array enumValues = Enum.GetValues(enumType);
            foreach (Enum enumValue in enumValues)
            {
                Int32 key = Convert.ToInt32(enumValue);
                String value = GetDescription(enumValue);
                enumDic.Add(key, value);
            }
            return enumDic;
        }

        public static string GetDescription(Enum value, Boolean nameInstead = true)
        {
            Type type = value.GetType();
            string name = Enum.GetName(type, value);
            if (name == null)
            {
                return null;
            }

            FieldInfo field = type.GetField(name);
            DescriptionAttribute attribute = Attribute.GetCustomAttribute(field, typeof(DescriptionAttribute)) as DescriptionAttribute;

            if (attribute == null && nameInstead == true)
            {
                return name;
            }
            return attribute == null ? null : attribute.Description;
        }
    }
}
