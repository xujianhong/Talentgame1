package com.daomingedu.talentgame.mvp.model.api.service

import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.CompareFaceScoreBean
import com.daomingedu.talentgame.mvp.model.entity.UploadVideoParamBean
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Description
 * Created by jianhongxu on 4/14/21
 */
interface UploadService {

    @FormUrlEncoded
    @POST
    fun getUploadVideoParam(
        @Url url: String,
        @Field("key") key: String,
        @Field("testSignId") testSignId: String,
        @Field("studentId") studentId: String
    ): Observable<BaseJson<UploadVideoParamBean>>


    @FormUrlEncoded
    @POST
    fun saveUploadVideo(
        @Url url: String,
        @Field("key") key: String,
        @Field("testSignId") testSignId: String,
        @Field("studentId") studentId: String,
        @Field("videoPath") videoPath: String,
        @Field("videoSize") videoSize: String
    ): Observable<BaseJson<Any>>


    @FormUrlEncoded
    @POST
    fun saveLocalVideo(
        @Url url:String,
        @Field("key")key:String,
        @Field("testSignId") testSignId: String,
        @Field("studentId") studentId: String
    ):Observable<BaseJson<Any>>


    @FormUrlEncoded
    @POST("api/common/getWaterMark")
    fun getWaterMark(@Field("key") key: String): Observable<BaseJson<String>>

    /**
     * 获取人脸识别
     */
    @FormUrlEncoded
    @POST("api/student/getCompareFaceScore.do")
    fun getCompareFaceScore(
        @Field("sessionId") sessionId: String,
        @Field("testSignId") testSignId: String,
        @Field("base64Image") base64Image: String,
    ): Observable<BaseJson<CompareFaceScoreBean>>
}