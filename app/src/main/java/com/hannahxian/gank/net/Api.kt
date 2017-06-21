package com.hannahxian.gank.net

import com.hannahxian.gank.repository.PublishedDate
import com.hannahxian.gank.repository.Result
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by hannahxian on 2017/6/21.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */
interface Api {
    //http://gank.io/api/data/Android/10/1
    @GET("api/data/{type}/{pageSize}/{pageNumber}")
    fun getData(@Path("type") type:String,
                @Path("pageSize") pageSize:String,
                @Path("typpageNumbere") pageNumber:String):Observable<Result>

    @GET("api/day/{year}/{month}/{day}")
    fun getDataByDate(@Path("year") year:String,
                      @Path("month") month:String,
                      @Path("day") day:String)

    @GET("api/day/{date}")
    fun getDataByDate(@Path("date") date:String):Observable<ResponseBody>

    @GET("history")
    fun getHistory():Observable<ResponseBody>

    @GET("api/day/history")
    fun getPublishedDate():Observable<PublishedDate>


    companion object Factory{
        fun create():Api{
            val logging = HttpLoggingInterceptor();
            logging.level = HttpLoggingInterceptor.Level.BASIC;
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://gank.io/")
                    .build();
            return retrofit.create(Api::class.java);
        }
    }
}