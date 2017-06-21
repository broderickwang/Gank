package com.hannahxian.gank.repository

/**
 * Created by hannahxian on 2017/6/21.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

//_id: "593f2091421aa92c769a8c6a",
//createdAt: "2017-06-13T07:15:29.423Z",
//desc: "Android之自定义View：侧滑删除",
//publishedAt: "2017-06-15T13:55:57.947Z",
//source: "web",
//type: "Android",
//url: "https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247484934&idx=1&sn=f2a40261efe8ebee45804e9df93c1cce&chksm=96cda74ba1ba2e5dbbac15a9e57b5329176d1fe43478e5c63f7bc502a6ca50e4dfa6c0a9041e#rd",
//used: true,
//who: "陈宇明"
data class Article(val _id:String,val createdAt:String,val desc:String,val publishedAt:String,
                   val source:String,val type:String,val url:String,val used:Boolean,val who:String) {
}