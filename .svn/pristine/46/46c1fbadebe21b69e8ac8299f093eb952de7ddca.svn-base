package com.daomingedu.talentgame.mvp.ui

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.mvp.model.entity.LocalWork
import com.daomingedu.talentgame.util.MediaFile
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.LogUtils
import com.jmolsmobile.landscapevideocapture.VideoCaptureActivity
import kotlinx.android.synthetic.main.activity_record_video.*
import java.io.File

/**
 * Description
 * Created by jianhongxu on 4/6/21
 */
class RecordVideoActivity: AppCompatActivity() {

    internal var videofile: Long = Long.MAX_VALUE//限制视频文件大小
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_video)

        title = "考级视频"
        iv_record.setOnClickListener {
            UploadVideoListActivity.startUploadVideoListActivity(this, 0, 0)

        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == LocalWork.VIDEO) { //选择录像
                val path =
                    data!!.getStringExtra(VideoCaptureActivity.EXTRA_OUTPUT_FILENAME)
                LogUtils.warnInfo(path)
                val file = File(path)
                LogUtils.debugInfo("filesize：" + file.length() / 1024f / 1024f)
                if (!file.exists()) {
                    showMessage("视频获取失败")
                    return
                }
                if (!MediaFile.isVideoFileType(path)) {
                    showMessage("请选择视频")
                    return
                }
                LogUtils.debugInfo("视频文件大小：" + file.length() + "字节")
                if (file.length() > videofile) { //文件大于
                    showMessage("视频大于+" + (videofile / (1024 * 1024)).toInt() + "M，建议选择视频作品--录像")
                    return
                }
                sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)))
                AlertDialog.Builder(this@RecordVideoActivity)
                    .setTitle("提示")
                    .setMessage("视频拍摄结束，请在手机\"照片\"库里预览查看和管理")
                    .setPositiveButton("确定", object : DialogInterface.OnClickListener{
                        override fun onClick(
                            dialog: DialogInterface?,
                            which: Int
                        ) {
                            dialog?.dismiss()
                            UploadVideoListActivity.startUploadVideoListActivity(this@RecordVideoActivity, 0, 0)
                            /*val albumIntent = Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                            startActivity(albumIntent)*/
                        }
                    })
                    .setNegativeButton("取消",null)
                    .create()
                    .show()

                //getVideoName(file)
//                showDialog(path)
            }
        }
    }



    fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

}