package com.daomingedu.talentgame.di.component

import com.daomingedu.talentgame.di.module.PersonalInfoModule
import com.daomingedu.talentgame.mvp.ui.PersonalInfoActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component
import java.lang.Appendable

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */

@ActivityScope
@Component(modules = [PersonalInfoModule::class], dependencies = [AppComponent::class])
interface PersonalInfoComponent {

    fun inject(activity: PersonalInfoActivity)
}