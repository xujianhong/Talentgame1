package com.daomingedu.talentgame.di.module

import com.daomingedu.talentgame.mvp.contract.MeContract
import com.daomingedu.talentgame.mvp.contract.QuestionContract
import com.daomingedu.talentgame.mvp.model.QuestionModel
import com.jess.arms.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Description
 * Created by jianhongxu on 2021/10/15
 */
@Module
class QuestionModule(val view:QuestionContract.View) {

    @FragmentScope
    @Provides
    fun provideQuestionView():QuestionContract.View{
        return view
    }

    @FragmentScope
    @Provides
    fun provideQuestionModel(model :QuestionModel):QuestionContract.Model{
        return model
    }
}