package com.bahercoding.smiledetectorlib


import android.app.Activity
import android.content.Intent

class SmileDetectorSDK {

    class Builder(private val context: Activity) {
        private var sdkCallback: SdkCallback? = null
        fun setSdkCallback(callback: SdkCallback) = apply {
            this.sdkCallback = callback
        }

        fun build() {
            callbackHolder.callback = sdkCallback

            if (sdkCallback == null) {
                throw IllegalArgumentException("SdkCallback must be set")
            }
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}

















//
//import android.app.Activity
//import android.content.Context
//import androidx.camera.view.PreviewView
//import androidx.lifecycle.LifecycleOwner
//import com.bahercoding.smiledetectorlib.camera.CameraManager
//import com.bahercoding.smiledetectorlib.graphic.GraphicOverlay
//
//class SmileDetectorSDK(
//    private val context: Activity,
//    private val previewView: PreviewView,
//    private val graphicOverlay: GraphicOverlay<*>,
//    private val lifecycleOwner: LifecycleOwner
//) {
//   private lateinit var cameraManager: CameraManager
//
//    fun startCamera() {
//        cameraManager = CameraManager(context, previewView, graphicOverlay, lifecycleOwner)
//        cameraManager.cameraStart()
//    }
//
//    fun stopCamera() {
//        cameraManager.cameraStop()
//    }
//
//    fun toggleCamera() {
//        cameraManager.changeCamera()
//    }
//}


//import android.app.Activity
//import androidx.camera.view.PreviewView
//import androidx.lifecycle.LifecycleOwner
//import com.bahercoding.smiledetectorlib.camera.CameraManager
//import com.bahercoding.smiledetectorlib.graphic.GraphicOverlay
//
//
//class SmileDetectorSDK private constructor(
//    private val activity: Activity,
//    private val previewView: PreviewView,
//    private val graphicOverlay: GraphicOverlay<*>,
//    private val lifecycleOwner: LifecycleOwner
//) {
//    private lateinit var cameraManager: CameraManager
//
//    fun startCamera() {
//        cameraManager = CameraManager(activity, previewView, graphicOverlay, lifecycleOwner)
//        cameraManager.cameraStart()
//    }
//
//    fun stopCamera() {
//        cameraManager.cameraStop()
//    }
//
//    fun toggleCamera() {
//        cameraManager.changeCamera()
//    }
//
//    class Builder {
//        private var context: Activity? = null
//        private var previewView: PreviewView? = null
//        private var graphicOverlay: GraphicOverlay<*>? = null
//        private var lifecycleOwner: LifecycleOwner? = null
//
//        fun setContext(context: Activity) = apply { this.context = context }
//        fun setPreviewView(previewView: PreviewView) = apply { this.previewView = previewView }
//        fun setGraphicOverlay(graphicOverlay: GraphicOverlay<*>) = apply { this.graphicOverlay = graphicOverlay }
//        fun setLifecycleOwner(lifecycleOwner: LifecycleOwner) = apply { this.lifecycleOwner = lifecycleOwner }
//
//        fun build(): SmileDetectorSDK {
//            return SmileDetectorSDK(
//                context ?: throw IllegalArgumentException("Context is required"),
//                previewView ?: throw IllegalArgumentException("PreviewView is required"),
//                graphicOverlay ?: throw IllegalArgumentException("GraphicOverlay is required"),
//                lifecycleOwner ?: throw IllegalArgumentException("LifecycleOwner is required")
//            )
//        }
//    }
//}

//
//import android.app.Activity
//import android.content.Context
//import android.view.View
//import androidx.camera.view.PreviewView
//import androidx.lifecycle.LifecycleOwner
//import com.bahercoding.smiledetectorlib.camera.CameraManager
//import com.bahercoding.smiledetectorlib.graphic.GraphicOverlay
//
//class SmileDetectorSDK private constructor(
//    private val context: Activity,
//    private val sdkCallback: SdkCallback,
//    private val lifecycleOwner: LifecycleOwner
//) {
//    lateinit var cameraManager: CameraManager
//    lateinit var previewView: PreviewView
//    lateinit var graphicOverlay: GraphicOverlay<*>
//
//    fun startCamera() {
//        cameraManager = CameraManager(context,previewView , graphicOverlay  , lifecycleOwner)
//        cameraManager.cameraStart()
//    }
//
//    fun stopCamera() {
//        cameraManager.cameraStop()
//    }
//
//    fun toggleCamera() {
//        cameraManager.changeCamera()
//    }
//
//    class Builder(private val context: Activity) {
//        private lateinit var previewView: PreviewView
//        private lateinit var graphicOverlay: GraphicOverlay<*>
//        private var sdkCallback: SdkCallback? = null
//
//
//        fun setSdkCallback(callback: SdkCallback) = apply {
//            this.sdkCallback = callback
//        }
//
//        fun build(): SmileDetectorSDK {
//            if (sdkCallback == null) {
//                throw IllegalArgumentException("SdkCallback must be set")
//            }
//            return SmileDetectorSDK(context, )
//        }
//    }
//}
//
