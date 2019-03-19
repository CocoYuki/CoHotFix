package com.yirong.hotfixtest.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 */
public class FileUtils {

    /**
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @throws IOException IO异常
     * */
    public static void copyFile(File sourceFile,File targetFile) throws IOException {
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(input);

        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(output);

        byte[] b =  new byte[1024*5];
        int len;
        while ((len = bufferedInputStream.read(b))!= -1){
            bufferedOutputStream.write(b,0,len);
        }
        bufferedOutputStream.flush();
        bufferedInputStream.close();
        bufferedOutputStream.close();
        output.close();
        input.close();
    }
}
