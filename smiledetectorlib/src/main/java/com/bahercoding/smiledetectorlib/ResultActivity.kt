package com.bahercoding.smiledetectorlib

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Boolean.TRUE


class ResultActivity : AppCompatActivity() {

    private var sdkCallback: SdkCallback? = callbackHolder.callback
    private var responseListener: SdkResponse.ResponseListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        val imageView: ImageView = findViewById(R.id.imageView)
        val returnButton : Button = findViewById(R.id.returnButton)
        val cancelButton : Button = findViewById(R.id.cancelButton)



        val byteArray = intent.getByteArrayExtra("captured_bitmap")
        if (byteArray != null) {
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            imageView.setImageBitmap(bitmap)
        }
        cancelButton.setOnClickListener{
            sdkCallback?.onFailure(FailedResponse( error = "Canceled"))
            finish()
        }
        returnButton.setOnClickListener {
            if (byteArray != null) {
                sdkCallback?.onSuccess(SuccessResponse(result = byteArray))
            }
            finish()
        }
    }

    fun setSdkCallback(callback: SdkCallback) {
        sdkCallback = callback
    }
    fun setResponseListener(listener: SdkResponse.ResponseListener) {
        responseListener = listener
    }



}