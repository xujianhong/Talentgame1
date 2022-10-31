package com.daomingedu.talentgame.di.component

import com.daomingedu.talentgame.di.module.SplashModule
import com.daomingedu.talentgame.mvp.ui.SplashActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

/**
 * Description
 * Created by jianhongxu on 4/6/21
 */
@ActivityScope
@Component(modules = [SplashModule::class], dependencies = [AppComponent::class])
interface SplashComponent {
    fun inject(activity: SplashActivity)
}