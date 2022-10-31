package com.daomingedu.talentgame.mvp.ui.adapter

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.daomingedu.onecp.app.Constant
import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.database.Video
import com.daomingedu.talentgame.util.Utils
import kotlinx.android.synthetic.main.item_videolist.view.*
import java.io.File

/**
 * Description
 * Created by jianhongxu on 4/30/21
 */
class MusicRaceUploadAdapter(data: MutableList<Video>) :
    BaseQuickAdapter<Video, BaseViewHolder>(R.layout.item_videolist,data) {
    override fun convert(helper: BaseViewHolder?, item: Video?) {



            Glide.with(mContext).asBitmap().load(item?.filePath).apply(
                RequestOptions.bitmapTransform(
                    RoundedCorners(20)
                )
            ).into(helper!!.getView<ImageView>(R.id.item_videolist_iv))


        helper?.setText(R.id.tvFileName,item?.fileName)
        helper?.setText(R.id.tvCreateTime,"拍摄于 "+ Utils.getTime(File(item?.filePath).lastModified(),"yyyy-MM-dd HH:mm"))

        helper?.addOnClickListener(R.id.item_videolist_scan)
        helper?.addOnClickListener(R.id.item_videolist_select)
    }

}