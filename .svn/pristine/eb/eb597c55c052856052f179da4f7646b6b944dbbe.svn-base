package com.daomingedu.talentgame.mvp.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.daomingedu.onecp.app.Constant

import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.mvp.model.entity.NewsBean

class HomeAdapter(datas: MutableList<NewsBean>) :
    BaseQuickAdapter<NewsBean, BaseViewHolder>(R.layout.item_home_news, datas) {
    override fun convert(helper: BaseViewHolder, item: NewsBean) {

        helper.setText(R.id.tvTitle,item.title)
        helper.setText(R.id.tvSourceOrigin,"${item.source}")
        helper.setText(R.id.tvTime,"${item.createTime}")
        /*helper.getView<ImageView>(R.id.ivCover).apply {
            post {
                loadImage(JHCImageConfig.Builder()
                    .imageRadius(10.px)
                    .imageView(this)
                    .url(Constant.IMAGE_PREFIX+item.image)
                    .build()
                )
            }
        }*/
        Glide.with(mContext).asBitmap().load(Constant.IMAGE_PREFIX+item.image).apply(
            RequestOptions.bitmapTransform(
                RoundedCorners(20)
            )).into(helper.getView<ImageView>(R.id.ivCover))
    }
}