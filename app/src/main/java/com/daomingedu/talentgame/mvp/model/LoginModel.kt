package com.daomingedu.talentgame.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject


import com.daomingedu.talentgame.mvp.contract.LoginContract
import com.daomingedu.talentgame.mvp.model.api.service.CommonService
import com.daomingedu.talentgame.mvp.model.api.service.CustomerService
import com.daomingedu.talentgame.mvp.model.entity.AboutUsBean
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.RegisterBean
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/24/2019 23:28
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class LoginModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    LoginContract.Model {
    override fun login(
        key: String,
        mobile: String,
        password: String,
        channel: Int,
        pushId: String?,
        deviceNo: String?,
        versionCode: Int
    ): Observable<BaseJson<RegisterBean>> {
        return mRepositoryManager.obtainRetrofitService(CustomerService::class.java)
            .login(key, mobile, password, channel, pushId, deviceNo, versionCode)
    }

    override fun aboutUs(): Observable<BaseJson<AboutUsBean>> {
        return mRepositoryManager.obtainRetrofitService(CommonService::class.java).aboutUs()
    }

    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}
