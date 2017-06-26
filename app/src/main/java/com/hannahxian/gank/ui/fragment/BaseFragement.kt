package com.hannahxian.gank.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannahxian.gank.R
import com.hannahxian.gank.net.Api
import com.hannahxian.gank.repository.Article
import com.hannahxian.gank.repository.Result
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_base.*

/**
 * Created by hannahxian on 2017/6/26.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */
abstract class BaseFragement : Fragment() {
    val pageSize = 10;
    var pageNumber = 1;
    var isRefresh = false;

    var activity:Activity? = null;
    var rootView : View? = null;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(rootView == null){
            rootView = inflater?.inflate(R.layout.fragment_base,container,false);
        }
        return rootView;
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView();

        swipeLayout.setOnRefreshListener {
            pageNumber = 1;
            isRefresh= true;
            loadData(pageSize,pageNumber)
        }
        loadData(pageSize,pageNumber)
    }

    protected fun loadData(pageSize:Int,pageNumber:Int){
        val api = Api.Factory.create();
        api.getData(getType(),pageSize,pageNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({
                    t: Result? -> parseResult(t)
                },{
                    _ ->
                    loadError()
                    loadFinish()
                },{

                })
    }

    private fun parseResult(result:Result?){
        if (result != null) {
            if(result?.error){
                loadError()
            }else{
                loadSuccess(result?.results)
            }
        }

        loadFinish()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.activity = context as Activity;
    }

    override fun onDetach() {
        super.onDetach()
        this.activity = null
    }

    abstract fun initRecycleView();

    abstract fun getType():String;

    abstract fun loadError();

    abstract fun loadSuccess(data:List<Article>);

    abstract fun loadFinish();
}