package com.example.girhubsearch.recyclerAdaptor

import android.app.DownloadManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.girhubsearch.CustomItems
import com.example.girhubsearch.DataRepositories
import com.example.girhubsearch.MainActivity2
import com.example.girhubsearch.R
import com.example.girhubsearch.data.CustomItemsData
import com.example.girhubsearch.viewAndRepository.Costul
import com.squareup.picasso.Picasso
import okhttp3.Cookie
import java.net.CookieManager
import java.net.URI
import java.time.Instant

class CustomRecyclerAdaptor_save(
    private val context: Context,
    private val listRepositories: MutableList<CustomItemsData>

    ) : RecyclerView.Adapter<CustomRecyclerAdaptor_save.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        //используемые компоненты
        var imageViewUsers: ImageView? = null
        var nameTextView: TextView? = null
        var nameRepTextView: TextView? = null
        var saveImageView: ImageView? = null
        var inInternetBottom: Button? = null
        var downloadBottom: Button? = null


        init {

            imageViewUsers = itemView.findViewById(R.id.imageView3Save)
            nameTextView = itemView.findViewById(R.id.textView3Save)
            nameRepTextView = itemView.findViewById(R.id.textView4Save)
            saveImageView = itemView.findViewById(R.id.imageView2Save)
            inInternetBottom = itemView.findViewById(R.id.buttonInInternetSave)
            downloadBottom = itemView.findViewById(R.id.buttonDownloadSave)



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerveiw_save, parent, false)
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

        if (listRepositories[position].flagsSave == false) {
            holder.saveImageView?.setImageResource(R.drawable.ic_baseline_bookmark_border_24)

        } else {
            holder.saveImageView?.setImageResource(R.drawable.ic_baseline_bookmark_24)
        }


        holder.saveImageView?.setOnClickListener {
            deleteItem(position, holder)
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

    private fun deleteItem(position: Int, holder: MyViewHolder) {
        Costul.updatelistRep(listRepositories[position])
        listRepositories.removeAt(position)
        // notifyDataSetChanged()
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, 1)
        holder.itemView.visibility = View.GONE

    }


}