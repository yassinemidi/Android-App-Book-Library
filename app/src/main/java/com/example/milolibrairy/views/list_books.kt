package com.example.milolibrairy.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.databinding.FragmentListBooksBinding
import com.example.milolibrairy.models.book

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [list_books.newInstance] factory method to
 * create an instance of this fragment.
 */
class list_books : Fragment() {
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
        val view= inflater.inflate(R.layout.fragment_list_books, container, false)
        val binding=FragmentListBooksBinding.bind(view)
        val tab=ManageBook(requireContext()).listbooks()



        var adapter= RvListbookAdapter(tab,requireContext(),activity as IndexActivity)
        binding.rvhome1.layoutManager= LinearLayoutManager(this.activity)
        binding.rvhome1.adapter=adapter


        binding.spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {


                if(p0?.getItemAtPosition(p2).toString().lowercase().trim()=="all"){
                    adapter= RvListbookAdapter(tab,requireContext(),activity as IndexActivity)
                    binding.rvhome1.adapter=adapter
                }else{
                    val filtred_list=ArrayList<book>()
                    for(b in tab){
                        if(b.category.lowercase().trim()==p0?.getItemAtPosition(p2).toString().lowercase().trim()){
                            filtred_list.add(b)
                        }
                    }
                    adapter= RvListbookAdapter(filtred_list,requireContext(),activity as IndexActivity)
                    binding.rvhome1.adapter=adapter
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment list_books.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            list_books().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}