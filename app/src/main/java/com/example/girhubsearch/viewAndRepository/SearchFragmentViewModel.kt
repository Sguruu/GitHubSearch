package com.example.girhubsearch.viewAndRepository

import androidx.lifecycle.ViewModel
import com.example.girhubsearch.CustomItems
import com.example.girhubsearch.data.CustomItemsData

class SearchFragmentViewModel : ViewModel() {
    private val searchRepository = SearchFragmentRepository()

    // получаю из вне
    var listRespons: List<CustomItems> = listOf()
        private set

    //получаю внутри
    var listResponsData: MutableList<CustomItemsData> = mutableListOf()
        private set

    // поисковый запрос пользователя
    lateinit var text: String
        private set


    fun createListD() {
        listResponsData = searchRepository.addFlags(listRespons)
    }

    fun addList() {
        listResponsData = searchRepository.addMutableList(listResponsData, listRespons)
    }

    fun update(t: String, listRespons: List<CustomItems>?) {
        text = t
        if (listRespons == null) {
        } else {
            this.listRespons = listRespons
        }

    }

    fun updateListResponsData(data: MutableList<CustomItemsData>?) {
        if (data == null) {
        } else {
            this.listResponsData = data
        }

    }
}