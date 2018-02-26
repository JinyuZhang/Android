package com.able.rx.tools.convert;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public interface ConvertCallback<S, R> {
    /**
     * 获取源数据
     *
     * @return
     */
    S getSource();

    /**
     * 转换
     *
     * @param source
     * @return
     */
    R convertAction(S source);

    /**
     * 转换完成
     *
     * @param success 是否成功
     * @param r       转换后的数据
     * @param e       转换中发生的异常
     */
    void convertComplete(boolean success, R r, Throwable e);
}
