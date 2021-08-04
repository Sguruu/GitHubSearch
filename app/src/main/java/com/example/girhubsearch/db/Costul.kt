package com.example.girhubsearch.viewAndRepository

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import com.example.girhubsearch.data.CustomItemsData

//стыдно за это временное решение :(
object Costul {
    var listRep: MutableList<CustomItemsData> = mutableListOf()
    val savelistRep: MutableList<CustomItemsData> = mutableListOf()
    private val posishion: MutableList<Postihon> = mutableListOf()


    fun addSaveListRep(data: CustomItemsData, positi: Int) {
        if (!similarityСheck(data,false)) {
            savelistRep.add(data)
            val a = Postihon(positi, data)
            posishion.add(a)
        }
    }

    fun clearSaveListRep(data: CustomItemsData) {
        savelistRep.remove(data)
        updatelistRep(data)
    }

    fun updatelistRep(data: CustomItemsData) {
        if (listRep.size != 0) {
            val index = posishion.find { it.list == data }
            listRep[index!!.positi].flagsSave = false
        }

    }

    //только для возвращения значений
    fun setPos(): MutableList<Postihon> {
        val buf = posishion
        return buf
    }

    //использовать аккуратно !
    fun onCreatedBD(listsave: MutableList<CustomItemsData>, listPos: MutableList<Postihon>) {
        savelistRep.addAll(listsave)
        posishion.addAll(listPos)
    }

    //проверка на схожеть элементов, поъожин, не похожи
    //removeMatches удалить совпадение ? true нет
    fun similarityСheck(data: CustomItemsData, removeMatches: Boolean): Boolean {
        for (i in 0 until savelistRep.size) {
            if (data.id == savelistRep[i].id) {
                if (removeMatches) {
                    clearSaveListRep(savelistRep[i])
                }
                return true
            }
        }
        return false
    }


}

data class PostList(
    var postList: List<Postihon>
)

data class Postihon(
    var positi: Int,
    var list: CustomItemsData
)