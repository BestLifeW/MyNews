package com.rjxy.xmut.mynews.utilis;

import android.content.Context;

/**
 * Cache缓存
 */
public class CacheUtils {
    /**
     * 以url为key, 以json为value,保存在本地
     *
     * @param url
     * @param json
     */
    public static void setCache(String url, String json, Context ctx) {
        //也可以用文件缓存: 以MD5(url)为文件名, 以json为文件内容
        PrefUtils.setString(ctx, url, json);
    }

    /**
     * 获取缓存
     *
     * @param url
     * @param ctx
     * @return
     */
    public static String getCache(String url, Context ctx) {
        //文件缓存: 查找有没有一个文件叫做MD5(url)的, 有的话,说明有缓存
        return PrefUtils.getString(ctx, url, null);
    }
}
