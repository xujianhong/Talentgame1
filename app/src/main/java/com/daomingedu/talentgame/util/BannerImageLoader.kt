package com.daomingedu.talentgame.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.loader.ImageLoader

class BannerImageLoader: ImageLoader() {
  override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
    Glide.with(context!!)
      .load(path)
      .into(imageView!!)
  }

}