package com.karandev.util.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.putQueue(responseCallback: (Call<T>, Response<T>) -> Unit, failCallback: (Call<T>, Throwable) -> Unit)
{
    this.enqueue(object: Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) = responseCallback(call, response)
        override fun onFailure(call: Call<T>, ex: Throwable) = failCallback(call, ex)
    })
}

