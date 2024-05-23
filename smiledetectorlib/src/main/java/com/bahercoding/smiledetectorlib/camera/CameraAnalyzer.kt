package com.bahercoding.smiledetectorlib.camera

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.YuvImage
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageProxy
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.FileProvider
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.bahercoding.smiledetectorlib.ResultActivity
import com.bahercoding.smiledetectorlib.graphic.GraphicOverlay
import com.bahercoding.smiledetectorlib.graphic.RectangleOverlay
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class CameraAnalyzer(
    private val context: Activity,
    private val overlay: GraphicOverlay<*>
) : BaseCameraAnalyzer<List<Face>>(){

    override val graphicOverlay: GraphicOverlay<*>
        get() = overlay

    private val cameraOptions = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
        .setMinFaceSize(0.15f)
        .enableTracking()
        .build()

    private val detector = FaceDetection.getClient(cameraOptions)
    private var isImageCaptured = false

    override fun detectInImage(image: InputImage): Task<List<Face>> {
        return detector.process(image)
    }

    override fun stop() {
        try {
            detector.close()
        } catch (e : Exception) {
            Log.e(TAG , "stop : $e")
        }
    }

    override fun onSuccess(results: List<Face>, graphicOverlay: GraphicOverlay<*>, rect: Rect, imageProxy:ImageProxy) {
        graphicOverlay.clear()
        results.forEach {
            if (!isImageCaptured && it.smilingProbability!! >0.8){
                val faceGraphic = RectangleOverlay(graphicOverlay, it, rect)
                graphicOverlay.add(faceGraphic)
                Toast.makeText(context, "Smile Success", Toast.LENGTH_SHORT).show()
                captureAndSendBitmap(imageProxy)
                context.finish()
                isImageCaptured = true
            }
        }
        graphicOverlay.postInvalidate()
    }

    override fun onFailure(e: Exception) {
        Log.e(TAG, "onFailure : $e")
    }

    companion object {
        private const val TAG = "CameraAnalyzer"
    }

    private fun captureAndSendBitmap(imageProxy: ImageProxy) {
        val bitmap = imageProxy.toBitmap()
        bitmap?.let {
            val intent = Intent(context, ResultActivity::class.java).apply {
                putExtra("captured_bitmap", bitmapToByteArray(it))
            }
            context.startActivity(intent)
            imageProxy.close()
        }
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }
    @SuppressLint("UnsafeOptInUsageError")
    fun ImageProxy.toBitmap(): Bitmap? {
        val image = this.image ?: return null
        val yBuffer = image.planes[0].buffer
        val uBuffer = image.planes[1].buffer
        val vBuffer = image.planes[2].buffer

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)

        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        val yuvImage = YuvImage(nv21, ImageFormat.NV21, image.width, image.height, null)
        val out = ByteArrayOutputStream()
        yuvImage.compressToJpeg(Rect(0, 0, image.width, image.height), 100, out)
        val imageBytes = out.toByteArray()
        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        return rotateBitmap(bitmap, 270)
    }
    private fun rotateBitmap(bitmap: Bitmap, rotationDegrees: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(rotationDegrees.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }


}
