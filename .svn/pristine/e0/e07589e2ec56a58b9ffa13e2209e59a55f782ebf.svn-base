package com.daomingedu.talentgame.di.component

import com.daomingedu.talentgame.di.module.MeModule
import com.daomingedu.talentgame.mvp.ui.fragment.MeFragment

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import dagger.Component

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
@FragmentScope
@Component(modules = [MeModule::class],dependencies = [AppComponent::class])
interface MeComponent {
    fun inject(fragment: MeFragment)
}