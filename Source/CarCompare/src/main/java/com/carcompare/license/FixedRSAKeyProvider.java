package com.carcompare.license;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 固定的RSA密钥提供类
 */
public class FixedRSAKeyProvider implements IRSAKeyProvider {
    static final Logger logger = LoggerFactory.getLogger(FixedRSAKeyProvider.class);

    /**
     * RSA私钥
     */
    static final String PUBLIC_KEY = "MIGdMA0GCSqGSIb3DQEBAQUAA4GLADCBhwKBgQCEccQnnbs1Oh8CX1zkf1YxIKVXrt4XVB1IsnyMqKkxMOLMjw+oRZUpg4q7QB0EgaWss/Y3Bp1s/ahDiRuuGs7NR1D0fYSb8vU3ETJlqw8tZGei/TXaVI/lh+4raV0yRoo93g4NnAJa1qBL3aXVAvQtZpgtob3Pz0k0ZB2ofb4COQIBAw==";

    /**
     * 获取公钥
     * @return
     */
    @Override
    public String getPublicKey() {
        return PUBLIC_KEY;
    }
}
