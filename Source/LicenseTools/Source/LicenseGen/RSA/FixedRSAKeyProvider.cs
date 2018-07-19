namespace LicenseGen.RSA
{
    /// <summary>
    /// 固定的密钥提供类
    /// </summary>
    public class FixedRSAKeyProvider : IRSAKeyProvider
    {
        const string PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIRxxCeduzU6HwJfXOR/VjEgpVeu3hdUHUiyfIyoqTEw4syPD6hFlSmDirtAHQSBpayz9jcGnWz9qEOJG64azs1HUPR9hJvy9TcRMmWrDy1kZ6L9NdpUj+WH7itpXTJGij3eDg2cAlrWoEvdpdUC9C1mmC2hvc/PSTRkHah9vgI5AgEDAoGAFhL2BppJ3jRagGU6JhU5CDAbjp0lA+NaNshqF3FxiDLQd20tRrZDhutByeAE1hWbnMipCSvE53+cC0GEnQR3zKMdvYJJd5RyJGGkhAh/T+40+3z6+in3q9y9zDrEXAikK/mDQs44yo8h3TmWdEg+LGtvfJAmJrfwDpHZf5YMArECQQDWsOAksB2avRxtbMIUNqsuYshj9725jued1MzLrtT7Fglxb/zvorUjU4a1NHnAu/KKitrtwfJatxeQgTEglkZTAkEAne2jSxuw3YtAWeqLY9yioMb1q1w/nzL2vaaVPRQ1F5ssk4p/1wrmWIGVzuqhkMMwV3BnUxb1JPHFaIN52N+rwwJBAI8glW3KvmcovZ5IgWLPHMmXMEKlKSZfRRPjMzJ0jfy5W6D1U0pseMI3ryN4USsn9wcHPJ6BTDx6D7WrdhW5hDcCQGlJF4dnyz5c1ZFHB5fobGsvTnI9f793TykZuNNizg+8yGJcVToHRDsBDonxwQssyuT1mjdko232g5sCUTs/x9cCQQCed9RvNGmbOaXmvEMxQ8Ix5mqVXhglY3PlMarZkXEEYEhfey1/TQdWPp9f6rrA5tlAtgN9Qun1FRarpsKVOw2X";

        const string PUBLIC_KEY = "MIGdMA0GCSqGSIb3DQEBAQUAA4GLADCBhwKBgQCEccQnnbs1Oh8CX1zkf1YxIKVXrt4XVB1IsnyMqKkxMOLMjw+oRZUpg4q7QB0EgaWss/Y3Bp1s/ahDiRuuGs7NR1D0fYSb8vU3ETJlqw8tZGei/TXaVI/lh+4raV0yRoo93g4NnAJa1qBL3aXVAvQtZpgtob3Pz0k0ZB2ofb4COQIBAw==";

        public string GetPrivateKey()
        {
            return PRIVATE_KEY;
        }

        public string GetPublicKey()
        {
            return PUBLIC_KEY;
        }
    }
}
