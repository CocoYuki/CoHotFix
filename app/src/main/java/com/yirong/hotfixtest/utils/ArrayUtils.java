package com.yirong.hotfixtest.utils;

import java.lang.reflect.Array;

public class ArrayUtils {


    /**
     * @param arrLhs 前数组
     * @param arrRhs 后数组
     * @return 合并数组
     */
    public static Object conbineArray(Object arrLhs,Object arrRhs){
        Class<?> mClass = arrLhs.getClass().getComponentType();
        int i = Array.getLength(arrLhs);
        int j = i + Array.getLength(arrRhs);
        Object instance = Array.newInstance(mClass, j);

        for (int k = 0;k< j;++k){
            if(k < i){
                Array.set(instance,k,Array.get(arrLhs,k));
            }else{
                Array.set(instance,k,Array.get(arrRhs ,k - i));
            }

        }
        return instance;
    }
}
