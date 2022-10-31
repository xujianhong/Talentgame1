package com.daomingedu.talentgame.mvp.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.onClick
import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.di.component.DaggerRegisterComponent
import com.daomingedu.talentgame.di.module.RegisterModule
import com.daomingedu.talentgame.mvp.contract.RegisterContract
import com.daomingedu.talentgame.mvp.model.entity.AboutUsBean
import com.daomingedu.talentgame.mvp.presenter.RegisterPresenter
import com.daomingedu.talentgame.mvp.ui.wigets.LoadingDialog
import com.daomingedu.talentgame.util.PhoneUtil
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_register.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Description
 * Created by jianhongxu on 4/6/21
 */
class RegisterActivity : BaseActivity<RegisterPresenter>(), RegisterContract.View {
    val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(this)
    }

    var uuID: String = "";

    var disposable: Disposable? = null
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerRegisterComponent.builder().appComponent(appComponent)
            .registerModule(RegisterModule(this)).build().inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_register
    }

    override fun initData(savedInstanceState: Bundle?) {
        title = "注册"
        tvSendCode.onClick {
            val mobile = etInputPhoneNumber.text.toString().trim()
            if (TextUtils.isEmpty(mobile)) {
                ArmsUtils.makeText(application, "手机号不能为空")
                return@onClick
            }
            mPresenter?.sendSMS(mobile)
        }
        register_user.onClick {
            mPresenter?.getProtocol(1)
        }
        register_privacy.onClick {
            mPresenter?.getProtocol(2)
        }
        btnRegister.onClick {
            if(!cbAgree.isChecked){
                ArmsUtils.makeText(application, "请勾选同意相关协议政策")
                return@onClick
            }
            val mobile = etInputPhoneNumber.text.toString().trim()
            if (TextUtils.isEmpty(mobile)) {

                ArmsUtils.makeText(application, "手机号不能为空")
                return@onClick
            }
            val verCode = etVerCode.text.toString().trim()
            if (TextUtils.isEmpty(verCode)) {
                ArmsUtils.makeText(application, "验证码不能为空")
                return@onClick
            }
            val pwd = etInputPwd.text.toString().trim()
            if (TextUtils.isEmpty(pwd)) {
                ArmsUtils.makeText(application, "密码不能为空")
                return@onClick
            }
            val pwdAgain = etInputPwdAgain.text.toString().trim()
            if (pwd != pwdAgain) {
                ArmsUtils.makeText(application, "两次密码不相同")
                return@onClick
            }
            if(TextUtils.isEmpty(uuID)){
                getUUID()
                return@onClick
            }
            mPresenter?.reg(mobile, pwd, verCode,uuID)
        }

        getUUID()
    }

    private fun getUUID(){
        RxPermissions(this).request(Manifest.permission.READ_PHONE_STATE)
            .subscribe {
                if (it) {

                    uuID = PhoneUtil.getDeviceId(this)
                    Log.e("UUID",uuID)


                } else {
                    Toast.makeText(this, "请开启相关权限", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun requestSendSMSSuccess() {
        val count = 60L//倒计时60秒requestRegSuccess
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS)
            .take(count + 1)
            .map { aLong -> count - aLong }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { tvSendCode.isEnabled = false }
            .subscribe({ aLong -> tvSendCode.text = "${aLong}秒" }, { }, {
                tvSendCode.isEnabled = true
                tvSendCode.text = "获取验证码"
            })
    }

    override fun requestRegSuccess() {
        ArmsUtils.makeText(application, "注册账号成功")
        startActivity(Intent(this, MainActivity::class.java))
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


    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }
}