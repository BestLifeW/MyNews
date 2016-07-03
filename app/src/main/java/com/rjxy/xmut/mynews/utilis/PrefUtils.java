package com.rjxy.xmut.mynews.utilis;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 * 对SharedPreferences 的分装
 */
public class  PrefUtils {

    public static boolean getBoolean(Context context,String key,boolean defValue ){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }
    public static void setBoolean(Context context,String key,boolean Value ){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,Value).apply();
    }
    public static String getString(Context context,String key,String defValue ){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }
    public static void setString(Context context,String key,String Value ){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putString(key,Value).apply();
    }

    public static int getInt(Context context,String key,int defValue ){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt(key,defValue);
    }
    public static void setInt(Context context,String key,int Value ){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putInt(key,Value).apply();
    }
}
