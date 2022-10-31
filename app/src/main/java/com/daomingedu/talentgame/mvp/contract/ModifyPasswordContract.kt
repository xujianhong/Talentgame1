package com.daomingedu.talentgame.mvp.contract

import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */
interface ModifyPasswordContract {

    interface View:IView{
        fun requestChangePswSuccess()
    }

    interface Model:IModel{
        fun changePsw(
            sessionId: String, oldPsw: String, newPsw: String
        ): Observable<BaseJson<Any>>
    }
}