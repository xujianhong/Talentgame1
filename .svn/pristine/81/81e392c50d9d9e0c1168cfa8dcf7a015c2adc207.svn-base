package com.daomingedu.talentgame.mvp.presenter

import android.app.Application
import com.daomingedu.talentgame.mvp.contract.QuestionContract
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

/**
 * Description
 * Created by jianhongxu on 2021/10/15
 */
@FragmentScope
class QuestionPresenter
@Inject
constructor(model: QuestionContract.Model,view:QuestionContract.View):
BasePresenter<QuestionContract.Model,QuestionContract.View>(model,view){

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

}