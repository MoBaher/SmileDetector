// com/bahercoding/smiledetectorlib/SdkResponse.kt
package com.bahercoding.smiledetectorlib

interface SdkResponse {
    interface ResponseListener {
        fun onSuccess(response: SuccessResponse)
        fun onFailure(response: FailedResponse)
    }
}

data class SuccessResponse(val result: ByteArray) : SdkResponse

data class FailedResponse(val error: String) : SdkResponse
