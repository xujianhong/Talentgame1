package com.daomingedu.talentgame.mvp.model

import com.daomingedu.talentgame.mvp.contract.MusicTestContract
import com.daomingedu.talentgame.mvp.model.api.service.CommonService
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.GradeBean
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Description
 * Created by jianhongxu on 4/7/21
 */
@ActivityScope
class MusicTestModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    MusicTestContract.Model {
    override fun gradedList(key: String): Observable<BaseJson<MutableList<GradeBean>>> {
        return mRepositoryManager.obtainRetrofitService(CommonService::class.java).gradedList(key)
    }


}