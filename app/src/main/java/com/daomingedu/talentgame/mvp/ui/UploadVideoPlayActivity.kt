package com.daomingedu.talentgame.mvp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.util.MediaController
import com.pili.pldroid.player.PLOnCompletionListener
import com.pili.pldroid.player.PLOnErrorListener
import kotlinx.android.synthetic.main.activity_upload_video_play.*

import kotlinx.android.synthetic.main.toolbar.*

/**
 * Description
 * Created by jianhongxu on 4/6/21
 */
class UploadVideoPlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_video_play)

        toolbar_new_title.text = "视频预览"
        toolbar_new.setNavigationOnClickListener { finish() }

        val path = intent.extras?.getString("path")!!

        initVideoPlay(path)
    }

    override fun onDestroy() {
        super.onDestroy()
        PLVideoTextureView.stopPlayback()
    }

    companion object {

        fun startUploadVideoPlayActivity(activity: AppCompatActivity, path: String){
            val intent = Intent(activity, UploadVideoPlayActivity::class.java)
            intent.putExtra("path", path)
            activity.startActivity(intent)
        }
    }

    private fun initVideoPlay(videoPath: String){
        PLVideoTextureView.setMediaController(MediaController(this))
        PLVideoTextureView.setVideoPath(videoPath)
        PLVideoTextureView.start()
        PLVideoTextureView.setOnErrorListener(object : PLOnErrorListener {
            override fun onError(p0: Int): Boolean {
                return false
            }
        })
        PLVideoTextureView.setOnCompletionListener(object : PLOnCompletionListener {
            override fun onCompletion() {
            }
        })
    }
}