package com.yirong.hotfixtest.utils;


import android.content.Context;

import java.io.File;
import java.lang.reflect.Array;
import java.util.HashSet;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 *
 */
public class FixDexUtils {

    private static HashSet<File> loadDex = new HashSet<>();

    static {
        loadDex.clear();
    }
    public static void loadFixDex(Context context) {
        if(context == null){
            return;
        }
        File fileDir = context.getDir(Constants.DEX_DIR, Context.MODE_PRIVATE);
        File[] fileLists = fileDir.listFiles();
        for (File f:fileLists){
            if(!"classes.dex".equals(f.getName()) && f.getName().endsWith(Constants.DEX_SUFFIX)){
                loadDex.add(f);
            }
        }

        //创建类加载器
        createClassLoader(context,fileDir);
    }

    private static void createClassLoader(Context context, File fileDir) {
        //临时解压目录，先解压后进行热修复
        String unpackDir = fileDir.getAbsolutePath()+ File.separator + "opt_dex";
        File file = new File(unpackDir);
        if(!file.exists()){
            file.mkdirs();
        }

        for(File dex:loadDex){
            DexClassLoader dexClassLoader = new DexClassLoader(dex.getAbsolutePath(),unpackDir,null,context.getClassLoader());
            hotfix(dexClassLoader,context);
        }
    }


    /**
     * @param dexClassLoader
     * @param context
     */
    private static void hotfix(DexClassLoader dexClassLoader, Context context) {
        //获取系统pathLoader
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();

        try {
            //获取系统的dex列表
            Object sysDexElement = ReflectUtils.getDexElements(ReflectUtils.getPathList(pathClassLoader));
            //获取自己需要添加的dex列表
            Object dexElement = ReflectUtils.getDexElements(ReflectUtils.getPathList(dexClassLoader));
            //合并两个列表
            Object dexElementAll = ArrayUtils.conbineArray(dexElement, sysDexElement);
            //通过反射获取pathList地址
            Object sysPathList = ReflectUtils.getPathList(pathClassLoader);
            //添加值到pathList列表中
            ReflectUtils.setField(sysPathList,sysPathList.getClass(),dexElementAll);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
