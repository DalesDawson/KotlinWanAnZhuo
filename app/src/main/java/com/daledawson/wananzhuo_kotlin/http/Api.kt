package com.daledawson.wananzhuo_kotlin.http

import com.daledawson.wananzhuo_kotlin.bean.BaseResponse
import com.daledawson.wananzhuo_kotlin.bean.CollectionResponse
import com.daledawson.wananzhuo_kotlin.bean.RegisterResponse
import com.daledawson.wananzhuo_kotlin.bean.ScoreData
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/13
 * 修改时间：
 * 修改备注：
 */
interface Api {
    /**
     * 注册
     */
    @POST(HttpProvider.REGISTER)
    fun register(@QueryMap map: Map<String, String>): Call<RegisterResponse>

    /**
     * 登录
     */
    @POST(HttpProvider.LOGIN)
    fun login(@QueryMap map: Map<String, String>): Call<BaseResponse<Any>>

    /**
     * 获取收藏列表
     */
    @GET(HttpProvider.COLLECT_LIST)
    fun getCollectionList(@Path("page") page: Int): Call<CollectionResponse>

    /**
     * 获取首页文章数据
     */
    @GET("/article/list/{page}/json")
    fun getHomeList(@Path("page") pageNo: Int): Observable<BaseResponse<Any>>

    /**
     * 获取首页置顶文章数据
     */
    @GET("/article/top/json")
    fun getTopList(): Observable<BaseResponse<MutableList<Any>>>

    /**
     * banner
     */
    @GET("/banner/json")
    fun getBanner(): Observable<BaseResponse<MutableList<Any>>>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<BaseResponse<Any>>

    @GET("/user/logout/json")
    fun logout(): Call<BaseResponse<Any>>

    /**
     * 获取收藏文章数据
     */
    @GET("/lg/collect/list/{page}/json")
    fun getCollectData(@Path("page") pageNo: Int):
            Call<CollectionResponse>

    /**
     * 获取个人积分
     */
    @GET("/lg/coin/userinfo/json")
    fun getIntegral(): Call<BaseResponse<ScoreData>>

    /**
     * 收藏
     */
    @POST("/lg/collect/{id}/json")
    fun collect(@Path("id") id: Int): Call<BaseResponse<Any>>

    /**
     * 取消收藏
     */
    @POST("/lg/uncollect_originId/{id}/json")
    fun unCollect(@Path("id") id: Int): Observable<BaseResponse<Any>>

    /**
     * 获取项目tab
     */
    @GET("/project/tree/json")
    fun getProjectTabList(): Observable<BaseResponse<MutableList<Any>>>

    /**
     * 获取项目tab
     */
    @GET("/wxarticle/chapters/json  ")
    fun getAccountTabList(): Observable<BaseResponse<MutableList<Any>>>

    /**
     * 获取项目列表
     */
    @GET("/project/list/{pageNum}/json")
    fun getProjectList(@Path("pageNum") pageNum: Int, @Query("cid") cid: Int)
            : Observable<BaseResponse<Any>>

    /**
     * 获取公众号列表
     */
    @GET("/wxarticle/list/{id}/{pageNum}/json")
    fun getAccountList(@Path("id") cid: Int, @Path("pageNum") pageNum: Int)
            : Observable<BaseResponse<Any>>


    /**
     * 获取项目tab
     */
    @POST("/article/query/{pageNum}/json")
    fun search(@Path("pageNum") pageNum: Int, @Query("k") k: String)
            : Observable<BaseResponse<Any>>

    /**
     * 体系
     */
    @GET("/tree/json")
    fun getSystemList(): Observable<BaseResponse<MutableList<Any>>>


    /**
     * 获取项目tab
     */
    @GET("/article/list/{pageNum}/json")
    fun getSystemArticle(@Path("pageNum") pageNum: Int, @Query("cid") cid: Int)
            : Observable<BaseResponse<Any>>

    /**
     * 导航
     */
    @GET("/navi/json")
    fun getNavigation(): Observable<BaseResponse<MutableList<Any>>>

    /**
     * 排名
     */
    @GET("/coin/rank/{pageNum}/json")
    fun getRank(@Path("pageNum") pageNum: Int): Observable<BaseResponse<Any>>

    /**
     * 积分记录
     */
    @GET("/lg/coin/list/{pageNum}/json")
    fun getIntegralRecord(@Path("pageNum") pageNum: Int): Observable<BaseResponse<Any>>

    /**
     * 我分享的文章
     */
    @GET("/user/lg/private_articles/{pageNum}/json")
    fun getMyArticle(@Path("pageNum") pageNum: Int): Observable<BaseResponse<Any>>

    /**
     * 我分享的文章
     */
    @POST("/lg/user_article/delete/{id}/json")
    fun deleteMyArticle(@Path("id") id: Int): Observable<BaseResponse<Any>>

    /**
     * 分享文章
     */
    @POST("/lg/user_article/add/json")
    fun shareArticle(
        @Query("title") title: String,
        @Query("link") link: String
    ): Observable<BaseResponse<Any>>

    /**
     * 注册
     */
    @POST("/user/register")
    fun register(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("repassword") repassword: String
    ): Observable<BaseResponse<Any>>
}