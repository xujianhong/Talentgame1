package com.daomingedu.talentgame.di.component

import com.daomingedu.talentgame.di.module.MusicTestModule
import com.daomingedu.talentgame.mvp.ui.MusicTestActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

/**
 * Description
 * Created by jianhongxu on 4/7/21
 */
@ActivityScope
@Component(modules = [MusicTestModule::class], dependencies = [AppComponent::class])
interface MusicTestComponent {

    fun inject(activity: MusicTestActivity)
}