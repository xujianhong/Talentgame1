package com.daomingedu.talentgame.mvp.presenter

import android.app.Application
import android.util.Log
import com.daomingedu.onecp.app.Constant
import com.daomingedu.talentgame.mvp.contract.MusicTestContract
import com.daomingedu.talentgame.mvp.model.api.Api
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.GradeBean
import com.daomingedu.talentgame.mvp.model.entity.TestCityBean
import com.daomingedu.talentgame.mvp.ui.adapter.MusicTestAdapter
import com.daomingedu.talentgame.util.RxUtils
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
import timber.log.Timber
import javax.inject.Inject

/**
 * Description
 * Created by jianhongxu on 4/7/21
 */
@ActivityScope
class MusicTestPresenter
@Inject
constructor(model: MusicTestContract.Model, view: MusicTestContract.View) :
    BasePresenter<MusicTestContract.Model, MusicTestContract.View>(model, view) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager


    @Inject
    lateinit var mAdapter: MusicTestAdapter

    @Inject
    lateinit var mDatas: MutableList<TestCityBean>

    fun gradeList() {
        mModel.gradedList(Constant.KEY)
            .retryWhen(RetryWithDelay(2, 5))
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseJson<MutableList<GradeBean>>>(mErrorHandler) {
                override fun onNext(t: BaseJson<MutableList<GradeBean>>) {
                    if (Api.SUCCESS == t.code) {
                        mDatas.clear()
                        t.data?.get(0)?.cityList?.let { mDatas.addAll(it) }
                        mAdapter.notifyDataSetChanged()

                        mRootView.requestGradeListSuccess(t.data!!)

//                        mGrades.clear()
//                        mGrades.addAll(t.data?: mutableListOf())
//                        mHomeGradAdapter.notifyDataSetChanged()
                    } else {
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }

}