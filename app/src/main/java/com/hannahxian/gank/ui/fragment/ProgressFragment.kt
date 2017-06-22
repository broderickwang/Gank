package com.hannahxian.gank.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

/**
 *  Created by Broderick
 *  User: Broderick
 *  Date: 2017/6/22
 *  Time: 12:41
 *  Version: 1.0
 *  Description:
 *  Email:wangchengda1990@gmail.com
 **/
class ProgressFragment  : DialogFragment() {

    companion object{
        fun  newInstance() : ProgressFragment{
            return ProgressFragment();
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        return ProgressBar(activity);
    }
}