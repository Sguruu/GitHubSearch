package com.example.girhubsearch.viewAndRepository

import com.example.girhubsearch.CustomItems
import com.example.girhubsearch.data.CustomItemsData
import com.example.girhubsearch.data.ListRepositoires
import com.example.girhubsearch.network.CustomResponse

class SearchFragmentRepository {



    // создание новых данных с флагом
     fun addFlags(responsData: List<CustomItems>): MutableList<CustomItemsData> {
        val dataMutList: MutableList<CustomItemsData> = mutableListOf()
        val size = responsData.size
        //until  не включая
        for (i in 0 until size) {
            val customItemsData = CustomItemsData(
                responsData[i].id,
                responsData[i].name,
                responsData[i].owner,
                responsData[i].html_url,
                responsData[i].description,
                responsData[i].url,
                false
            )
            dataMutList.add(i, customItemsData)
        }
        return dataMutList
    }

    // добавление новых данных уже к финальным данным
    fun addMutableList(
        mutList: MutableList<CustomItemsData>,
        responsData: List<CustomItems>
    ): MutableList<CustomItemsData> {
        mutList.addAll(addFlags(responsData))
        return mutList
    }
}
