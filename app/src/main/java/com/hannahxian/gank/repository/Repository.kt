package com.hannahxian.gank.repository

/**
 * Created by hannahxian on 2017/6/21.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */
data class History(val date:String,val content:String)

data class PublishedDate(val error:Boolean,val results:List<String>)
