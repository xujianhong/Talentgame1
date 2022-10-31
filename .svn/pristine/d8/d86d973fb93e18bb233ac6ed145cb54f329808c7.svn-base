package com.daomingedu.talentgame.mvp.presenter

import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.daomingedu.onecp.app.Constant
import com.daomingedu.talentgame.app.Preference
import com.daomingedu.talentgame.mvp.contract.MusicRaceUploadContract
import com.daomingedu.talentgame.mvp.model.api.Api
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.daomingedu.talentgame.mvp.model.entity.CompareFaceScoreBean
import com.daomingedu.talentgame.mvp.model.entity.LocalWork
import com.daomingedu.talentgame.mvp.model.entity.UploadVideoParamBean
import com.daomingedu.talentgame.util.MemoryUtil
import com.daomingedu.talentgame.util.RxUtils
import com.daomingedu.talentgame.util.SharedPreferencesUtil
import com.daomingedu.talentgame.util.Utils
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jmolsmobile.landscapevideocapture.VideoCaptureActivity
import com.jmolsmobile.landscapevideocapture.configuration.CaptureConfiguration
import com.jmolsmobile.landscapevideocapture.configuration.PredefinedCaptureConfigurations
import io.reactivex.android.schedulers.AndroidSchedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
import retrofit2.http.Field
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

/**
 * Description
 * Created by jianhongxu on 4/14/21
 */
@ActivityScope
class MusicRaceUploadPresenter
@Inject constructor(model: MusicRaceUploadContract.Model, view: MusicRaceUploadContract.View) :
    BasePresenter<MusicRaceUploadContract.Model, MusicRaceUploadContract.View>(model, view) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

    private val sessionId by Preference(Constant.SESSIONID, "")

    private var videoTime by Preference(Constant.VIDEO_TIME, 0)
    private var videoPixel by Preference(Constant.VIDEO_PIXEL, 0)

    fun getUploadVideoParam(
        url: String,
        testSignId: String,
        studentId: String
    ) {
        mModel.getUploadVideoParam(url, "0afe0ff023194bcfb8c90a50f92c8bcd", testSignId, studentId)
            .retryWhen(RetryWithDelay(2, 5))
            .compose(RxUtils.applySchedulers(mRootView))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :
                ErrorHandleSubscriber<BaseJson<UploadVideoParamBean>>(mErrorHandler) {
                override fun onNext(t: BaseJson<UploadVideoParamBean>) {
                    if (Api.SUCCESS == t.code) {
                        t.data?.let { mRootView.requestUploadVideoParamSuccess(it) }
                    } else {
                        mRootView.requestUploadVideoParamError(t.msg)
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }

    fun saveUploadVideo(
        url: String,
        testSignId: String,
        studentId: String,
        videoPath: String,
        videoSize: String
    ) {
        mModel.saveUploadVideo(
            url,
            "0afe0ff023194bcfb8c90a50f92c8bcd",
            testSignId,
            studentId,
            videoPath,
            videoSize
        )
            .retryWhen(RetryWithDelay(2, 5))
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<Any>>(mErrorHandler) {
                override fun onNext(t: BaseJson<Any>) {
                    if (Api.SUCCESS == t.code) {
                        mRootView.requestSaveUploadVideoSuccess()
                    } else {
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }

    fun saveLocalVideo(
        url: String,
        testSignId: String,
        studentId: String
    ) {
        mModel.saveLocalVideo( url,
            "0afe0ff023194bcfb8c90a50f92c8bcd",
            testSignId,
            studentId)
            .retryWhen(RetryWithDelay(2, 5))
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<Any>>(mErrorHandler) {
                override fun onNext(t: BaseJson<Any>) {
                    if (Api.SUCCESS == t.code) {
                        mRootView.requestSaveLocalVideoSuccess()
                    } else {
                        mRootView.requestSaveLocalVideoError(t.msg)
                        mRootView.showMessage(t.msg)
                    }
                }

                override fun onError(t: Throwable) {
                    super.onError(t)
                    mRootView.requestSaveLocalVideoError(t.message.toString())
                }

            })
    }

    fun getWaterMark() {
        mModel.getWaterMark(Constant.KEY)
            .retryWhen(RetryWithDelay(2, 5))
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<String>>(mErrorHandler) {
                override fun onNext(t: BaseJson<String>) {
                    if (Api.SUCCESS == t.code) {

                        val bitmap = Utils.decodeImage(t.data!!)
                        if (!saveBitmapToSdCard(mApplication, bitmap!!, "test")) return
                        when (MemoryUtil.memoryIsAvailble(mApplication)) {
                            MemoryUtil.MEMORY_OK -> {
                                mRootView.requestWaterMarkSuccess()
                            }
                            MemoryUtil.MEMORY_OUT -> {
                                val dialog = AlertDialog.Builder(mApplication)
                                dialog.setMessage("存储空间过小, 可能会出现录制完了无法保存视频的情况, 是否继续拍摄视频")
                                dialog.setCancelable(false)
                                dialog.setPositiveButton(
                                    "继续",
                                    object : DialogInterface.OnClickListener {
                                        override fun onClick(dialog: DialogInterface?, which: Int) {
                                            dialog?.dismiss()
                                            mRootView.requestWaterMarkSuccess()
                                        }
                                    })
                                dialog.setNegativeButton(
                                    "取消",
                                    object : DialogInterface.OnClickListener {
                                        override fun onClick(dialog: DialogInterface?, which: Int) {
                                            dialog?.dismiss()
                                        }
                                    })
                                dialog.create().show()
                            }
                        }


                    } else {
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }

    fun saveBitmapToSdCard(
        context: Context,
        mybitmap: Bitmap,
        name: String
    ): Boolean {
        var result = false
        //创建位图保存目录
        val path =
            Environment.getExternalStorageDirectory().toString() + "/test/"
        val sd = File(path)
        if (!sd.exists()) {
            sd.mkdir()
        }
        val file = File("$path$name.png")
        var fileOutputStream: FileOutputStream? = null
        try {
            // 判断SD卡是否存在，并且是否具有读写权限
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                fileOutputStream = FileOutputStream(file)
                mybitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
                fileOutputStream.flush()
                fileOutputStream.close()

                //update gallery
                val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                val uri = Uri.fromFile(file)
                intent.data = uri
                context.sendBroadcast(intent)
                //Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
                result = true
            } else {
                Toast.makeText(context, "不能读取到SD卡", Toast.LENGTH_SHORT).show()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }


//    fun getCompareFaceScore(testSignId: String, base64: String) {
//        mModel.getCompareFaceScore(sessionId, testSignId, base64)
//            .retryWhen(RetryWithDelay(2, 5))
//            .compose(RxUtils.applySchedulers(mRootView))
//            .subscribe(object :
//                ErrorHandleSubscriber<BaseJson<CompareFaceScoreBean>>(mErrorHandler) {
//                override fun onNext(t: BaseJson<CompareFaceScoreBean>) {
//                    if (Api.SUCCESS == t.code) {
//                        t.data?.let { mRootView.requestCompareFaceScoreSuccess(it) }
//                    } else {
//                        mRootView.showMessage(t.msg)
//                    }
//                }
//
//                override fun onError(t: Throwable) {
//                    super.onError(t)
//                    mRootView.showMessage(t.toString())
//                }
//
//            })
//    }


}