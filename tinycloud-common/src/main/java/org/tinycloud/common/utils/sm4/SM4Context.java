package org.tinycloud.common.utils.sm4;

/**
 * sm4上下文对象
 * @author liuxingyu01
 * @since 2021-09-08-19:41
 **/
public class SM4Context {

    public int mode;

    public long[] sk;

    public boolean isPadding;

    public SM4Context() {
        this.mode = 1;
        this.isPadding = true;
        this.sk = new long[32];
    }
}
