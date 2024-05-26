package com.bahercoding.smiledetectorlib.camera

import android.annotation.SuppressLint
import android.graphics.Rect
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.bahercoding.smiledetectorlib.graphic.GraphicOverlay

abstract class BaseCameraAnalyzer<T : Any> : ImageAnalysis.Analyzer {

    abstract val graphicOverlay: GraphicOverlay<*>

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        mediaImage?.let { image ->
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            detectInImage(InputImage.fromMediaImage(image, rotationDegrees))
                .addOnSuccessListener { results ->
                    onSuccess(results, graphicOverlay, image.cropRect, imageProxy, rotationDegrees)
                    imageProxy.close()
                }
                .addOnFailureListener {
                    onFailure(it)
                    imageProxy.close()
                }
        }
    }

    protected abstract fun detectInImage(image: InputImage): Task<T>

    abstract fun stop()

    protected abstract fun onSuccess(
        results: T,
        graphicOverlay: GraphicOverlay<*>,
        rect: Rect,
        imageProxy: ImageProxy,
        rotationDegrees: Int
    )

    protected abstract fun onFailure(e: Exception)
}
