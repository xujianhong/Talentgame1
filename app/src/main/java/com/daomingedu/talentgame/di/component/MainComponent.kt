package com.daomingedu.talentgame.di.component

import com.daomingedu.talentgame.di.module.MainModule
import com.daomingedu.talentgame.mvp.ui.MainActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
@ActivityScope
@Component(modules = [MainModule::class],dependencies = [AppComponent::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}