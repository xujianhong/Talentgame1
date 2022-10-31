package com.daomingedu.talentgame.mvp.ui.fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.loadImage
import com.daomingedu.onecp.app.onClick
import com.daomingedu.talentgame.BuildConfig
import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.app.JHCImageConfig
import com.daomingedu.talentgame.app.Preference
import com.daomingedu.talentgame.di.component.DaggerMeComponent
import com.daomingedu.talentgame.di.module.MeModule
import com.daomingedu.talentgame.mvp.contract.MeContract
import com.daomingedu.talentgame.mvp.model.entity.AboutUsBean
import com.daomingedu.talentgame.mvp.model.entity.CheckCerBean
import com.daomingedu.talentgame.mvp.model.entity.UserInfoBean
import com.daomingedu.talentgame.mvp.presenter.MePresenter
import com.daomingedu.talentgame.mvp.ui.*
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.fragment_me.*

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
class MeFragment : BaseFragment<MePresenter>(), MeContract.View {

    private var mSessionId by Preference(Constant.SESSIONID, "")

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerMeComponent.builder().appComponent(appComponent).meModule(MeModule(this)).build()
            .inject(this)
    }

    companion object {
        const val TAG = "MeFragment"
        fun newInstance(): MeFragment {
            return MeFragment()
        }
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {

        tvVersion.text = BuildConfig.VERSION_NAME


        ivAvatar.onClick {
            if (mSessionId.isEmpty()) {
                startActivity(Intent(mContext, LoginActivity::class.java))

            } else
                startActivity(Intent(mContext, PersonalInfoActivity::class.java))
        }

        btnAboutUs.onClick {
            mPresenter?.aboutUs(MeContract.TYPE_ABOUT_US)
        }

        btnPrivate.onClick {
            mPresenter?.aboutUs(MeContract.TYPE_USE)

        }

        btnPrivacy.onClick {
            mPresenter?.aboutUs(MeContract.TYPE_PRIVACY)
        }

        btnAdvice.onClick {
            startActivity(Intent(mContext, FeedbackActivity::class.java))
        }

        btnModify.onClick {
            startActivity(Intent(mContext, ModifyPasswordActivity::class.java))
        }

        btnClean.onClick {
            AlertDialog.Builder(activity!!).setTitle("注意")
                .setMessage("清除缓存,将清除拍摄的缓存文件!\n确定清除缓存吗?")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val file = activity!!.getExternalFilesDir("Movies")
                        if (file != null) {
                            if (file.exists()) {
                                val list = file.listFiles()
                                if (list != null) {
                                    for (item in list) {
                                        if (item.name.endsWith(".mp4")) {
                                            item.delete()
                                        }
                                    }
                                }
                            }
                        }
                    }
                })
                .show()
        }

        btnVideo.onClick {
            UploadVideoListActivity.startUploadVideoListActivity(
                activity as AppCompatActivity,
                UploadVideoListActivity.TYPE_RETURN_NO,
                UploadVideoListActivity.IMPORT_YES
            )
        }


        btnExit.onClick {
            AlertDialog.Builder(activity!!).setTitle("确认注销登陆?")
                .setNegativeButton("否", null)
                .setPositiveButton("是") { _, _ -> mPresenter?.loginOut() }
                .show()
        }

    }

    override fun onResume() {
        super.onResume()
        btnExit.visibility = if (mSessionId.isNotEmpty()) View.VISIBLE else View.GONE
        btnModify.visibility = if (mSessionId.isNotEmpty()) View.VISIBLE else View.GONE
        mPresenter?.getCustomerInfo()

    }

    override fun setData(data: Any?) {

    }

    override fun requestLogoutSuccess() {
        mSessionId = "";
        ivAvatar.loadImage(
            ImageConfigImpl.builder().imageView(ivAvatar)
                .url(Constant.IMAGE_PREFIX )
                .isCircle(true)
                .errorPic(R.mipmap.avatar_default)
                .placeholder(R.mipmap.avatar_default)
                .build()
        )

        tvNickName.text = "我的"
        tvPhone.text = ""
        startActivity(Intent(mContext, LoginActivity::class.java))
//        activity?.finish()
    }

    override fun requestGetCustomerInfoSuccess(data: UserInfoBean?) {
        data?.apply {
            if (imagePath.isNotEmpty())
                ivAvatar.loadImage(
                    ImageConfigImpl.builder().imageView(ivAvatar)
                        .url(Constant.IMAGE_PREFIX + imagePath)
                        .isCircle(true)
                        .errorPic(R.mipmap.avatar_default)
                        .placeholder(R.mipmap.avatar_default)
                        .build()
                )

            tvNickName.text = nickName
            tvPhone.text = mobile
        }
    }

    override fun requestAboutUsSuccess(data: AboutUsBean?, type: Int) {
        data?.let {

            val intent = Intent(mContext, CommonWebActivity::class.java)
            if (type == MeContract.TYPE_ABOUT_US) {
                intent.putExtra(Constant.URL_EXTRA, it.aboutUs)
                intent.putExtra(Constant.TITLE_EXTRA, "关于我们")
            } else if (type == MeContract.TYPE_USE) {
                intent.putExtra(Constant.URL_EXTRA, it.userAgreement)
                intent.putExtra(Constant.TITLE_EXTRA, "用户协议")
            }else if(type ==MeContract.TYPE_PRIVACY){
                intent.putExtra(Constant.URL_EXTRA, it.privacy)
                intent.putExtra(Constant.TITLE_EXTRA, "隐私政策")
            }
            startActivity(intent)
        }
    }


    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }
}