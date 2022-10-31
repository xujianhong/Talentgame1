package com.daomingedu.talentgame.mvp.ui


import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.onClick
import com.daomingedu.onecp.app.visible
import com.daomingedu.talentgame.BuildConfig
import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.app.Preference
import com.daomingedu.talentgame.di.component.DaggerMainComponent
import com.daomingedu.talentgame.di.module.MainModule
import com.daomingedu.talentgame.mvp.contract.MainContract
import com.daomingedu.talentgame.mvp.model.entity.AboutUsBean
import com.daomingedu.talentgame.mvp.model.entity.VersionBean
import com.daomingedu.talentgame.mvp.presenter.MainPresenter
import com.daomingedu.talentgame.mvp.ui.fragment.HomeFragment
import com.daomingedu.talentgame.mvp.ui.fragment.MeFragment
import com.daomingedu.talentgame.mvp.ui.fragment.QuestionFragment
import com.daomingedu.talentgame.util.SharedPreferencesUtil
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import constant.UiType
import kotlinx.android.synthetic.main.activity_main.*
import model.UiConfig
import model.UpdateConfig
import timber.log.Timber
import update.UpdateAppUtils
import javax.inject.Inject


class MainActivity : BaseActivity<MainPresenter>(), MainContract.View, View.OnClickListener {

    var isFirst by Preference("isFirst", true)

    @Inject
    lateinit var homeFragment: HomeFragment

    @Inject
    lateinit var meFragment: MeFragment

