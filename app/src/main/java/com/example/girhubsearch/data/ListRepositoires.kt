package com.example.girhubsearch.data

import com.example.girhubsearch.CustomOwner

//Класс отличается от класса DataRepositories полями
//Новые поля будут помечены комментарием
data class ListRepositoires(
    val items: List<CustomItemsData>
)

data class CustomItemsData(
    val id: String?,
    val name: String?,
    val owner: CustomOwner?,
    val html_url: String?,
    val description: String?,
    val url: String?,
    //Флаг который отвечает true - добавлен в загладки, false - нет
    var flagsSave: Boolean = false
)

