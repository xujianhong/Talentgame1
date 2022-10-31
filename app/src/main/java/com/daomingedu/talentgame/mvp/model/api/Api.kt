package com.daomingedu.talentgame.mvp.model.api

/**
 * Created by jianhongxu on 3/31/21
 * Description
 */
interface Api {
    companion object {

//        const val APP_DOMAIN = "http://art.4hand.com.cn/talentgame/"

        //        const val APP_DOMAIN = "http://114.117.194.109/talentgame/"
        const val APP_DOMAIN = "http://talentgame.4hand.com.cn/"

        const val SUCCESS = 0

        //客服跳转地址
        const val CUSTOMER_SERVICE =
            "https://h5.youzan.com/v3/im/index?c=wsc&v=2&o=https%3A%2F%2Fshop40750931.youzan.com&kdt_id=40558763&type=goods&alias=&source_key=1593077657150&target=%2F#/index"


        val LOGIN_TIME_OUT = 1001
        val BUSINESS_ERROR = 203
        val SYSTEM_ERROR = 202
        val REQUEST_PARAM_ERROR = 201
    }
}