/************************************************************************************************************
 *  CONFIDENTIAL AND PROPRIETARY
 *
 *  The source code and other information contained herein is the confidential and the exclusive property of
 *  Raster Images Pvt. Ltd. and is subject to the terms and conditions in your end user license agreement.
 *  This source code, and any other information contained herein, shall not be copied, reproduced, published,
 *  displayed or distributed, in whole or in part, in any medium, by any means, for any purpose except as
 *  expressly permitted under such license agreement.
 *
 *  Copyright Raster Images Pvt. Ltd.
 *
 *  ALL RIGHTS RESERVED
 ************************************************************************************************************/
package in.raster.ihms.abdm.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Util class for RSA encryption.
 */
@Component
public class RsaEncryptionUtil {

    /**
     * Encrypt data.
     *
     * @param message - message
     * @param publicKeyString - public key string
     * @param cipherType - cipher type
     * @return rsa encrypted value
     */
    public static String encrypt(final String message, final String publicKeyString, final String cipherType)  {
        String encryptData = null;
        try {
            final byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString.getBytes());
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            final X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            final PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            final Cipher cipher = Cipher.getInstance(cipherType);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptData = Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptData;
    }
}
