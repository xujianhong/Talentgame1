package com.daomingedu.talentgame.mvp.presenter

import android.app.Application
import com.daomingedu.onecp.app.Constant
import com.daomingedu.talentgame.app.Preference
import com.daomingedu.talentgame.mvp.contract.ModifyPasswordContract
import com.daomingedu.talentgame.mvp.model.api.Api
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.util.RxUtils
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
import javax.inject.Inject

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */
@ActivityScope
class ModifyPasswordPresenter
@Inject
constructor(model: ModifyPasswordContract.Model, view: ModifyPasswordContract.View) :
    BasePresenter<ModifyPasswordContract.Model, ModifyPasswordContract.View>(model, view) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

    private var mSessionId: String by Preference(Constant.SESSIONID, "")

    fun changePsw(oldPsw: String, newPsw: String) {
        mModel.changePsw(mSessionId, oldPsw, newPsw)
            .retryWhen(RetryWithDelay(2, 5))
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<Any>>(mErrorHandler) {
                override fun onNext(t: BaseJson<Any>) {
                    if (Api.SUCCESS == t.code) {
                        mSessionId = ""
                        mRootView.requestChangePswSuccess()
                    } else {
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }
}