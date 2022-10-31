package com.daomingedu.talentgame.di.module

import com.daomingedu.talentgame.mvp.contract.HomeContract
import com.daomingedu.talentgame.mvp.model.HomeModel
import com.daomingedu.talentgame.mvp.model.entity.NewsBean
import com.daomingedu.talentgame.mvp.ui.adapter.HomeAdapter
import com.jess.arms.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
@Module
class HomeModule(val view: HomeContract.View) {

    @FragmentScope
    @Provides
    fun provideHomeView(): HomeContract.View {
        return view
    }

    @FragmentScope
    @Provides
    fun provideHomeModel(model: HomeModel): HomeContract.Model {
        return model
    }

    @FragmentScope
    @Provides
    fun provideHomeAdapter(data:MutableList<NewsBean>):HomeAdapter= HomeAdapter(data)

    @FragmentScope
    @Provides
    fun provideData() = mutableListOf<NewsBean>()
}