package com.hannahxian.gank.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannahxian.gank.R
import com.hannahxian.gank.net.Api
import com.hannahxian.gank.repository.History
import com.hannahxian.gank.ui.activity.MainActivity
import com.hannahxian.gank.ui.adapter.HistoryAdapter
import com.hannahxian.gank.utils.dismissProgress
import com.hannahxian.gank.utils.showProgress
import com.hannahxian.gank.utils.toast
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_history.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 *  Created by Broderick
 *  User: Broderick
 *  Date: 2017/6/22
 *  Time: 12:33
 *  Version: 1.0
 *  Description:
 *  Email:wangchengda1990@gmail.com
 **/
class HistoryFragment : Fragment() {

    var activity : MainActivity ? = null;

    companion object {
        fun newInstance():HistoryFragment{
            return HistoryFragment();
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_history,container,false);
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(activity);
        loadPublishedData();

    }

    /**
     * 获取数据，并刷新界面
     */
    private fun loadPublishedData(){
        activity?.showProgress();
        val api  = Api.Factory.create();
        api.getHistory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({
                    result -> setUpRecyclerview(parseHtml(result.string()))
                },{Log.d("gank","history data on error!")},{
                    activity?.dismissProgress();
                });
    }

    private fun setUpRecyclerview(data:List<History>){
        Log.d("gank","history setup recycleview size ${data.size}");
        val adapter:HistoryAdapter  = HistoryAdapter(R.layout.item_history,data);
        adapter.setOnItemClickListener { adapter, view, i -> activity?.toast("History item click ${i}") }

        recyclerView.adapter = adapter;
    }

    private fun parseHtml(html : String):List<History>{
        val doc : Document = Jsoup.parse(html);
        val typo : Elements = doc.getElementsByClass("typo");

        val data : MutableList<History> = arrayListOf();

        var history : History;
        typo.select("a").listIterator().forEach {
            history = History(it.attr("href").substring(1),it.text());
            data.add(history);
        }
        return data;
     }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.activity = context as MainActivity?
    }

    override fun onDetach() {
        super.onDetach()
        this.activity = null;
    }

}