package com.daomingedu.talentgame.di.module

import com.daomingedu.talentgame.mvp.contract.PersonalInfoContract
import com.daomingedu.talentgame.mvp.model.PersonalInfoModel
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */
@Module
class PersonalInfoModule(val view: PersonalInfoContract.View) {

    @ActivityScope
    @Provides
    fun providePersonalInfoView(): PersonalInfoContract.View {
        return view;
    }


    @ActivityScope
    @Provides
    fun providePersonalInfoModel(model: PersonalInfoModel): PersonalInfoContract.Model {
        return model
    }
}