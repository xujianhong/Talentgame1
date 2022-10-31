package com.daomingedu.talentgame.di.module

import com.daomingedu.talentgame.mvp.contract.ModifyPasswordContract
import com.daomingedu.talentgame.mvp.model.ModifyPasswordModel
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */
@Module
class ModifyPasswordModule(val view: ModifyPasswordContract.View) {

    @ActivityScope
    @Provides
    fun provideModifyPasswordView(): ModifyPasswordContract.View {
        return view
    }

    @ActivityScope
    @Provides
    fun provideModifyPasswordModel(model: ModifyPasswordModel): ModifyPasswordContract.Model {
        return model
    }
}