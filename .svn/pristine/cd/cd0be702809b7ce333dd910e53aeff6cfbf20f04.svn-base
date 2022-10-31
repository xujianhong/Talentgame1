package com.daomingedu.talentgame.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.mvp.model.entity.TestCityBean

class MusicTestAdapter(datas: MutableList<TestCityBean>) :
    BaseQuickAdapter<TestCityBean,BaseViewHolder>(R.layout.item_music_test,datas){
    override fun convert(helper: BaseViewHolder, item: TestCityBean) {
        helper.setText(R.id.tvTitle,item.cityName)
        helper.setText(R.id.tvSubTitle,item.remark)
    }

}