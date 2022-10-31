package com.daomingedu.talentgame.mvp.ui.view

interface IUploadView {

    fun getSongListResult(result: String)

    fun uploadPathResult(result: String)

    fun getTencentKeyResult(result: String)

    fun getFaceScoreResult(result: String)

    fun getFaceScoreResultFailed(result: String)

    fun saveUploadResult(result: String)

    fun saveUploadFailedResult(result: String)

}