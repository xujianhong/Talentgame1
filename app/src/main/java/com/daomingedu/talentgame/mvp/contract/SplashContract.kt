package com.daomingedu.talentgame.mvp.contract

import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.SessionIdBean
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Description
 * Created by jianhongxu on 4/6/21
 */
interface SplashContract {

    interface View:IView{
        fun requestCheckSessionIdSuccess(data: SessionIdBean?)
    }

    interface Model:IModel{
        fun checkSessionId(
            sessionId: String
        ): Observable<BaseJson<SessionIdBean>>
    }
}