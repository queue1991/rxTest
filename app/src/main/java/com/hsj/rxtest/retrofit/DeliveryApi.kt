package com.hsj.rxtest.retrofit

import com.hsj.rxtest.retrofit.base.BaseProxy
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*


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