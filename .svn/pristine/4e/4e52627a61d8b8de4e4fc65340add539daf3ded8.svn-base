package com.daomingedu.talentgame.mvp.model

import android.app.Application
import com.daomingedu.talentgame.mvp.contract.QuestionContract
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import javax.inject.Inject

/**
 * Description
 * Created by jianhongxu on 2021/10/15
 */
@FragmentScope
class QuestionModel
@Inject
constructor(repositoryManager:IRepositoryManager):BaseModel(repositoryManager),QuestionContract.Model{
    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;
}