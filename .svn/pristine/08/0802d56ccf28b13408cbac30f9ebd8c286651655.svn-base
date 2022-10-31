package com.daomingedu.talentgame.mvp.ui

import android.content.Intent
import android.os.Bundle
import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.di.component.DaggerSplashComponent
import com.daomingedu.talentgame.di.module.SplashModule
import com.daomingedu.talentgame.mvp.contract.SplashContract
import com.daomingedu.talentgame.mvp.model.entity.SessionIdBean
import com.daomingedu.talentgame.mvp.presenter.SplashPresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import java.util.*

/**
 * Description
 * Created by jianhongxu on 4/6/21
 */
class SplashActivity : BaseActivity<SplashPresenter>(), SplashContract.View {
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerSplashComponent.builder().appComponent(appComponent).splashModule(SplashModule(this))
            .build().inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash
    }

    override fun initData(savedInstanceState: Bundle?) {
        val t = Timer();
        t.schedule(object :TimerTask(){
            override fun run() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                overridePendingTransition(0, 0)
                finish()
            }

        },3000)
//        mPresenter?.checkSessionId()
    }

    override fun requestCheckSessionIdSuccess(data: SessionIdBean?) {
        when (data?.status) {
            "ok" -> {
                startActivity(Intent(this, MainActivity::class.java))
                overridePendingTransition(0, 0)
                finish()
            }
            else -> {
                startActivity(Intent(this, LoginActivity::class.java))
                overridePendingTransition(0, 0)
                finish()
            }
        }
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }
}