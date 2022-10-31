package com.daomingedu.talentgame.mvp.ui.persenter

interface IUploadPresenter {

    fun getSongList(id: String)

    fun uploadPath(id: String, fileName: String)

    fun getTencentKey()

    fun getFaceScore(id: String, image: String)

    fun saveUpload(id: String, videoPath: String, score: Double, majorLevelSongId: String, videoSize: String, videoCreateTime: String, isAudit: String)

}