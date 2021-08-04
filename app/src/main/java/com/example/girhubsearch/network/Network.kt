package com.example.girhubsearch.network

import android.util.Log
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

object Network {
    val client = OkHttpClient()
    fun getSearchRepositoriesCall(text: String, page: String): Call {
        //https://api.github.com/search/repositories?page=2&q=tets
        val host: String = "api.github.com"
        val url: HttpUrl = HttpUrl.Builder()
            .scheme("https")
            .host(host)
            .addEncodedPathSegment("search")
            .addEncodedPathSegment("repositories")
            .addQueryParameter("page", page)
            .addQueryParameter("q", text)
            .build()
        Log.d("Server", "url = $url")
        val request: Request = Request.Builder()
            .get()
            .url(url)
            .build()
        return client.newCall(request)
    }
}