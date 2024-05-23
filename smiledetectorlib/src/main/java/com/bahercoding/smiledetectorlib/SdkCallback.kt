// com/bahercoding/smiledetectorlib/SdkCallback.kt
package com.bahercoding.smiledetectorlib

import java.io.Serializable

interface SdkCallback : Serializable{
    fun onSuccess(response: SuccessResponse)
    fun onFailure(response: FailedResponse)
}
