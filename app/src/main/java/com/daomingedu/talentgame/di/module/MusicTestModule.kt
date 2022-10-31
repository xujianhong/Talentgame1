package com.daomingedu.talentgame.di.module

import com.daomingedu.talentgame.mvp.contract.MusicTestContract
import com.daomingedu.talentgame.mvp.model.MusicTestModel
import com.daomingedu.talentgame.mvp.model.entity.TestCityBean
import com.daomingedu.talentgame.mvp.ui.adapter.MusicTestAdapter
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Description
 * Created by jianhongxu on 4/7/21
 */
@Module
class MusicTestModule(val view:MusicTestContract.View) {

    @ActivityScope
    @Provides
    fun provideMusicTestView():MusicTestContract.View{
        return view
    }

    @ActivityScope
    @Provides
    fun provideMusicTestModel(model:MusicTestModel):MusicTestContract.Model{
        return model
    }


    @ActivityScope
    @Provides
    fun provideAdapter(data:MutableList<TestCityBean>):MusicTestAdapter = MusicTestAdapter(data)


    @ActivityScope
    @Provides
    fun provideDatas() = mutableListOf<TestCityBean>()

}