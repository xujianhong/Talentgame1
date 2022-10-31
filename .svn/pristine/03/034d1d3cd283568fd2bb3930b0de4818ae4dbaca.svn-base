package com.daomingedu.talentgame.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent



import com.jess.arms.di.scope.ActivityScope

import com.daomingedu.talentgame.di.module.ForgetPasswordModule
import com.daomingedu.talentgame.mvp.ui.ForgetPasswordActivity


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/27/2019 16:30
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = [ForgetPasswordModule::class], dependencies = [AppComponent::class])
interface ForgetPasswordComponent {
    fun inject(activity: ForgetPasswordActivity)
}
