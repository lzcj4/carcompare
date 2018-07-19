namespace LicenseGen.RSA
{
    /// <summary>
    /// RSA密钥提供接口
    /// </summary>
    public interface IRSAKeyProvider
    {
        /// <summary>
        /// 获取私钥
        /// </summary>
        /// <returns></returns>
        string GetPrivateKey();

        /// <summary>
        /// 获取公钥
        /// </summary>
        /// <returns></returns>
        string GetPublicKey();
    }
}
