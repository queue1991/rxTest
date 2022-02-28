package com.hsj.rxtest.retrofit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.text.TextUtils
import io.reactivex.rxjava3.core.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*
import java.io.File
import java.io.FileOutputStream
import java.util.*


object DeliveryApi : BaseProxy() {
    private val provider = retrofit(howserUrl("api/address/")).create(Method::class.java)

    /**
     * rx 호출 liftItems
     */
    fun liftItems() : Single<Boolean>{
        return provider.liftItems()
    }


    interface Method {
        @GET("driver-barcode-goods")
        fun liftItems(): Single<Boolean>
    }
}