
package com.bahercoding.smiledetector

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bahercoding.smiledetectorlib.*
import java.lang.Boolean.FALSE

class StartActivity : AppCompatActivity(), SdkCallback {

    private lateinit var startSdkButton: Button
    private lateinit var imageView: ImageView
    private lateinit var canceled : TextView
    private lateinit var smileDetectorSDK: SmileDetectorSDK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)

        startSdkButton = findViewById(R.id.startSdkButton)
        imageView = findViewById(R.id.imageView)
        canceled = findViewById(R.id.canceled)



        startSdkButton.setOnClickListener {
            SmileDetectorSDK.Builder(this)
                .setSdkCallback(this)
                .build()
        }
    }

    private fun startSmileDetectorSDK() {
      //  SmileDetectorSDKHolder.sdk = smileDetectorSDK
        val intent = Intent(this, com.bahercoding.smiledetectorlib.MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSuccess(response: SuccessResponse) {
        val bitmap = BitmapFactory.decodeByteArray(response.result, 0, response.result.size)
        runOnUiThread {
            Toast.makeText(this, "Success",Toast.LENGTH_SHORT).show()

            imageView.setImageBitmap(bitmap)
           if (!imageView.isVisible){
               imageView.visibility= View.VISIBLE

           }
            if (canceled.isVisible){
                canceled.visibility= View.INVISIBLE

            }
        }
    }

    override fun onFailure(response: FailedResponse) {
        runOnUiThread {
            imageView.visibility= View.INVISIBLE
            canceled.visibility=View.VISIBLE

            Toast.makeText(this, "Canceled",Toast.LENGTH_SHORT).show()
        }
    }
}




























////package com.bahercoding.smiledetector
////
////import android.content.Intent
////import android.graphics.BitmapFactory
////import android.os.Bundle
////import android.widget.Button
////import android.widget.ImageView
////import android.widget.Toast
////import androidx.appcompat.app.AppCompatActivity
////import com.bahercoding.smiledetectorlib.FailedResponse
////import com.bahercoding.smiledetectorlib.ResultActivity
////import com.bahercoding.smiledetectorlib.SdkCallback
////import com.bahercoding.smiledetectorlib.SdkResponse
////import com.bahercoding.smiledetectorlib.SuccessResponse
////
//////import com.bahercoding.smiledetector.databinding.ActivityStartBinding
////
////class StartActivity : AppCompatActivity() ,SdkCallback {
////    override val response: Any
////        get() = TODO("Not yet implemented")
////
////   // private val binding by lazy {ActivityStartBinding.inflate(layoutInflater)}
////    private val REQUEST_CODE_START_SDK = 1
////    private lateinit var startSdkButton:Button
////    private lateinit var imageView: ImageView
////
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContentView(R.layout.start_activity)
////        startSdkButton=findViewById(R.id.startSdkButton)
////        imageView=findViewById(R.id.imageView)
////
////        startSdkButton.setOnClickListener {
////            startSmileDetectorSDK()
////        }
////
////        response.setResponseListener(object : SdkResponse.ResponseListener {
////            override fun onSucess(response: SuccessResponse) {
////
////            }
////
////            override fun onFailure(response: FailedResponse) {
////
////
////
////            }
////
////
////        })
////    }
////
////    private fun startSmileDetectorSDK() {
////        val intent = Intent(this, com.bahercoding.smiledetectorlib.MainActivity::class.java)
////        startActivityForResult(intent, REQUEST_CODE_START_SDK)
////    }
////
////
////    override fun onResume() {
////        super.onResume()
////        val resultActivity = ResultActivity()
////        resultActivity.setSdkCallback(this)
////    }
////
////
////
////
////}
////
////
////
//////    override fun onSuccess(response: SuccessResponse) {
//////        val bitmap = BitmapFactory.decodeByteArray(response.result, 0, response.result.size)
//////        imageView.setImageBitmap(bitmap)
//////    }
//////
//////    override fun onFailure(response: FailedResponse) {
//////        //handle
//////        Toast.makeText(this,"Canceled",Toast.LENGTH_SHORT).show()
//////    }
//////    companion object {
//////        private const val REQUEST_CODE_START_SDK = 1
//////    }
//
//
//
//// com/bahercoding/smiledetector/StartActivity.kt
//package com.bahercoding.smiledetector
//
//import android.content.Intent
//import android.graphics.BitmapFactory
//import android.os.Bundle
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.bahercoding.smiledetectorlib.FailedResponse
//import com.bahercoding.smiledetectorlib.MainActivity
//import com.bahercoding.smiledetectorlib.ResultActivity
//import com.bahercoding.smiledetectorlib.SdkCallback
//import com.bahercoding.smiledetectorlib.SmileDetectorSDK
//import com.bahercoding.smiledetectorlib.SuccessResponse
//
//class StartActivity : AppCompatActivity(), SdkCallback {
//
//    private lateinit var startSdkButton: Button
//    private lateinit var imageView: ImageView
//    private lateinit var smileDetectorSDK: SmileDetectorSDK
//    private val REQUEST_CODE_START_SDK = 1
//    private var sdkCallback: SdkCallback? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.start_activity)
//
//        startSdkButton = findViewById(R.id.startSdkButton)
//        imageView = findViewById(R.id.imageView)
//
//        startSdkButton.setOnClickListener {
//            startSmileDetectorSDK()
//        }
//    }
//
//    private fun startSmileDetectorSDK() {
//       smileDetectorSDK = SmileDetectorSDK.Builder(this)
//            .setSdkCallback(this)
//            .build()
//        val intent = Intent(this, com.bahercoding.smiledetectorlib.MainActivity::class.java)
//        startActivityForResult(intent, REQUEST_CODE_START_SDK)
////        val intent = Intent(this, MainActivity::class.java)
////        ResultActivity().setSdkCallback(this)
////        startActivity(intent)
//
//    }
//    fun setSdkCallback(callback: SdkCallback) {
//        sdkCallback = callback
//    }
//
//    override fun onSuccess(response: SuccessResponse) {
//        val bitmap = BitmapFactory.decodeByteArray(response.result, 0, response.result.size)
//        runOnUiThread {
//            imageView.setImageBitmap(bitmap)
//        }
//    }
//
//    override fun onFailure(response: FailedResponse) {
//        runOnUiThread {
//            Toast.makeText(this, "Failed: ${response.error}", Toast.LENGTH_SHORT).show()
//        }
//    }
//}