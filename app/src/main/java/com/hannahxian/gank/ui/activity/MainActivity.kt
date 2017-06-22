package com.hannahxian.gank.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.hannahxian.gank.R
import com.hannahxian.gank.ui.fragment.HistoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastIndex = -1;
    var lastFragement : Fragment? = null ;

    var histroryFragment : HistoryFragment? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.action_recommend ->{
                    Log.d("gank","select recommen")
                    changeTab(0)
                }
                R.id.action_girl -> {
                    Log.d("gank","select girl")
                    changeTab(1)
                }
                R.id.action_history ->{
                    Log.d("gank","select history")
                    changeTab(2)
                }
            }
            true;
        }
        changeTab(0)
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
            2 -> {
                histroryFragment = fragementManager.findFragmentByTag(HistoryFragment::class.java.simpleName) as HistoryFragment?;
                if(histroryFragment != null){
                    fragementTransaction.show(histroryFragment);
                }else{
                    histroryFragment = HistoryFragment.newInstance();
                    fragementTransaction.add(R.id.container,histroryFragment,HistoryFragment::class.java.simpleName);
                }
                lastFragement = histroryFragment;
            }
        }

        fragementTransaction.commit();
    }
}
