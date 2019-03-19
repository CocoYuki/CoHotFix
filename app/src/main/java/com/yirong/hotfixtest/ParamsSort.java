package com.yirong.hotfixtest;

import android.content.Context;
import android.widget.Toast;


public class ParamsSort {
    public void math(Context context) {
        int a = 10;
        int b = 0;
        Toast.makeText(context,a+"除以"+b+"答案是"+a/b,Toast.LENGTH_SHORT).show();
    }
}
