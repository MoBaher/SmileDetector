package com.bahercoding.smiledetectorlib


import android.app.Activity
import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.bahercoding.smiledetectorlib.camera.CameraManager
import com.bahercoding.smiledetectorlib.graphic.GraphicOverlay

class SmileDetectorSDK(
    private val context: Activity,
    private val previewView: PreviewView,
    private val graphicOverlay: GraphicOverlay<*>,
    private val lifecycleOwner: LifecycleOwner
) {
    private lateinit var cameraManager: CameraManager

    fun startCamera() {
        cameraManager = CameraManager(context, previewView, graphicOverlay, lifecycleOwner)
        cameraManager.cameraStart()
    }

    fun stopCamera() {
        cameraManager.cameraStop()
    }

    fun toggleCamera() {
        cameraManager.changeCamera()
    }
}