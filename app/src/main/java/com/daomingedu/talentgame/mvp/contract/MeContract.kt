package com.daomingedu.talentgame.mvp.contract

import com.daomingedu.talentgame.mvp.model.entity.AboutUsBean
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.CheckCerBean
import com.daomingedu.talentgame.mvp.model.entity.UserInfoBean
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
interface MeContract {

    companion object {
        const val TYPE_ABOUT_US = 0x01
        const val TYPE_PRIVACY = 0x02
        const val TYPE_USE = 0x03;
    }

    interface View : IView {
        fun requestLogoutSuccess()
        fun requestGetCustomerInfoSuccess(data: UserInfoBean?)
        fun requestAboutUsSuccess(data: AboutUsBean?, type: Int)
    }

    interface Model : IModel {

        fun logout(
            sessionId: String
        ): Observable<BaseJson<Any>>

        fun getCustomerInfo(
            sessionId: String
        ): Observable<BaseJson<UserInfoBean>>

        fun aboutUs(): Observable<BaseJson<AboutUsBean>>


    }
}