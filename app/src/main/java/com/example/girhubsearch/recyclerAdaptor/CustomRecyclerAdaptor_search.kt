package com.example.girhubsearch.recyclerAdaptor

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.girhubsearch.R
import com.example.girhubsearch.data.CustomItemsData
import com.example.girhubsearch.viewAndRepository.Costul
import com.squareup.picasso.Picasso
import okhttp3.Cookie
import java.net.CookieManager
import java.net.URI
import java.time.Instant

class CustomRecyclerAdaptor_search(
    private val context: Context,
    private val listRepositories: MutableList<CustomItemsData>
) : RecyclerView.Adapter<CustomRecyclerAdaptor_search.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        //используемые компоненты
        var imageViewUsers: ImageView? = null
        var nameTextView: TextView? = null
        var nameRepTextView: TextView? = null
        var saveImageView: ImageView? = null
        var inInternetBottom: Button? = null
        var downloadBottom: Button? = null


        init {
            imageViewUsers = itemView.findViewById(R.id.imageViewUsers)
            nameTextView = itemView.findViewById(R.id.textViewNameSearch)
            nameRepTextView = itemView.findViewById(R.id.textViewRepSearch)
            saveImageView = itemView.findViewById(R.id.imageViewSearchSave)
            inInternetBottom = itemView.findViewById(R.id.buttonInInternet)
            downloadBottom = itemView.findViewById(R.id.buttonDownload)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerveiw_search, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hintName: String = "User: ${listRepositories[position].owner?.login}"
        val hintRepo = "Repo: ${listRepositories[position].name}"
        val picass =
            Picasso.get().load(listRepositories[position].owner?.avatar_url)
                .into(holder.imageViewUsers)
        holder.nameTextView?.text = hintName
        holder.nameRepTextView?.text = hintRepo

        //проверка в сохраненных
        if (Costul.similarityСheck(listRepositories[position], false)) {
            listRepositories[position].flagsSave = true
        }

        //рисую флаг в зависимости от значений
        if (listRepositories[position].flagsSave == false) {
            holder.saveImageView?.setImageResource(R.drawable.ic_baseline_bookmark_border_24)

        } else {
            holder.saveImageView?.setImageResource(R.drawable.ic_baseline_bookmark_24)
        }


        holder.saveImageView?.setOnClickListener {
            Toast.makeText(
                context,
                "вы нажали на сохранить ${listRepositories[position].owner?.login} позиция ${position}",
                Toast.LENGTH_LONG
            ).show()
            if (listRepositories[position].flagsSave == false) {
                holder.saveImageView?.setImageResource(R.drawable.ic_baseline_bookmark_24)
                listRepositories[position].flagsSave = true
                Costul.addSaveListRep(listRepositories[position], position)
            } else {
                ///
                holder.saveImageView?.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
                listRepositories[position].flagsSave = false
                //проверка в сохраненных
                if (Costul.similarityСheck(listRepositories[position], true)) {
                } else {
                    Costul.clearSaveListRep(listRepositories[position])
                }

            }
        }

        holder.inInternetBottom?.setOnClickListener {
            //открытие браузера
            val uri: String? = listRepositories[position].html_url
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(uri))
            val chooser = Intent.createChooser(intent, "браузер")
            context.startActivity(chooser)

        }
        holder.downloadBottom?.setOnClickListener {
            val fragment: String = "/archive/refs/heads/master.zip"
            val uri: String = listRepositories[position].html_url + fragment
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(uri))
            val chooser = Intent.createChooser(intent, "браузер")
            context.startActivity(chooser)
        }

    }

    override fun getItemCount(): Int {
        return listRepositories.size
    }

    // получение позиции нажатия
    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


}