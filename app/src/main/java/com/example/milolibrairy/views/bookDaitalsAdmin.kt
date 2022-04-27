package com.example.milolibrairy.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.databinding.FragmentBookDaitalsAdminBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [bookDaitalsAdmin.newInstance] factory method to
 * create an instance of this fragment.
 */
class bookDaitalsAdmin : Fragment() {
    val args:bookDaitalsAdminArgs by navArgs()
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
        val view= inflater.inflate(R.layout.fragment_book_daitals_admin, container, false)
        val binding= FragmentBookDaitalsAdminBinding.bind(view)

        val bookId=args.bookId

        val book=ManageBook(requireContext()).findbook(bookId)
         if (book != null) {
             binding.imageView.setImageBitmap(ManageBook(requireContext()).getImage(book.image))
             binding.bookTitle.text=book.title
             binding.bookAuthor.text=book.author
             binding.bookCayegory.text="Category: "+book.category
             binding.bookDescription.text=book.description
             binding.button.setOnClickListener {
                 val action=bookDaitalsAdminDirections.actionBookDaitalsAdminToFeedbackView(book.id)
                 findNavController().navigate(action)

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
         * @return A new instance of fragment bookDaitalsAdmin.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            bookDaitalsAdmin().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}