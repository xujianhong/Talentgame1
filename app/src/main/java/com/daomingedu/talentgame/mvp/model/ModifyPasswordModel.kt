package com.daomingedu.talentgame.mvp.model

import com.daomingedu.talentgame.mvp.contract.ModifyPasswordContract
import com.daomingedu.talentgame.mvp.model.api.service.CustomerService
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */
@ActivityScope
class ModifyPasswordModel
@Inject constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    ModifyPasswordContract.Model {
    override fun changePsw(
        sessionId: String,
        oldPsw: String,
        newPsw: String
    ): Observable<BaseJson<Any>> {
        return mRepositoryManager.obtainRetrofitService(CustomerService::class.java)
            .changePsw(sessionId, oldPsw, newPsw)
    }


}