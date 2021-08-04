package com.example.girhubsearch.network

import android.util.Log
import com.example.girhubsearch.CustomItems
import com.example.girhubsearch.CustomOwner
import com.example.girhubsearch.DataRepositories
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.security.acl.Owner

class CustomResponse {
    fun responseRepositories(text: String, page: String): List<CustomItems> {
        try {
            val response = Network.getSearchRepositoriesCall(text, page).execute()
            val responsString = response.body?.string().orEmpty()
            Log.d("Server", "response body = $responsString")
            Log.d("Server", "response successful = ${response.isSuccessful}")
            val repositories: List<CustomItems> = parseRepositoriesResponse(responsString)
            return repositories
        } catch (e: IOException) {
            Log.e("Server", "execute request error = ${e.message}", e)
            return emptyList()
        }
    }

    private fun parseRepositoriesResponse(responseBodyString: String): List<CustomItems> {
        try {
            val gson = Gson()
            val dataRepositories: DataRepositories =
                gson.fromJson(responseBodyString, DataRepositories::class.java)
            val listResponse : List<CustomItems> = dataRepositories.items
            Log.d("ServerParse", "parse response successful = ${listResponse}")
            return listResponse
        } catch (e: JSONException) {
            Log.d("ServerParse", "parse response erorr = ${e.message}")
            return emptyList()
        }
    }
}