package com.bahercoding.smiledetector

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bahercoding.smiledetectorlib.SmileDetectorSDK
import com.bahercoding.smiledetectorlib.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var smileDetectorSDK: SmileDetectorSDK
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        smileDetectorSDK = SmileDetectorSDK(
            this,
            binding.viewCameraPreview,
            binding.viewGraphicOverlay,
            this
        )


    }
}
