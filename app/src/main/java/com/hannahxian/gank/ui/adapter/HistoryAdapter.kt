package com.hannahxian.gank.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hannahxian.gank.R
import com.hannahxian.gank.repository.History

/**
 *  Created by Broderick
 *  User: Broderick
 *  Date: 2017/6/22
 *  Time: 13:09
 *  Version: 1.0
 *  Description:
 *  Email:wangchengda1990@gmail.com
 **/
class HistoryAdapter(layoutId:Int,data:List<History>) : BaseQuickAdapter<History,BaseViewHolder>(layoutId,data){
    override fun convert(helper: BaseViewHolder?, item: History?) {
        helper?.setText(R.id.content,"${item?.content}(${item?.date})");
    }

}