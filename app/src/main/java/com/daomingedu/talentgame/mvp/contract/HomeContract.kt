package com.daomingedu.talentgame.mvp.contract

import com.daomingedu.talentgame.mvp.model.entity.BannerBean
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.HomePictureSet
import com.daomingedu.talentgame.mvp.model.entity.NewsBean
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
interface HomeContract {

    interface View : IView {
        fun requestBannerListSuccess(data: MutableList<BannerBean>?)
        fun requestHomePictureSuccess(data: String?)
        fun requestHomePictureSetSuccess(data: HomePictureSet?)
    }

    interface Model : IModel {

        fun bannerList(
            sessionId: String
        ): Observable<BaseJson<MutableList<BannerBean>>>

        fun newsList(
            sessionId: String, type: Int, start: Int, size: Int
        ): Observable<BaseJson<MutableList<NewsBean>>>

        fun homePicture()
                : Observable<BaseJson<String>>

        fun homePictureSet():
                Observable<BaseJson<HomePictureSet>>
    }
}