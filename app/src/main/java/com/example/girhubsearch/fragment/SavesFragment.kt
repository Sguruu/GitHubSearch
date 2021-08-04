package com.example.girhubsearch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.girhubsearch.CustomHandler
import com.example.girhubsearch.R
import com.example.girhubsearch.recyclerAdaptor.CustomRecyclerAdaptor_save
import com.example.girhubsearch.recyclerAdaptor.CustomRecyclerAdaptor_search
import com.example.girhubsearch.viewAndRepository.Costul
import com.example.girhubsearch.viewAndRepository.SavesFrgmentViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SavesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var imageViewSave: ImageView? = null
    private var textViewTitle: TextView? = null

    private val customHandler = CustomHandler()

    private val savesFragmentViewModel: SavesFrgmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // тут писать код
        return inflater.inflate(R.layout.fragment_saves, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SavesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SavesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageViewSave = view?.findViewById(R.id.imageView2Save)
        textViewTitle = view?.findViewById(R.id.textViewFSavesTitle)

        customHandler.initHandler()


        val recyclerView: RecyclerView? = view?.findViewById(R.id.recyclerViewFSaves)
        recyclerView?.layoutManager = LinearLayoutManager(this.activity)
        recyclerView?.adapter =
        CustomRecyclerAdaptor_save(this.requireActivity(), Costul.savelistRep)

        imageViewSave?.setOnClickListener {
            val index =2
        }
        //рекулер view


    }

    override fun onDetach() {
        customHandler.quit(true)
        super.onDetach()
    }
}