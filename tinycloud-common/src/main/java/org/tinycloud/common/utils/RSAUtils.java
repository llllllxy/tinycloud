package org.tinycloud.common.utils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA算法工具类
 *
 * @author liuxingyu01
 * @since 2022-03-11-16:47
 **/
public class RSAUtils {

    /**
     * 编码格式
     */
    public static final String CHARSET = "UTF-8";

    /**
     * RSA加解密算法
     */
    public static final String ALGORITHM_RSA = "RSA";

    /**
     * RSA 签名算法
     */
    public static final String ALGORITHM_RSA_SIGN = "SHA256WithRSA";

    /**
     * 密钥长度，建议2048，为了安全也可以4096
     */
    public static final int ALGORITHM_RSA_PRIVATE_KEY_LENGTH = 2048;


    /**
     * 初始化RSA算法密钥对（2048长度）
     *
     * @return 经过Base64编码后的公私钥Map, 键名分别为publicKey和privateKey
     */
    public static Map<String, String> generateRSAKey() {
        // 为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm -->[" + ALGORITHM_RSA + "]");
        }
        kpg.initialize(ALGORITHM_RSA_PRIVATE_KEY_LENGTH);
        // 生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        // 得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        // 得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);
        return keyPairMap;
    }


    /**
     * RSA算法分段加解密数据
     *
     * @param cipher 初始化了加解密工作模式后的javax.crypto.Cipher对象
     * @param opmode 加解密模式,值为javax.crypto.Cipher.ENCRYPT_MODE/DECRYPT_MODE
     * @return 加密或解密后得到的数据的字节数组
     */
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = ALGORITHM_RSA_PRIVATE_KEY_LENGTH / 8;
        } else {
            maxBlock = ALGORITHM_RSA_PRIVATE_KEY_LENGTH / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        return resultDatas;
    }


    /**
     * RSA算法公钥加密数据
     *
     * @param data 待加密的明文字符串
     * @param key  RSA公钥字符串
     * @return RSA公钥加密后的经过Base64编码的密文字符串
     */
    public static String buildRSAEncryptByPublicKey(String data, String key) {
        try {
            // 通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.getEncoder().encodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET)));
        } catch (Exception e) {
            throw new RuntimeException("公钥加密字符串[" + data + "]时遇到异常", e);
        }
    }


    /**
     * RSA算法公钥解密数据
     *
     * @param data 待解密的经过Base64编码的密文字符串
     * @param key  RSA公钥字符串
     * @return RSA公钥解密后的明文字符串
     */
    public static String buildRSADecryptByPublicKey(String data, String key) {
        try {
            // 通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.getDecoder().decode(data)), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("公钥解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA算法私钥加密数据
     *
     * @param data 待加密的明文字符串
     * @param key  RSA私钥字符串
     * @return RSA私钥加密后的经过Base64编码的密文字符串
     */
    public static String buildRSAEncryptByPrivateKey(String data, String key) {
        try {
            // 通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.getEncoder().encodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET)));
        } catch (Exception e) {
            throw new RuntimeException("私钥加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA算法私钥解密数据
     *
     * @param data 待解密的经过Base64编码的密文字符串
     * @param key  RSA私钥字符串
     * @return RSA私钥解密后的明文字符串
     */
    public static String buildRSADecryptByPrivateKey(String data, String key) {
        try {
            //通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.getDecoder().decode(data)), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("私钥解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA算法使用私钥对数据生成数字签名
     *
     * @param data 待签名的明文字符串
     * @param key  RSA私钥字符串
     * @return RSA私钥签名后的经过Base64编码的字符串
     */
    public static String buildRSASignByPrivateKey(String data, String key) {
        try {
            // 通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance(ALGORITHM_RSA_SIGN);
            signature.initSign(privateKey);
            signature.update(data.getBytes(CHARSET));
            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (Exception e) {
            throw new RuntimeException("私钥签名字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA算法使用公钥校验数字签名
     *
     * @param data 参与签名的明文字符串
     * @param key  RSA公钥字符串
     * @param sign RSA签名得到的经过Base64编码的字符串
     * @return true--验签通过,false--验签未通过
     */
    public static boolean buildRSAVerifyByPublicKey(String data, String key, String sign) {
        try {
            // 通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
            Signature signature = Signature.getInstance(ALGORITHM_RSA_SIGN);
            signature.initVerify(publicKey);
            signature.update(data.getBytes(CHARSET));
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            throw new RuntimeException("公钥验签字符串[" + data + "]时遇到异常", e);
        }
    }


    public static void main(String[] args) {
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj4+4NFMZE9iCyS3idB6OFiRKz7aGzvgn/DI/SKHOPnkH19GhpTZU5oGS7j/ZWd94MakiSA/H8ELm32sgOc+xNl8KBhRqj1uvB68YGEbQEKRYXiKK/7tcZQhOIC8R+LBeDTl9rBqa1CGqP7sf17F2Zpa9mCcL2HvroKIMrmuQKhkxtI5V4r7Xajs8ofFbREyWA6KGkoanjaGmbZg/CSxg0s8UEgjFTJl3/xxyEMaph6nUfYHgNnebX+Gc1KJcY4ibJXZ52Ov3Ru3LJMjLC2lvDxPyqNwSAOUbbwOMfTrzPoCdMYu8maMpwToZGc9if8Rsr6aa4s4YCBxazW45Gw/BGwIDAQAB";
        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCPj7g0UxkT2ILJLeJ0Ho4WJErPtobO+Cf8Mj9Ioc4+eQfX0aGlNlTmgZLuP9lZ33gxqSJID8fwQubfayA5z7E2XwoGFGqPW68HrxgYRtAQpFheIor/u1xlCE4gLxH4sF4NOX2sGprUIao/ux/XsXZmlr2YJwvYe+ugogyua5AqGTG0jlXivtdqOzyh8VtETJYDooaShqeNoaZtmD8JLGDSzxQSCMVMmXf/HHIQxqmHqdR9geA2d5tf4ZzUolxjiJsldnnY6/dG7cskyMsLaW8PE/Ko3BIA5RtvA4x9OvM+gJ0xi7yZoynBOhkZz2J/xGyvpprizhgIHFrNbjkbD8EbAgMBAAECggEAL3OTsUfePB8Oa6zM8xC+ajcouytbt8Fyhfudy0YgQ6oBm6ZgxZkFywL9K97S2s/wjImZGTSOI64ZXbOs/S7XJl+Xkpbg+e2WFvYbArs1PqrzwnmsdAbb7VEncZ/dUtQI4lokXeSVVJSkD1isl8DCn/onRMlzMuUS5w9pHaXUCvW2KJl9rEO26m27cgQLrXsb9cbpGvfDnOk7f5ltyTO9RBxxZP48YieJMhebZCMFX+D3ue43J3/DrBfyULYIA8owzftwN/P955pt2YI3Xx/x3Jwyt8Tv1QFqy/CpZ3DC+mlVzCPDPoATO889PRGREdzheZOy81Ldavv7v1Z5XYvTgQKBgQDB6wrK4mjBrAVBVPu3gnB064AH2kUo4yQO2yigiSgWRU2ikgm7szvXuNGni+8Y2YsAF7OOpIvrhBPr6DZFlZt8njwzJww0y2bh56zMghqTx+Aslfvlup0nIG5v1o1MbsniGEuTZ/hgMIhEaSU9NJCF5wwgzFM3BFIHZim7E4X/oQKBgQC9hZapQhMoaeTjdHwVhbKDXJj4nH8/mPdoDdn83t+CH1ejKmQ64A/A64tq6jYdR31Oj5I5NG4SeBaaV/AKHTLQewdWp/3lqFAhL36LoGiJfdLyD3nnwT5iVuigCqfzTopE8v1HWarEUcU5NbONFeeKt24PE4EKBlmfEd5aexp3OwKBgG43alXwI3xYilAcvkkKEWLS610LOR3JJBhxhgDgk/E8iKiq+m89qvisajUhes8T7kYqMnsTmRdOZYhC3n3kT5Cf2zVzryNerGYfqH5AQFzYUNjmklp7G5E+hKZ6OqtDmOkxx9rLUEdTrk+7t1e3RaFlteX6XB28Ded4sKVMrm+hAoGABBbLx18blrWiMJ4kxzoZPehTjJmjzTV5sQ8Lcv9EB0Sx2V1iyDppXJo4adgfzsXqGDXUy9MrxM4+fpoDHdS/cewVnnMFcV0OTYj+y/hsCPtj/8EBulRbCjgh0MIqGX/KT+6GNHHk01OIU9GqzBJ66m1GvKCJGjRE3r+98mnRil0CgYBEQko6LAlLz4Eizr+rIPhED6zl51iqXV8cqDBkTvFBR2/YzVWvNAn1JtmPe0cludCyoqM6aGhslBZrSXph+K2BSTEspoB1BmD2ULyQvmYD5FE94149Q3RWEyPd3ltkREePryvdQjT0cR96FdaCe6tjBfJtuQlv4qxlC7zwZEsdOg==";

        String data = "!QAZ2wsx";
        // 公钥加密
        String result = buildRSAEncryptByPublicKey(data, publicKey);
        System.out.println("加密结果：" + result);
        // 私钥解密
        System.out.println("解密结果：" + buildRSADecryptByPrivateKey(result, privateKey));
    }
}
