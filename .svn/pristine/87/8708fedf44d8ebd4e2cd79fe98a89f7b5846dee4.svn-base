package com.daomingedu.talentgame.mvp.model

import android.app.Application
import com.daomingedu.talentgame.mvp.contract.SplashContract
import com.daomingedu.talentgame.mvp.model.api.service.CommonService
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.SessionIdBean
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Description
 * Created by jianhongxu on 4/6/21
 */
@ActivityScope
class SplashModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    SplashContract.Model {
    override fun checkSessionId(sessionId: String): Observable<BaseJson<SessionIdBean>> {
        return mRepositoryManager.obtainRetrofitService(CommonService::class.java)
            .checkSessionId(sessionId)
    }

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;
}