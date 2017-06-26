package com.hannahxian.gank.ui.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hannahxian.gank.R
import com.hannahxian.gank.repository.Article

/**
 * Created by hannahxian on 2017/6/26.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */
class GrilAdapter(var context: Context?,layoutId:Int) : BaseQuickAdapter<Article,BaseViewHolder>(layoutId) {
    override fun convert(holder: BaseViewHolder?, item: Article?) {
        val imageView = holder?.getView<ImageView>(R.id.image);
        Glide.with(context).load(item?.url).into(imageView);
    }
}