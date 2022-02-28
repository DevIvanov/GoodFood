package com.juniorteam.goodfood.di

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class OkHttp {
    private val tag = OkHttp::class.java.simpleName
    private val client = OkHttpClient()

    fun run() {
        val request = Request.Builder()
            .url("https://api.spoonacular.com/recipes/22347/information?Content-Type=application/json&apiKey=88754a02515e4ea88c6fcb7d51e78504")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            Log.e(tag, "Quota-Request: ${response.header("X-API-Quota-Request")}")
            Log.e(tag, "Quota-Used: ${response.header("X-API-Quota-Used")}")
            Log.e(tag, "Quota-Left: ${response.header("X-API-Quota-Left")}")
        }
    }
}