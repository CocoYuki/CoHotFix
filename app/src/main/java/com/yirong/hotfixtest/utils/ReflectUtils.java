package com.yirong.hotfixtest.utils;

import java.lang.reflect.Field;

public class ReflectUtils {

    /**
     * @param object 该属性所属对象
     * @param clazz 该属性所属类
     * @param field 该属性名称
     * @return 该属性对象
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getField(Object object,Class<?> clazz,String field) throws NoSuchFieldException, IllegalAccessException {
        Field declaredFields = clazz.getDeclaredField(field);
        declaredFields.setAccessible(true);
        return declaredFields.get(object);
    }

    /**
     * 给dexElement赋值，并设为私有可访问
     *
     * @param object 该属性所属类的对象
     * @param clazz 该属性所属类
     * @param value 该属性值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setField(Object object,Class<?> clazz,Object value) throws NoSuchFieldException, IllegalAccessException {
        Field declaredFields = clazz.getDeclaredField("dexElements");
        declaredFields.setAccessible(true);
        declaredFields.set(object,value);
    }

    /**
     * 通过反射获取 baseDexLoader 中的 PathList对象
     *
     * @param baseDexLoader
     * @return PathList对象
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getPathList(Object baseDexLoader) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return getField(baseDexLoader,Class.forName("dalvik.system.BaseDexClassLoader"),"pathList");
    }


    /**
     * 反射获取pathList对象，再获取dexElement
     *
     * @param paramsObject
     * @return dexElement对象
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getDexElements(Object paramsObject) throws NoSuchFieldException, IllegalAccessException {
        return getField(paramsObject,paramsObject.getClass(),"dexElements");
    }
}
