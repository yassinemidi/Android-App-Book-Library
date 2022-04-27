package com.example.milolibrairy.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.controller.ManageReservation
import com.example.milolibrairy.databinding.FragmentBookDetailsBinding
import com.example.milolibrairy.models.reservation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Book_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class Book_details : Fragment() {
    val args: Book_detailsArgs by navArgs()
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
        val view= inflater.inflate(R.layout.fragment_book_details, container, false)

        val binding=FragmentBookDetailsBinding.bind(view)
        val activity=activity as IndexActivity
        val book_id=args.bookId
        val book=ManageBook(requireContext()).findbook(book_id)
        if (book != null) {
            binding.imageView.setImageBitmap(ManageBook(requireContext()).getImage(book.image))
            binding.bookTitle.text=book.title
            binding.bookAuthor.text=book.author
            binding.bookCayegory.text="Category: "+book.category
            binding.bookDescription.text=book.description
            binding.button.setOnClickListener {
                val myres=ManageReservation(requireContext()).userReservation(activity.b.getInt("id_user"))
                var test=true
                for(r:reservation in myres){
                    if(r.id_book==book_id){
                        test=false
                    }
                }

                if(test){
                    ManageReservation(requireContext()).addReservation(reservation(0,book.id,activity.b.getInt("id_user")))
                    Toast.makeText(context, book.title+" added to your librairy", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, book.title+" Is already in your librairy", Toast.LENGTH_SHORT).show()

                }

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
         * @return A new instance of fragment Book_details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Book_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}