    @Inject
    lateinit var questionFragment: QuestionFragment


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).mainModule(MainModule(this))
            .build().inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initData(savedInstanceState: Bundle?) {

        switchFragment(homeFragment, HomeFragment.TAG)

        Btn1.onClick(this)
        Btn2.onClick(this)
        Btn3.onClick(this)

        mPresenter?.getVersionInfo()

        if (isFirst)
            showDialog()
    }


    override fun requestVersionInfoSuccess(data: VersionBean?) {
        data?.let {
            SharedPreferencesUtil.setIsShowButton(this, it.isShowButton)
            SharedPreferencesUtil.setIsShowFolder(this, it.isShowFolder)
            SharedPreferencesUtil.setIsShowVideo(this, it.isShowVideo)

            Btn3.visible(data.isQuestionBank == 1)

            if (BuildConfig.VERSION_CODE < it.versionCode) {
                // ui配置


                // 更新配置
                val updateConfig = UpdateConfig().apply {
                    force = it.isMust == 1
                    checkWifi = true
//                    needCheckMd5 = true
                    isShowNotification = true
                    alwaysShowDownLoadDialog = true
                    notifyImgRes = R.mipmap.ic_logo
//                    apkSavePath = Environment.getExternalStorageDirectory().absolutePath +"/teprinciple"
//                    apkSaveName = "teprinciple"
                }
                UpdateAppUtils
                    .getInstance()
                    .apkUrl(it.path)
                    .updateTitle("发现新版本${it.versionName}")
                    .updateContent(it.remark)
                    .updateConfig(updateConfig)
                    .update()
            }
        }

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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.Btn1 -> {
                onTabSelected(HomeFragment.TAG)
            }
            R.id.Btn2 -> {
                onTabSelected(MeFragment.TAG)
            }
            R.id.Btn3 -> {
                onTabSelected(QuestionFragment.TAG)
            }
        }
    }

    fun onTabSelected(tag: String) {
        when (tag) {
            HomeFragment.TAG -> {
                val dMain: Drawable = ContextCompat.getDrawable(this, R.mipmap.icon_main_true)!!
                dMain.setBounds(0, 0, dMain.minimumWidth, dMain.minimumHeight)


                val dQuestion: Drawable = ContextCompat.getDrawable(this, R.mipmap.icon_question_false)!!
                dQuestion.setBounds(0, 0, dQuestion.minimumWidth, dQuestion.minimumHeight)


                val dMe: Drawable = ContextCompat.getDrawable(this, R.mipmap.icon_me_false)!!
                dMe.setBounds(0, 0, dMe.minimumWidth, dMe.minimumHeight)



                switchFragment(homeFragment, HomeFragment.TAG)

                runOnUiThread {

                    Btn1.setCompoundDrawables(
                        null,
                        dMain,
                        null,
                        null
                    )
                    Btn1.setTextColor(ContextCompat.getColor(this, R.color.green_700))

                    Btn3.setCompoundDrawables(null, dQuestion, null, null)
                    Btn3.setTextColor(ContextCompat.getColor(this, R.color.black))

                    Btn2.setCompoundDrawables(
                        null,
                        dMe,
                        null,
                        null
                    )
                    Btn2.setTextColor(ContextCompat.getColor(this, R.color.black))
                }

            }
            MeFragment.TAG -> {
                val dMain: Drawable = ContextCompat.getDrawable(this, R.mipmap.icon_main_false)!!
                dMain.setBounds(0, 0, dMain.minimumWidth, dMain.minimumHeight)


                val dQuestion: Drawable = ContextCompat.getDrawable(this, R.mipmap.icon_question_false)!!
                dQuestion.setBounds(0, 0, dQuestion.minimumWidth, dQuestion.minimumHeight)


                val dMe: Drawable = ContextCompat.getDrawable(this, R.mipmap.icon_me_true)!!
                dMe.setBounds(0, 0, dMe.minimumWidth, dMe.minimumHeight)


                switchFragment(meFragment, MeFragment.TAG)

                runOnUiThread {

                    Btn1.setCompoundDrawables(
                        null,
                        dMain,
                        null,
                        null
                    )
                    Btn1.setTextColor(ContextCompat.getColor(this, R.color.black))

                    Btn3.setCompoundDrawables(null, dQuestion, null, null)
                    Btn3.setTextColor(ContextCompat.getColor(this, R.color.black))

                    Btn2.setCompoundDrawables(
                        null,
                        dMe,
                        null,
                        null
                    )
                    Btn2.setTextColor(ContextCompat.getColor(this, R.color.green_700))
                }

            }
            QuestionFragment.TAG -> {

                switchFragment(questionFragment, QuestionFragment.TAG)


                    val dMain: Drawable = ContextCompat.getDrawable(this, R.mipmap.icon_main_false)!!
                    dMain.setBounds(0, 0, dMain.minimumWidth, dMain.minimumHeight)



                    val dQuestion: Drawable = ContextCompat.getDrawable(this, R.mipmap.icon_question_true)!!
                    dQuestion.setBounds(0, 0, dQuestion.minimumWidth, dQuestion.minimumHeight)


                    val dMe: Drawable = ContextCompat.getDrawable(this, R.mipmap.icon_me_false)!!
                    dMe.setBounds(0, 0, dMe.minimumWidth, dMe.minimumHeight)


                runOnUiThread {

                    Btn1.setCompoundDrawables(
                        null,
                        dMain,
                        null,
                        null
                    )
                    Btn1.setTextColor(ContextCompat.getColor(this, R.color.black))

                    Btn3.setCompoundDrawables(null, dQuestion, null, null)
                    Btn3.setTextColor(ContextCompat.getColor(this, R.color.green_700))

                    Btn2.setCompoundDrawables(
                        null,
                        dMe,
                        null,
                        null
                    )
                    Btn2.setTextColor(ContextCompat.getColor(this, R.color.black))
                }




            }

        }


    }

    var mCurrentFragment: Fragment? = null
    private fun switchFragment(to: Fragment, TAG: String) {
        if (mCurrentFragment != to) {
            if (supportFragmentManager.findFragmentByTag(TAG) == null) {
                if (mCurrentFragment == null) {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.fragmentContainerView, to, TAG).commit()
                } else {
                    supportFragmentManager.beginTransaction().hide(mCurrentFragment!!)
                        .add(R.id.fragmentContainerView, to, TAG).commit()
                }

            } else {
                if (mCurrentFragment == null) {
                    supportFragmentManager.beginTransaction().show(to).commit()
                } else
                    supportFragmentManager.beginTransaction().hide(mCurrentFragment!!).show(to)
                        .commit()
            }

            mCurrentFragment = to
        }
    }

    var lastEndTime = 0L
    override fun onBackPressed() {
        val curTime = System.currentTimeMillis()
        val intervalTime = curTime - lastEndTime
        lastEndTime = curTime
        if (intervalTime < 2000) {
            super.onBackPressed()
        } else {
            ArmsUtils.makeText(applicationContext, "再按一次退出")
        }
    }


    private fun showDialog() {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.show()
        alertDialog.setCancelable(false)
        val window = alertDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.diag_privacy);
            window.setGravity(Gravity.CENTER);

            val tvContent: TextView = window.findViewById(R.id.tv_content);
            val tvCancel: TextView = window.findViewById(R.id.tv_cancel);
            val tvAgree: TextView = window.findViewById(R.id.tv_agree)


            val str = "    感谢您对本公司的支持!本公司非常重视您的个人信息和隐私保护。" +
                    "为了更好地保障您的个人权益，在您使用我们的产品前，" +
                    "请务必审慎阅读《隐私政策》和《用户协议》内的所有条款，" +
                    "尤其是:\n" +
                    " 1.我们对您的个人信息的收集/保存/使用/对外提供/保护等规则条款，以及您的用户权利等条款;\n" +
                    " 2. 约定我们的限制责任、免责条款;\n" +
                    " 3.其他以颜色或加粗进行标识的重要条款。\n" +
                    "您点击“同意并继续”的行为即表示您已阅读完毕并同意以上协议的全部内容。" +
                    "如您同意以上协议内容，请点击“同意”，开始使用我们的产品和服务!";

            val ssb = SpannableStringBuilder();
            ssb.append(str);
            val start = str.indexOf("《");//第一个出现的位置
            ssb.setSpan(object : ClickableSpan() {

                override fun onClick(widget: View) {
                    mPresenter?.getProtocol(2)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = getResources().getColor(R.color.green_700);
                    ds.isUnderlineText = false;
                }
            }, start, start + 6, 0);

            val end = str.lastIndexOf("《");
            ssb.setSpan(object : ClickableSpan() {

                override fun onClick(widget: View) {
                    mPresenter?.getProtocol(1)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = getResources().getColor(R.color.green_700);
                    ds.isUnderlineText = false;
                }
            }, end, end + 6, 0);

            tvContent.movementMethod = LinkMovementMethod.getInstance();
            tvContent.setText(ssb, TextView.BufferType.SPANNABLE);


            tvCancel.setOnClickListener {
                alertDialog.cancel();
                finish();
            };

            tvAgree.setOnClickListener {
                isFirst = false
                alertDialog.cancel();
            }

        }

    }
}

