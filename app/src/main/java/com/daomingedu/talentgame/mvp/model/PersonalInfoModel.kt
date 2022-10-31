package com.daomingedu.talentgame.mvp.model

import com.daomingedu.talentgame.mvp.contract.PersonalInfoContract
import com.daomingedu.talentgame.mvp.model.api.service.CustomerService
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.UserInfoBean
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
class PersonalInfoModel
@Inject constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    PersonalInfoContract.Model {
    override fun getCustomerInfo(sessionId: String): Observable<BaseJson<UserInfoBean>> {
        return mRepositoryManager.obtainRetrofitService(CustomerService::class.java)
            .getCustomerInfo(sessionId)
    }

    override fun updateCustomer(
        sessionId: String,
        image: String?,
        realName: String?,
        nickName: String?,
        sex: Int?,
        birthday: String?,
        motto: String?
    ): Observable<BaseJson<Any>> {
        return mRepositoryManager.obtainRetrofitService(CustomerService::class.java)
            .updateCustomer(sessionId, image, realName, nickName, sex, birthday, motto)
    }


}