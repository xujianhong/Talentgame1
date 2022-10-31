package com.daomingedu.talentgame.mvp.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.daomingedu.onecp.app.onClick
import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.di.component.DaggerModifyPasswordComponent
import com.daomingedu.talentgame.di.module.ModifyPasswordModule
import com.daomingedu.talentgame.mvp.contract.ModifyPasswordContract
import com.daomingedu.talentgame.mvp.presenter.ModifyPasswordPresenter
import com.daomingedu.talentgame.mvp.ui.wigets.LoadingDialog
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_modify_password.*

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */
class ModifyPasswordActivity : BaseActivity<ModifyPasswordPresenter>(),
    ModifyPasswordContract.View {

    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerModifyPasswordComponent.builder().appComponent(appComponent).modifyPasswordModule(
            ModifyPasswordModule(this)
        ).build().inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_modify_password
    }

    override fun initData(savedInstanceState: Bundle?) {
        title = "修改密码"
        btnComplete.onClick {
            val oldPwd = etInputPwd.text.toString().trim()
            if (TextUtils.isEmpty(oldPwd)) {
                ArmsUtils.makeText(application,"旧密码不能为空")
                return@onClick
            }
            val pwd = etInputPwdAgain.text.toString().trim()
            if (TextUtils.isEmpty(pwd)) {
                ArmsUtils.makeText(application,"密码不能为空")
                return@onClick
            }
            mPresenter?.changePsw(oldPwd,pwd)
        }
    }

    override fun requestChangePswSuccess() {
        ArmsUtils.makeText(application,"修改密码成功,请重新登录")
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun showLoading() {
        loadingDialog.show()
    }

    override fun hideLoading() {
        loadingDialog.hide()
    }
}