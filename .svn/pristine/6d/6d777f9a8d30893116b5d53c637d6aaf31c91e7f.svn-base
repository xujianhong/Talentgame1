package com.daomingedu.talentgame.di.component

import com.daomingedu.talentgame.di.module.MusicRaceUploadModule
import com.daomingedu.talentgame.mvp.ui.MusicRaceUploadActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

/**
 * Description
 * Created by jianhongxu on 4/14/21
 */
@ActivityScope
@Component(modules = [MusicRaceUploadModule::class], dependencies = [AppComponent::class])
interface MusicRaceUploadComponent {

    fun inject(activity: MusicRaceUploadActivity)
}