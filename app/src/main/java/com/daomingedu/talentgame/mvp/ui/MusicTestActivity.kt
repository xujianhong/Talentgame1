package com.daomingedu.talentgame.mvp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.onClick
import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.di.component.DaggerMusicTestComponent
import com.daomingedu.talentgame.di.module.MusicTestModule
import com.daomingedu.talentgame.mvp.contract.MusicTestContract
import com.daomingedu.talentgame.mvp.model.entity.GradeBean
import com.daomingedu.talentgame.mvp.model.entity.TestCityBean
import com.daomingedu.talentgame.mvp.presenter.MusicTestPresenter
import com.daomingedu.talentgame.mvp.ui.adapter.MusicTestAdapter
import com.daomingedu.talentgame.util.SharedPreferencesUtil
import com.daomingedu.talentgame.util.SharedPreferencesUtil.setLimit
import com.daomingedu.talentgame.util.SharedPreferencesUtil.setWater
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_music_test.*
import kotlinx.android.synthetic.main.include_title.*
import javax.inject.Inject

/**
 * Description
 * Created by jianhongxu on 4/7/21
 */
class MusicTestActivity : BaseActivity<MusicTestPresenter>(), MusicTestContract.View {

    @Inject
    lateinit var mAdapter: MusicTestAdapter

    @Inject
    lateinit var mDatas: MutableList<TestCityBean>

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMusicTestComponent.builder().appComponent(appComponent).musicTestModule(
            MusicTestModule(this)
        ).build().inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_music_test
    }

    override fun initData(savedInstanceState: Bundle?) {

        if (SharedPreferencesUtil.getIsShowFolder(this) == 1) {
            btnRecord.visibility = View.VISIBLE
        } else {
            btnRecord.visibility = View.GONE
        }

        btnRecord.onClick {
            UploadVideoListActivity.startUploadVideoListActivity(
                this,
                UploadVideoListActivity.TYPE_RETURN_NO,
                UploadVideoListActivity.IMPORT_NO
            )
        }

//        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = mAdapter.apply {
            setOnItemClickListener { adapter, view, position ->
                mAdapter.getItem(position)?.let {
                    setWater(this@MusicTestActivity, it.isWaterMark)
                    setLimit(this@MusicTestActivity, it.videoLimit)
                    startActivity(
                        Intent(
                            this@MusicTestActivity,
                            CommonWebActivity::class.java
                        ).putExtra(Constant.URL_EXTRA, it.url)
                            .putExtra(Constant.TITLE_EXTRA, it.cityName)
                    )
                }
            }
        }

        mPresenter?.gradeList()
    }

    override fun requestGradeListSuccess(data: MutableList<GradeBean>) {
        toolbar_title.text = data[0].gradedNamae
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

}