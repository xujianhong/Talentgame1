package com.daomingedu.talentgame.mvp.presenter

import android.app.Application
import com.daomingedu.onecp.app.Constant
import com.daomingedu.talentgame.app.Preference
import com.daomingedu.talentgame.mvp.contract.PersonalInfoContract
import com.daomingedu.talentgame.mvp.model.api.Api
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.UserInfoBean
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
 * Description
 * Created by jianhongxu on 4/8/21
 */
@ActivityScope
class PersonalInfoPresenter
@Inject constructor(model: PersonalInfoContract.Model, view: PersonalInfoContract.View) :
    BasePresenter<PersonalInfoContract.Model, PersonalInfoContract.View>(model, view) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

    val sessionId by Preference(Constant.SESSIONID, "")

    fun getCustomerInfo() {
        mModel.getCustomerInfo(sessionId)
            .retryWhen(RetryWithDelay(2, 5))
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<UserInfoBean>>(mErrorHandler) {
                override fun onNext(t: BaseJson<UserInfoBean>) {
                    if (Api.SUCCESS == t.code) {
                        mRootView.requestGetCustomerInfoSuccess(t.data)
                    } else {
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }

    fun updateCustomer(
        image: String? = null,
        realName: String? = null,
        nickName: String? = null,
        sex: Int? = null,
        birthday: String? = null,
        motto: String? = null
    ) {
        mModel.updateCustomer(sessionId, image, realName, nickName, sex, birthday, motto)
            .retryWhen(RetryWithDelay(2, 5))
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<Any>>(mErrorHandler) {
                override fun onNext(t: BaseJson<Any>) {
                    if (Api.SUCCESS == t.code) {
                        mRootView.requestUpdateCustomer()
                    } else {
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }
}