package com.hannahxian.gank.ui.fragment

import android.os.Bundle
import android.support.v7.widget.SimpleItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import com.hannahxian.gank.R
import com.hannahxian.gank.common.Type
import com.hannahxian.gank.repository.Article
import com.hannahxian.gank.ui.adapter.GrilAdapter
import com.hannahxian.gank.utils.toast
import kotlinx.android.synthetic.main.activity_article_detail.*
import kotlinx.android.synthetic.main.fragment_article_list.*

/**
 * Created by hannahxian on 2017/6/26.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */
class WelfFragement : BaseFragement() {

    var adapter : GrilAdapter? = null;

    companion object{
        fun newInstance():WelfFragement{
            return WelfFragement()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.visibility = View.VISIBLE
    }

    override fun initRecycleView() {
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        val simpleAnimate : SimpleItemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        simpleAnimate.supportsChangeAnimations = false;

        adapter = GrilAdapter(activity?.applicationContext,R.layout.item_girl)
        adapter?.setOnItemClickListener { adapter, view, i -> activity?.toast("girl item clicked ${i}") }

        recyclerView.adapter = adapter;
        adapter?.setOnLoadMoreListener({
            //BaseQuick封装好的，数据显示完 加载更多
            pageNumber++;
            Log.d("Gank","girl on load pagenumber=${pageNumber},pagesize=${pageSize}");
            isRefresh = false;
            loadData(pageSize,pageNumber);
        },recyclerView)
    }

    override fun getType(): String {
        return Type.福利.name;
    }

    override fun loadError() {
        activity?.toast("girl load data error")
    }

    override fun loadSuccess(data: List<Article>) {
        if(isRefresh){
            adapter?.setNewData(data)
        }else{
            adapter?.addData(data)
        }
    }

    override fun loadFinish() {
        if(swipeLayout.isRefreshing){
            swipeLayout.isRefreshing = true;
        }
        adapter?.loadMoreComplete();
    }
}