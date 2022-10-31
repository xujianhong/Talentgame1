package com.daomingedu.talentgame.mvp.contract

import com.daomingedu.talentgame.mvp.model.entity.AboutUsBean
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.VersionBean
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
interface MainContract {

    interface View:IView{

        fun requestVersionInfoSuccess(data:VersionBean?)
        fun requestAboutUsSuccess(data: AboutUsBean?, type:Int)

    }


    interface Model:IModel{

        fun getVersionInfo(
            key: String, systemType: Int
        ): Observable<BaseJson<VersionBean>>

        fun aboutUs(
        ): Observable<BaseJson<AboutUsBean>>
    }
}