package com.fxn.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by akshay on 01/03/18.
 */

public class Store {

    private static Store store;
    private SharedPreferences sp;

    public static void init(Context context) {
        store = new Store();
        if (store.sp == null) {
            store.sp = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    private static void checkfornull() {
        if (store == null)
            throw new NullPointerException("Call init() method in application class");
    }

    //putString
    public static void put(String key, String value) {
        checkfornull();
        try {
            store.sp.edit().putString(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //putStringSet
    public static void put(String key, Set<String> value) {
        checkfornull();
        try {
            store.sp.edit().putStringSet(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //putInt
    public static void put(String key, int value) {
        checkfornull();
        try {
            store.sp.edit().putInt(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //putLong
    public static void put(String key, long value) {
        checkfornull();
        try {
            store.sp.edit().putLong(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //putFloat
    public static void put(String key, float value) {
        checkfornull();
        try {
            store.sp.edit().putFloat(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //putBoolean
    public static void put(String key, boolean value) {
        checkfornull();
        try {
            store.sp.edit().putBoolean(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //putObject or arrayList
    public static void put(String key, Object value) {
        checkfornull();
        try {
            Gson gson = new GsonBuilder().create();
            store.sp.edit().putString(key, gson.toJson(value).toString()).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //getString
    public static String getString(String key, String defaultvalue) {
        checkfornull();
        try {
            return store.sp.getString(key, defaultvalue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultvalue;
        }
    }


    //getStringSet
    public static Set<String> getStringSet(String key, Set<String> defaultvalue) {
        checkfornull();
        try {
            return getStringSet(key, defaultvalue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultvalue;
        }
    }

    //getInt
    public static int getInt(String key, int defaultvalue) {
        checkfornull();
        try {
            return store.sp.getInt(key, defaultvalue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultvalue;
        }
    }

    //getLong
    public static long getLong(String key, long defaultvalue) {
        checkfornull();
        try {
            return store.sp.getLong(key, defaultvalue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultvalue;
        }
    }

    //getFloat
    public static float getFloat(String key, float defaultvalue) {
        checkfornull();
        try {
            return store.sp.getFloat(key, defaultvalue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultvalue;
        }
    }

    //getBoolean
    public static boolean getBoolean(String key, boolean defaultvalue) {
        checkfornull();
        try {
            return store.sp.getBoolean(key, defaultvalue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultvalue;
        }
    }

    //getObject
    public static <T> Object getObject(String key, Class<?> tClass) {
        checkfornull();
        try {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(store.sp.getString(key, ""), tClass);
        } catch (Exception e) {
            Log.e("gson", e.getMessage());
            return "";
        }
    }

    //getArrayList
    public static <T> ArrayList<T> getArrayList(String key, Class<?> tClass) {
        Log.e("_+_++__+_+", "" + tClass.getName());
        Gson gson = new Gson();
        String data = store.sp.getString(key, "");
        if (!data.trim().isEmpty()) {
            Type type = new GenericType(tClass);
            return (ArrayList<T>) gson.fromJson(data, type);
        }
        return new ArrayList<T>();
    }

    //clear single value
    public static void clear(String key) {
        checkfornull();
        try {
            store.sp.edit().remove(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //clear all preference
    public static void clearAll() {
        checkfornull();
        try {
            store.sp.edit().clear().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class GenericType implements ParameterizedType {

        private Type type;

        GenericType(Type type) {
            this.type = type;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{type};
        }

        @Override
        public Type getRawType() {
            return ArrayList.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }

        // implement equals method too! (as per javadoc)
    }
}
