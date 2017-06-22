package com.hannahxian.gank.utils

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.hannahxian.gank.ui.fragment.ProgressFragment

/**
 *  Created by Broderick
 *  User: Broderick
 *  Date: 2017/6/22
 *  Time: 12:45
 *  Version: 1.0
 *  Description:
 *  Email:wangchengda1990@gmail.com
 **/
fun AppCompatActivity.showProgress(){
    val dialog  = ProgressFragment.newInstance();
    dialog.show(this.supportFragmentManager, ProgressFragment::class.java.simpleName);
}

fun AppCompatActivity.dismissProgress(){
    (supportFragmentManager.findFragmentByTag(ProgressFragment::class.java.simpleName)
            as ProgressFragment?)?.dismiss();
}

fun Activity.toast(msg:String){
    Toast.makeText(applicationContext,msg, Toast.LENGTH_LONG).show();
}