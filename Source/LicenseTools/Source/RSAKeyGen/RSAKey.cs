namespace RSAKeyGen
{
    /// <summary>
    /// KEY 结构体
    /// </summary>
    public struct RSAKey
    {
        /// <summary>
        /// 公钥
        /// </summary>
        public string PublicKey
        {
            get;
            set;
        }

        /// <summary>
        /// 私钥
        /// </summary>
        public string PrivateKey
        {
            get;
            set;
        }
    }
}
