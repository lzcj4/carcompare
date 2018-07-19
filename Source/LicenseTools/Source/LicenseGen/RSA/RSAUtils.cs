using Org.BouncyCastle.Asn1;
using Org.BouncyCastle.Crypto;
using System;

using Org.BouncyCastle.Crypto.Encodings;
using Org.BouncyCastle.Crypto.Engines;
using Org.BouncyCastle.Security;
using System.Collections.Generic;

namespace LicenseGen.RSA
{
    public class RSAUtils
    {
        const int MAX_ENCRYPT_BLOCK = 117;

        const int MAX_DECRYPT_BLOCK = 128;

        /// <summary>
        /// 加密
        /// </summary>
        /// <param name="plainText"></param>
        /// <param name="privateKey"></param>
        /// <returns></returns>
        public static string Encrypt(string plainText, string privateKey)
        {
            //非对称加密算法，加解密用  
            IAsymmetricBlockCipher engine = new Pkcs1Encoding(new RsaEngine());

            //加密  
            try
            {
                engine.Init(true, GetPrivateKeyParameter(privateKey));
                byte[] byteData = System.Text.Encoding.UTF8.GetBytes(plainText);

                int inputLen = byteData.Length;
                int offSet = 0;
                byte[] cache;
                int i = 0;
                List<byte> result = new List<byte>();

                // 对数据分段加密  
                while (inputLen - offSet > 0)
                {
                    byte[] data;
                    if (inputLen - offSet > MAX_ENCRYPT_BLOCK)
                    {
                        data = new byte[MAX_ENCRYPT_BLOCK];                       
                    }
                    else
                    {
                        data = new byte[inputLen - offSet];
                    }

                    Array.Copy(byteData, offSet, data, 0, data.Length);
                    cache = engine.ProcessBlock(data, 0, data.Length);
                    result.AddRange(cache);

                    i++;
                    offSet = i * MAX_ENCRYPT_BLOCK;
                }

                return Convert.ToBase64String(result.ToArray());
            }
            catch (Exception ex)
            {
                return ex.Message;

            }
        }

        /// <summary>
        /// 解密
        /// </summary>
        /// <param name="encryptedText"></param>
        /// <param name="publicKey"></param>
        /// <returns></returns>
        public static string Decrypt(string encryptedText, string publicKey)
        {
            encryptedText = encryptedText.Replace("\r", "").Replace("\n", "").Replace(" ", "");
            //非对称加密算法，加解密用  
            IAsymmetricBlockCipher engine = new Pkcs1Encoding(new RsaEngine());

            //解密  
            try
            {
                engine.Init(false, GetPublicKeyParameter(publicKey));
                byte[] byteData = Convert.FromBase64String(encryptedText);

                int inputLen = byteData.Length;
                int offSet = 0;
                byte[] cache;
                int i = 0;
                List<byte> result = new List<byte>();

                // 对数据分段解密  
                while (inputLen - offSet > 0)
                {
                    byte[] data;
                    if (inputLen - offSet > MAX_DECRYPT_BLOCK)
                    {
                        data = new byte[MAX_DECRYPT_BLOCK];
                    }
                    else
                    {
                        data = new byte[inputLen - offSet];
                    }

                    Array.Copy(byteData, offSet, data, 0, data.Length);
                    cache = engine.ProcessBlock(data, 0, data.Length);
                    result.AddRange(cache);

                    i++;
                    offSet = i * MAX_DECRYPT_BLOCK;
                }

                return System.Text.Encoding.UTF8.GetString(result.ToArray());

            }
            catch (Exception ex)
            {
                return ex.Message;

            }
        }

        private static AsymmetricKeyParameter GetPublicKeyParameter(string s)
        {
            s = s.Replace("\r", "").Replace("\n", "").Replace(" ", "");
            byte[] publicInfoByte = Convert.FromBase64String(s);
            Asn1Object pubKeyObj = Asn1Object.FromByteArray(publicInfoByte);//这里也可以从流中读取，从本地导入   
            AsymmetricKeyParameter pubKey = PublicKeyFactory.CreateKey(publicInfoByte);
            return pubKey;
        }

        private static AsymmetricKeyParameter GetPrivateKeyParameter(string s)
        {
            s = s.Replace("\r", "").Replace("\n", "").Replace(" ", "");
            byte[] privateInfoByte = Convert.FromBase64String(s);
            // Asn1Object priKeyObj = Asn1Object.FromByteArray(privateInfoByte);//这里也可以从流中读取，从本地导入   
            // PrivateKeyInfo privateKeyInfo = PrivateKeyInfoFactory.CreatePrivateKeyInfo(privateKey);
            AsymmetricKeyParameter priKey = PrivateKeyFactory.CreateKey(privateInfoByte);
            return priKey;
        }
    }
}
