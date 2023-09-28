package com.nice.nicebaby.util;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.security.KeyPair;
import java.security.Provider;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class JwtUtil {

    static RSAPublicKey rsaPublicKey;
    static RSAPrivateKey rsaPrivateKey;

    public static int init(final String crt, final String privateKey) {
        if (Security.getProvider("BC") == null) {
            Security.addProvider((Provider) new BouncyCastleProvider());
        }
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC");
            Certificate crtObj = cf.generateCertificate(new ByteArrayInputStream(crt.getBytes()));
            JwtUtil.rsaPublicKey = (RSAPublicKey) crtObj.getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (privateKey != null) {
            try {
                PEMParser pemParser = new PEMParser(new StringReader(privateKey));
                Object object = pemParser.readObject();
                PEMDecryptorProvider decProv = new JcePEMDecryptorProviderBuilder().build("".toCharArray());
                JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");

                if (object instanceof PEMEncryptedKeyPair) {
                    System.out.println("Encrypted key - using provided password");
                    KeyPair kp = converter.getKeyPair(((PEMEncryptedKeyPair) object).decryptKeyPair(decProv));
                    JwtUtil.rsaPrivateKey = (RSAPrivateKey) kp.getPrivate();
                } else if (object instanceof PrivateKeyInfo) {
                    JwtUtil.rsaPrivateKey = (RSAPrivateKey) converter.getPrivateKey((PrivateKeyInfo) object);
                } else if (object instanceof PEMKeyPair) {
                    KeyPair kp = converter.getKeyPair((PEMKeyPair) object);
                    JwtUtil.rsaPrivateKey = (RSAPrivateKey) kp.getPrivate();
                } else {
                    System.err.println("Unsupported private key type.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

}
