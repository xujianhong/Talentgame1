package com.daomingedu.talentgame.di.module

import com.daomingedu.talentgame.mvp.contract.MainContract
import com.daomingedu.talentgame.mvp.model.MainModel
import com.daomingedu.talentgame.mvp.ui.fragment.HomeFragment
import com.daomingedu.talentgame.mvp.ui.fragment.MeFragment
import com.daomingedu.talentgame.mvp.ui.fragment.QuestionFragment
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
@Module
class MainModule(val view: MainContract.View) {

    @ActivityScope
    @Provides
    fun provideMainView(): MainContract.View {
        return view
    }

    @ActivityScope
    @Provides
    fun provideMainModel(model: MainModel): MainContract.Model {
        return model
    }

    @ActivityScope
    @Provides
    fun provideHomeFragment() = HomeFragment.newInstance()

    @ActivityScope
    @Provides
    fun provideMeFragment() = MeFragment.newInstance()

    @ActivityScope
    @Provides
    fun provideQuestionFragment() = QuestionFragment.newInstance()
}