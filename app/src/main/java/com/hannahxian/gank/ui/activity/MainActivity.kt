package com.hannahxian.gank.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.hannahxian.gank.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastIndex = -1;
    var lastFragement : Fragment? = null ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.action_recommend ->{
                    Log.d("gank","select recommen")
                }
                R.id.action_girl -> {
                    Log.d("gank","select girl")
                }
                R.id.action_history ->{
                    Log.d("gank","select history")
                }
            }
            true;
        }
    }

    private fun changeTab(position:Int){
        if(position == lastIndex)
            return;
        lastIndex = position;

        val fragementManager = supportFragmentManager;
        val fragementTransaction = fragementManager.beginTransaction();

        if(lastFragement != null){
            fragementTransaction.hide(lastFragement);
        }

        when(position){
            0 -> {}
            1 -> {}
            2 -> {}
        }

        fragementTransaction.commit();
    }
}
