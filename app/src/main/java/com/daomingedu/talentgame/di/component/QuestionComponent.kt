package com.daomingedu.talentgame.di.component

import com.daomingedu.talentgame.di.module.QuestionModule
import com.daomingedu.talentgame.mvp.model.QuestionModel
import com.daomingedu.talentgame.mvp.ui.fragment.QuestionFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import dagger.Component

/**
 * Description
 * Created by jianhongxu on 2021/10/15
 */
@FragmentScope
@Component(modules = [QuestionModule::class],dependencies = [AppComponent::class])
interface QuestionComponent {
    fun inject(fragment: QuestionFragment)
}