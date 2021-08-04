package com.example.girhubsearch

import com.google.gson.annotations.SerializedName
import java.security.acl.Owner

data class DataRepositories(
    val items: List<CustomItems>
)

data class CustomItems(
    val id: String?,
    val name: String?,
    val owner: CustomOwner?,
    val html_url: String?,
    val description: String?,
    val url: String?
)

data class CustomOwner(
    val login: String?,
    val avatar_url: String?,
    val html_url: String?
)

