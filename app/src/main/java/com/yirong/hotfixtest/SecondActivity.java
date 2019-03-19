package com.yirong.hotfixtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;

import android.support.annotation.Nullable;

import android.util.Log;
import android.view.View;

import com.yirong.hotfixtest.utils.Constants;
import com.yirong.hotfixtest.utils.FileUtils;
import com.yirong.hotfixtest.utils.FixDexUtils;

import java.io.File;
import java.io.IOException;

public class SecondActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void show(View view){
        ParamsSort sort = new ParamsSort();
        sort.math(this);
    }

    public void fix(View view){
        fixBug();
    }
    //classes2.dex -----> /storage/emulated/0/classes2.dex
    private void fixBug() {
        File sourceFile = new File(Environment.getExternalStorageDirectory(), Constants.DEX_NAME);

        Log.i("SD",Environment.getExternalStorageDirectory().toString());
        File targetFile = new File(getDir(Constants.DEX_DIR, Context.MODE_PRIVATE).getAbsolutePath()+File.separator+Constants.DEX_NAME);

        //如果修复过,清理掉
        if(targetFile != null){
            targetFile.delete();
            Log.i("fixBug","已经清理掉原修复classes文件");
        }
        try {
            FileUtils.copyFile(sourceFile,targetFile);
            Log.i("fixBug","已经修复classes文件");
            FixDexUtils.loadFixDex(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
