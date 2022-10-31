package com.daomingedu.talentgame.di.component

import com.daomingedu.talentgame.di.module.HomeModule
import com.daomingedu.talentgame.mvp.ui.fragment.HomeFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import dagger.Component

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
@FragmentScope
@Component(modules = [HomeModule::class],dependencies = [AppComponent::class])
interface HomeComponent {

    fun inject(fragment:HomeFragment)
}