package com.daomingedu.talentgame.mvp.presenter

import android.app.Application
import com.daomingedu.onecp.app.Constant
import com.daomingedu.talentgame.app.Preference
import com.daomingedu.talentgame.mvp.contract.MainContract
import com.daomingedu.talentgame.mvp.model.api.Api
import com.daomingedu.talentgame.mvp.model.entity.AboutUsBean
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.VersionBean
import com.daomingedu.talentgame.util.RxUtils
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
import javax.inject.Inject

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
@ActivityScope
class MainPresenter
@Inject
constructor(model: MainContract.Model, view: MainContract.View) : BasePresenter<MainContract.Model, MainContract.View>(model, view) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    private var videoTime by Preference(Constant.VIDEO_TIME,0)
    private var videoPixel by Preference(Constant.VIDEO_PIXEL,0)


    fun getVersionInfo(){
        mModel.getVersionInfo(Constant.KEY,1)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<VersionBean>>(mErrorHandler){
                override fun onNext(t: BaseJson<VersionBean>) {
                    if (t.code == Api.SUCCESS) {
                        videoTime = t.data?.videoTime?:0
                        videoPixel = t.data?.videoPixel?:0
                        mRootView.requestVersionInfoSuccess(t.data)
                    }else{
                        mRootView.showMessage(t.msg)
                    }
                }
            })
    }

    fun getProtocol(type:Int){
        mModel.aboutUs().retryWhen(RetryWithDelay(2, 5))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<AboutUsBean>>(mErrorHandler) {
                override fun onNext(t: BaseJson<AboutUsBean>) {
                    if (Api.SUCCESS == t.code) {
                        mRootView.requestAboutUsSuccess(t.data,type)
                    } else {
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }

}