package com.example.milolibrairy.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.databinding.FragmentSearchListBinding
import com.example.milolibrairy.models.book

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [search_list.newInstance] factory method to
 * create an instance of this fragment.
 */
class search_list : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view= inflater.inflate(R.layout.fragment_search_list, container, false)
        val binding=FragmentSearchListBinding.bind(view)
        val tab= ManageBook(requireContext()).listbooks()


        var adapter= RvSearchAdapter(tab,requireContext(),activity as IndexActivity)
        binding.rvsearch.layoutManager= LinearLayoutManager(this.activity)
        binding.rvsearch.adapter=adapter

        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return  false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                val filtred_list=ArrayList<book>()
                for(b in tab){
                    if(p0?.lowercase()?.let { b.title.lowercase().contains(it) } == true){
                        filtred_list.add(b)
                    }
                }
                adapter= RvSearchAdapter(filtred_list,requireContext(),activity as IndexActivity)
                binding.rvsearch.adapter=adapter

                return true

            }

        })
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment search_list.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            search_list().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}