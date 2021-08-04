package com.example.girhubsearch

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.girhubsearch.data.CustomItemsData
import com.example.girhubsearch.data.ListRepositoires
import com.example.girhubsearch.fragment.ProfilFragment
import com.example.girhubsearch.fragment.SavesFragment
import com.example.girhubsearch.fragment.SearchFragment
import com.example.girhubsearch.viewAndRepository.Costul
import com.example.girhubsearch.viewAndRepository.PostList
import com.example.girhubsearch.viewAndRepository.Postihon
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class MainActivity2 : AppCompatActivity() {
    lateinit var prefs: SharedPreferences
    private val APP_PREFERENCES = "Datalist"
    private val APP_PREFERENCES_LISTSAVE = "ListSave"
    private val APP_PREFERENCES_POSITIN = "Positin"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)


        val fragmentSearch = SearchFragment()
        loadFragment(fragmentSearch)

        prefs = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        //*************
        if (prefs.contains(APP_PREFERENCES_LISTSAVE) && prefs.contains(APP_PREFERENCES_POSITIN)) {

            val gson = Gson()
            val fromJsonSaveList: ListRepositoires = gson.fromJson(
                prefs.getString(APP_PREFERENCES_LISTSAVE, ""),
                ListRepositoires::class.java
            )
            val fromJosList: PostList =
                gson.fromJson(prefs.getString(APP_PREFERENCES_POSITIN, ""), PostList::class.java)
            Log.d("Server", "toJson $fromJsonSaveList")

            val bufSaveList: MutableList<CustomItemsData> = mutableListOf()
            bufSaveList.addAll(fromJsonSaveList.items)
            val bufPosList: MutableList<Postihon> = mutableListOf()
            bufPosList.addAll(fromJosList.postList)

            Costul.onCreatedBD(bufSaveList, bufPosList)
        }
        Log.d("Server", "toJson ")
        //*************



        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.search -> {
                    val fragmentSearch = SearchFragment()
                    loadFragment(fragmentSearch)
                }
                R.id.bookmark -> {
                    val fragmentSaves = SavesFragment()
                    loadFragment(fragmentSaves)
                }
                R.id.users -> {
                    val fragmentProfil = ProfilFragment()
                    loadFragment(fragmentProfil)
                }
            }
            return@OnNavigationItemSelectedListener true
        })


    }

    // функция для работы с фрагментом
    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMainActivity2, fragment)
        fragmentTransaction.commit()
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()

        val bufListSave: ListRepositoires = ListRepositoires(Costul.savelistRep)
        val bufListPos = PostList(Costul.setPos())

        val gson = Gson()
        val jsonSaveList = gson.toJson(bufListSave)
        val jsonPosList = gson.toJson(bufListPos)

        val editor = prefs.edit()
        editor.putString(APP_PREFERENCES_LISTSAVE, jsonSaveList)
        editor.putString(APP_PREFERENCES_POSITIN, jsonPosList)
        Log.d("Server", "toJson $jsonSaveList")
        editor.commit()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onBackPressed() {
    }

}