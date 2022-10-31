package com.daomingedu.talentgame.mvp.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.onClick
import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.di.component.DaggerLoginComponent
import com.daomingedu.talentgame.di.module.LoginModule
import com.daomingedu.talentgame.mvp.contract.LoginContract
import com.daomingedu.talentgame.mvp.model.entity.AboutUsBean
import com.daomingedu.talentgame.mvp.presenter.LoginPresenter
import com.daomingedu.talentgame.mvp.ui.wigets.LoadingDialog
import com.daomingedu.talentgame.util.PhoneUtil
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.integration.AppManager
import com.jess.arms.utils.ArmsUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.observers.DisposableCompletableObserver
import kotlinx.android.synthetic.main.activity_login.*
import io.reactivex.observers.DisposableSingleObserver


/**
 * Description
 * Created by jianhongxu on 4/6/21
 */
class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.View {

    private var isJump: Boolean = false; //是否跳转到 MainActivity

    //
    companion object {
        const val JUMP_MAIN = "JUMP_MAIN" //登陆后是否跳转到 mainActivity
    }

    var uuID: String = "";

    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLoginComponent.builder().appComponent(appComponent).loginModule(LoginModule(this))
            .build().inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }

    override fun initData(savedInstanceState: Bundle?) {
        isJump = intent.getBooleanExtra(JUMP_MAIN, false);
//        AppManager.getAppManager().killAll(LoginActivity::class.java)
        btnForget.onClick {
            startActivity(Intent(this, ForgetPasswordActivity::class.java))

        }
        btnRegister.onClick {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        btnRegisterProtocol.onClick {
            mPresenter?.getProtocol(1)
        }
        btnRegisterPrivate.onClick {
            mPresenter?.getProtocol(2)
        }
        btnLogin.onClick {
            val mobile = etInputPhoneNumber.text.toString().trim()
            if (TextUtils.isEmpty(mobile)) {
                ArmsUtils.makeText(application, "手机号不能为空")
                return@onClick
            }
            val pwd = etInputPwd.text.toString().trim()
            if (TextUtils.isEmpty(pwd)) {
                ArmsUtils.makeText(application, "密码不能为空")
                return@onClick
            }
            if(TextUtils.isEmpty(uuID)){
                getUUID()
                return@onClick
            }
            mPresenter?.login(mobile, pwd,uuID)
        }


        getUUID()
    }

    private fun getUUID(){
        RxPermissions(this).request(Manifest.permission.READ_PHONE_STATE)
            .subscribe {
                if (it) {

                    uuID =PhoneUtil.getDeviceId(this)
                    Log.e("UUID",uuID)


                } else {
                    Toast.makeText(this, "请开启相关权限", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun requestLoginSuccess() {
        if (isJump) {
            AppManager.getAppManager().killAll(LoginActivity::class.java)
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }

    override fun requestAboutUsSuccess(data: AboutUsBean?, type: Int) {
        data?.let {
            if (type == 1) {
                val intent: Intent = Intent(this, CommonWebActivity::class.java)
                intent.putExtra(Constant.URL_EXTRA, it.userAgreement)
                intent.putExtra(Constant.TITLE_EXTRA, "用户协议")
                startActivity(intent)
            } else if (type == 2) {
                val intent: Intent = Intent(this, CommonWebActivity::class.java)
                intent.putExtra(Constant.URL_EXTRA, it.privacy)
                intent.putExtra(Constant.TITLE_EXTRA, "隐私政策")
                startActivity(intent)
            }
        }
    }

    override fun showLoading() {
        loadingDialog.show()
    }

    override fun hideLoading() {
        loadingDialog.dismiss()
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }
}