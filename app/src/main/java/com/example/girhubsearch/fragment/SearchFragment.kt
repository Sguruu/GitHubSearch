package com.example.girhubsearch.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.girhubsearch.CustomHandler
import com.example.girhubsearch.CustomItems
import com.example.girhubsearch.DataRepositories
import com.example.girhubsearch.R
import com.example.girhubsearch.network.CustomResponse
import com.example.girhubsearch.recyclerAdaptor.CustomRecyclerAdaptor_search
import com.example.girhubsearch.viewAndRepository.Costul
import com.example.girhubsearch.viewAndRepository.SearchFragmentViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val searchViewModel: SearchFragmentViewModel by viewModels()

    private var searchButtom: Button? = null
    private var saveImageView: ImageView? = null
    private var textsearchEditText: EditText? = null

    private val customHandler = CustomHandler()
    private val customResponse = CustomResponse()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Fragment0", "SearchFragment onAttach")

    }

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
        Log.d("Fragment", "SearchFragment onCreateView")
        val imageViewSearchSave = view?.findViewById<ImageView>(R.id.imageViewSearchSave)

        imageViewSearchSave?.setColorFilter(Color.parseColor(R.color.blackBack.toString()))
        // тут писать код

        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchButtom = view?.findViewById(R.id.buttonFragmentSearchSearch)
        textsearchEditText = view?.findViewById(R.id.editTextTextPersonName2)
        customHandler.initHandler()

        searchViewModel.updateListResponsData(Costul.listRep)


        //рекулер view
        val recyclerView: RecyclerView? = view?.findViewById(R.id.recyclerViewSearch)
        recyclerView?.layoutManager = LinearLayoutManager(this.activity)
        recyclerView?.adapter = CustomRecyclerAdaptor_search(this.requireActivity(), searchViewModel.listResponsData)



        searchButtom?.setOnClickListener {
            var text: String = ""
            if (textsearchEditText != null || textsearchEditText?.text.toString() != "") {
                text = textsearchEditText!!.text.toString()
                Log.d("Server", "lalalaalalal  ${text} | ${textsearchEditText?.text.toString()}  ")

                customHandler.handler.post {
                    var resultRespons =
                        customResponse.responseRepositories(text, "1")

                    customHandler.mainHandler.post {
                        searchViewModel.update(text, resultRespons)
                        searchViewModel.createListD()
                        searchViewModel.addList()
                        recyclerView?.adapter =
                            CustomRecyclerAdaptor_search(
                                this.requireActivity(),
                                searchViewModel.listResponsData
                            )
                        Log.d(
                            "handler",
                            "mainHandler SearchFragment searchViewModel.listResponsData = ${
                                searchViewModel.listResponsData
                            } "
                        )
                    }

                }
            } else {

            }

        }

    }

    override fun onResume() {
        super.onResume()


    }

    override fun onDetach() {
        super.onDetach()
        customHandler.quit(false)
        Costul.listRep = searchViewModel.listResponsData
        Log.d("Fragment", "SearchFragment onDetach")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}