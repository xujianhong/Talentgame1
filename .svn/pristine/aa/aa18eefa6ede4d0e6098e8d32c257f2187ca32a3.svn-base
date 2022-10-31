package com.daomingedu.talentgame.di.module

import com.daomingedu.talentgame.mvp.contract.MusicRaceUploadContract
import com.daomingedu.talentgame.mvp.model.MusicRaceUploadModel
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Description
 * Created by jianhongxu on 4/14/21
 */
@Module
class MusicRaceUploadModule(val view: MusicRaceUploadContract.View) {

    @ActivityScope
    @Provides
    fun provideMusicUploadView(): MusicRaceUploadContract.View {
        return view
    }

    @ActivityScope
    @Provides
    fun provideMusicUploadModel(model: MusicRaceUploadModel): MusicRaceUploadContract.Model {
        return model
    }
}