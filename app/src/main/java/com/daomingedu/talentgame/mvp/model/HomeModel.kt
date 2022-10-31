package com.daomingedu.talentgame.mvp.model

import android.app.Application
import com.daomingedu.talentgame.mvp.contract.HomeContract
import com.daomingedu.talentgame.mvp.model.api.service.CommonService
import com.daomingedu.talentgame.mvp.model.api.service.NewsService
import com.daomingedu.talentgame.mvp.model.entity.BannerBean
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.HomePictureSet
import com.daomingedu.talentgame.mvp.model.entity.NewsBean
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
@FragmentScope
class HomeModel
@Inject
constructor(repositoryManager:IRepositoryManager):BaseModel(repositoryManager),HomeContract.Model{

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;
    override fun bannerList(sessionId: String): Observable<BaseJson<MutableList<BannerBean>>> {
        return mRepositoryManager.obtainRetrofitService(NewsService::class.java).bannerList(sessionId)
    }

    override fun newsList(
        sessionId: String,
        type: Int,
        start: Int,
        size: Int
    ): Observable<BaseJson<MutableList<NewsBean>>> {
        return mRepositoryManager.obtainRetrofitService(NewsService::class.java).newsList(sessionId, type, start, size)
    }

    override fun homePicture(): Observable<BaseJson<String>> {
        return mRepositoryManager.obtainRetrofitService(CommonService::class.java).homePicture()
    }

    override fun homePictureSet(): Observable<BaseJson<HomePictureSet>> {
        return mRepositoryManager.obtainRetrofitService(CommonService::class.java).homPictureSet()
    }

}