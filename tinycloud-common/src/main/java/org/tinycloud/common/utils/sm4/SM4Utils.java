package org.tinycloud.common.utils.sm4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 国密4加密算法工具类（对称加密）
 *
 * @author liuxingyu01
 * @since 2021-09-08-19:45
 **/
public class SM4Utils {


    /**
     * 十六进制串转化为byte数组
     *
     * @return the array of byte
     */
    public static byte[] hexToByte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Argument hex ( String ) must be a multiple of 2! ");
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param b byte[] 需要转换的字节数组
     * @return String 十六进制字符串
     */
    public static String byteToHex(byte[] b) {
        if (b == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xff);
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toLowerCase();
    }

    /**
     * 加密（ECB模式）
     *
     * @param plainText 待加密的内容
     * @param secretKey 加密密钥16字节
     * @param hexString 返回结果的数据类型， true-hex  false-base64
     * @return 加密后的结果
     */
    public static String encrypt_ECB(String plainText, String secretKey, boolean hexString) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes(StandardCharsets.UTF_8));

            if (hexString) {
                return byteToHex(encrypted);
            } else {
                return Base64.getEncoder().encodeToString(encrypted);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密（ECB模式）- 返回base64
     *
     * @param plainText 待加密的内容
     * @param secretKey 加密密钥16字节
     * @return 加密后的结果，base64格式
     */
    public static String encrypt_ECB(String plainText, String secretKey) {
        return encrypt_ECB(plainText, secretKey, false);
    }

    /**
     * 解密（ECB模式）
     *
     * @param cipherText 待解密的内容
     * @param secretKey  加密密钥16字节
     * @param hexString  返回结果的数据类型， true-hex  false-base64
     * @return 解密后的结果
     */
    public static String decrypt_ECB(String cipherText, String secretKey, boolean hexString) {
        try {
            byte[] encrypted;
            if (hexString) {
                encrypted = hexToByte(cipherText);
            } else {
                if (cipherText != null && cipherText.trim().length() > 0) {
                    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                    Matcher m = p.matcher(cipherText);
                    cipherText = m.replaceAll("");
                }
                encrypted = Base64.getDecoder().decode(cipherText);
            }

            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, encrypted);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 解密（ECB模式）
     *
     * @param cipherText 待解密的内容
     * @param secretKey  加密密钥16字节
     * @return 解密后的结果
     */
    public static String decrypt_ECB(String cipherText, String secretKey) {
        return decrypt_ECB(cipherText, secretKey, false);
    }


    /**
     * 加密（CBC模式）
     *
     * @param plainText 待加密的内容
     * @param secretKey 加密密钥
     * @param iv        偏移量
     * @param hexString 返回结果的数据类型， true-hex  false-base64
     * @return 加密后的结果
     */
    public static String encrypt_CBC(String plainText, String secretKey, String iv, boolean hexString) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            byte[] ivBytes;

            keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
            ivBytes = iv.getBytes(StandardCharsets.UTF_8);

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes(StandardCharsets.UTF_8));

            if (hexString) {
                return byteToHex(encrypted);
            } else {
                return Base64.getEncoder().encodeToString(encrypted);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密（CBC模式）
     *
     * @param plainText 待加密的内容
     * @param secretKey 加密密钥
     * @param iv        偏移量
     * @return 加密后的结果，base64格式
     */
    public static String encrypt_CBC(String plainText, String secretKey, String iv) {
        return encrypt_CBC(plainText, secretKey, iv, false);
    }

    /**
     * 解密（CBC模式）
     *
     * @param cipherText 待解密的内容
     * @param secretKey  加密密钥
     * @param iv         偏移量
     * @param hexString  返回结果的数据类型， true-hex  false-base64
     * @return 解密后的结果
     */
    public static String decrypt_CBC(String cipherText, String secretKey, String iv, boolean hexString) {
        try {
            byte[] encrypted;
            if (hexString) {
                encrypted = hexToByte(cipherText);
            } else {
                if (cipherText != null && cipherText.trim().length() > 0) {
                    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                    Matcher m = p.matcher(cipherText);
                    cipherText = m.replaceAll("");
                }
                encrypted = Base64.getDecoder().decode(cipherText);
            }

            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
            byte[] ivBytes = iv.getBytes(StandardCharsets.UTF_8);

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, encrypted);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 解密（CBC模式）
     *
     * @param cipherText 待解密的内容
     * @param secretKey  加密密钥
     * @param iv         偏移量
     * @return 加密后的结果
     */
    public static String decrypt_CBC(String cipherText, String secretKey, String iv) {
        return decrypt_CBC(cipherText, secretKey, iv, false);
    }


    /**
     * 测试
     * secretKey和iv必须设置为16进制串（128位，16字节）
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {
        String plainText = "Test SM4 Function";

        // 设置加密密钥，必须16字节，128位
        String secretKey = "Ber3z8TK96xrg@e2";

        // ECB模式
        System.out.println("ECB模式加密");
        String cipherText = SM4Utils.encrypt_ECB(plainText, secretKey);
        System.out.println("加密 -> 密文: " + cipherText);

        String plainText2 = SM4Utils.decrypt_ECB(cipherText, secretKey);
        System.out.println("解密 -> 明文: " + plainText2);

        // CBC模式（需要设置一个32位的偏移量）
        System.out.println("CBC模式加密");

        // 偏移量（CBC模式使用），必须16字节，128位
        String iv = "E%BJuDUTvXfwSuGQ";

        String cipherText2 = SM4Utils.encrypt_CBC(plainText, secretKey, iv);
        System.out.println("加密 -> 密文: " + cipherText2);

        String plainText3 = SM4Utils.decrypt_CBC(cipherText2, secretKey, iv);
        System.out.println("解密 -> 明文: " + plainText3);

    }
}
