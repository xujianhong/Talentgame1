package com.daomingedu.talentgame.mvp.contract

import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.GradeBean
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Description
 * Created by jianhongxu on 4/7/21
 */
interface MusicTestContract {

    interface View:IView{
        fun requestGradeListSuccess(data:MutableList<GradeBean>)
    }

    interface Model:IModel{

        fun gradedList(key: String
        ): Observable<BaseJson<MutableList<GradeBean>>>
    }


}