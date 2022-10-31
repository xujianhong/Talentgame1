package com.daomingedu.talentgame.mvp.contract

import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.UserInfoBean
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */
interface PersonalInfoContract {

    interface View : IView {
        fun requestGetCustomerInfoSuccess(data: UserInfoBean?)
        fun requestUpdateCustomer()
    }

    interface Model : IModel {
        fun getCustomerInfo(
            sessionId: String
        ): Observable<BaseJson<UserInfoBean>>

        fun updateCustomer(
            sessionId: String, image: String?, realName: String?, nickName: String?,
            sex: Int?, birthday: String?, motto: String?
        ): Observable<BaseJson<Any>>
    }
}