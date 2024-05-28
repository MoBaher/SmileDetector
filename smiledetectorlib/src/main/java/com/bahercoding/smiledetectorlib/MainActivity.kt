
package com.bahercoding.smiledetectorlib

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bahercoding.smiledetectorlib.camera.CameraManager
import com.bahercoding.smiledetectorlib.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var cameraManager: CameraManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        var viewCameraPreview:PreviewView = binding.viewCameraPreview
//        var

        askCameraPermission()
    }

    private fun askCameraPermission() {
        if (arrayOf(android.Manifest.permission.CAMERA).all {
                ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
            }) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 0)
        }
    }

    private fun startCamera() {
        if (binding.viewCameraPreview!=null && binding.viewGraphicOverlay!=null){
            cameraManager = CameraManager(this, binding.viewCameraPreview, binding.viewGraphicOverlay, this)
            cameraManager.cameraStart()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            Toast.makeText(this, "Camera Permission Denied!", Toast.LENGTH_SHORT).show()
        }
    }
}





























//package com.bahercoding.smiledetectorlib
//
//import android.content.pm.PackageManager
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.bahercoding.smiledetectorlib.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var smileDetectorSDK: SmileDetectorSDK
//    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//        smileDetectorSDK.setPreviewVieww(binding.viewCameraPreview)
//        smileDetectorSDK.setgraphicOverlay(binding.viewGraphicOverlay)
//
//
//
//        askCameraPermission()
//    }
//
//    private fun askCameraPermission() {
//        if (arrayOf(android.Manifest.permission.CAMERA).all {
//                ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
//            }) {
//            smileDetectorSDK.startCamera()
//        } else {
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 0)
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 0 && ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            smileDetectorSDK.startCamera()
//        } else {
//            Toast.makeText(this, "Camera Permission Denied!", Toast.LENGTH_SHORT).show()
//        }
//    }
//}