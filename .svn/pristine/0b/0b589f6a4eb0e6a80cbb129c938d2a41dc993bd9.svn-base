package com.daomingedu.talentgame.di.component

import com.daomingedu.talentgame.di.module.RegisterModule
import com.daomingedu.talentgame.mvp.ui.RegisterActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/25/2019 20:07
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = [RegisterModule::class], dependencies = [AppComponent::class])
interface RegisterComponent {
    fun inject(activity: RegisterActivity)
}
