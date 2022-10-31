package com.daomingedu.talentgame.di.module

import com.daomingedu.talentgame.mvp.contract.SplashContract
import com.daomingedu.talentgame.mvp.model.SplashModel
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Description
 * Created by jianhongxu on 4/6/21
 */
@Module
class SplashModule(val view: SplashContract.View) {

    @ActivityScope
    @Provides
    fun provideSplashView(): SplashContract.View {
        return view
    }

    @ActivityScope
    @Provides
    fun provideSplashModel(model: SplashModel): SplashContract.Model {
        return model
    }
}