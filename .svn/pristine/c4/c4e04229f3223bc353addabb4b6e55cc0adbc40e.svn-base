package com.daomingedu.talentgame.di.module

import com.daomingedu.talentgame.mvp.contract.MeContract
import com.daomingedu.talentgame.mvp.model.MeModel
import com.jess.arms.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
@Module
class MeModule (val view:MeContract.View){

    @FragmentScope
    @Provides
    fun provideMeView():MeContract.View{
        return view
    }


    @FragmentScope
    @Provides
    fun provideMeModel(model:MeModel):MeContract.Model{
        return model
    }
}