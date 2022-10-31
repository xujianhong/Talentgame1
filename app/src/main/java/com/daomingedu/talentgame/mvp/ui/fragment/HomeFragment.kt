package com.daomingedu.talentgame.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.onClick
import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.di.component.DaggerHomeComponent
import com.daomingedu.talentgame.di.module.HomeModule
import com.daomingedu.talentgame.mvp.contract.HomeContract
import com.daomingedu.talentgame.mvp.model.entity.BannerBean
import com.daomingedu.talentgame.mvp.model.entity.HomePictureSet
import com.daomingedu.talentgame.mvp.presenter.HomePresenter
import com.daomingedu.talentgame.mvp.ui.CommonWebActivity
import com.daomingedu.talentgame.mvp.ui.MusicTestActivity
import com.daomingedu.talentgame.mvp.ui.adapter.HomeAdapter
import com.daomingedu.talentgame.util.BannerImageLoader
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
class HomeFragment : BaseFragment<HomePresenter>(), HomeContract.View {

    @Inject
    lateinit var mAdapter: HomeAdapter

    private val bannerList = mutableListOf<BannerBean>()
    private var bannerImages = mutableListOf<String>()

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHomeComponent.builder().appComponent(appComponent).homeModule(HomeModule(this))
            .build().inject(this)
    }

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {

        banner.setImageLoader(BannerImageLoader())
        banner.setOnBannerListener {
            val url = bannerList[it].url
            val title = bannerList[it].title
            if (!TextUtils.isEmpty(url)) {
                startActivity(
                    Intent(
                        mContext,
                        CommonWebActivity::class.java
                    ).putExtra(Constant.URL_EXTRA, url).putExtra(Constant.TITLE_EXTRA, title)
                )
            }
        }

        recyclerView.isNestedScrollingEnabled = false
        recyclerView.addItemDecoration(DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL))
        recyclerView.adapter = mAdapter.apply {
            setOnItemClickListener { _, _, position ->
                mAdapter.getItem(position)?.apply {
                    if (!TextUtils.isEmpty(url)) {
                        startActivity(
                            Intent(
                                mContext,
                                CommonWebActivity::class.java
                            ).putExtra(Constant.URL_EXTRA, url).putExtra(Constant.TITLE_EXTRA, "资讯")
                        )
                    }
                }
            }
        }

        btnRaceEnter.onClick {
           mPresenter?.homePictureSet()
        }


        mPresenter?.getBanner()
        mPresenter?.newsList(true)
        mPresenter?.homePicture()
    }

    override fun setData(data: Any?) {

    }

    override fun onResume() {
        super.onResume()
        banner.startAutoPlay()
    }

    override fun onPause() {
        super.onPause()
        banner.stopAutoPlay()
    }

    override fun requestBannerListSuccess(data: MutableList<BannerBean>?) {
        data?.let {
            bannerList.addAll(it)
        }
        data?.forEach { item ->
            bannerImages.add(Constant.IMAGE_PREFIX+item.image)
        }
        banner.setImages(bannerImages).start()
    }

    override fun requestHomePictureSuccess(data: String?) {
        data?.let {
            Glide.with(mContext).load(it).fitCenter().into(btnRaceEnter)
        }
    }

    override fun requestHomePictureSetSuccess(data: HomePictureSet?) {
        data?.let {
            if(it.isClick==1) {
                val intent = Intent(mContext, MusicTestActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }
}