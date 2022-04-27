package com.example.milolibrairy.views

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.controller.fVefify
import com.example.milolibrairy.databinding.FragmentAddBookAdminBinding
import com.example.milolibrairy.models.book

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addBookAdmin.newInstance] factory method to
 * create an instance of this fragment.
 */
class addBookAdmin : Fragment() {

    val SELECT_PHOTO=2222
    lateinit var bitmap:Bitmap
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
        val view = inflater.inflate(R.layout.fragment_add_book_admin, container, false)
        val binding=FragmentAddBookAdminBinding.bind(view)
        binding.btnchoos.setOnClickListener {
            val photoPicker= Intent(Intent.ACTION_PICK)
            photoPicker.type="image/*"

            startActivityForResult(photoPicker,SELECT_PHOTO)
        }

        var cat=""


        binding.spinner2.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {


                cat=p0?.getItemAtPosition(p2).toString()


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.btnAdd.setOnClickListener {

            if(this::bitmap.isInitialized){
                if (!fVefify().verifyLink(binding.linkBook.text.toString())){
                    Toast.makeText(requireContext(), "Please write a valid link!", Toast.LENGTH_SHORT).show()

                }else if(binding.titleBook.text.toString().isEmpty() || binding.authorBook.text.toString().isEmpty() || binding.descriptionBook.text.toString().isEmpty()){
                    Toast.makeText(requireContext(), "Please Fill all the gaps!", Toast.LENGTH_SHORT).show()

                }else{
                    ManageBook(requireContext()).addBook(book(0,
                        binding.titleBook.text.toString(),
                        binding.authorBook.text.toString(),
                        binding.descriptionBook.text.toString(),
                        cat,
                        ManageBook(requireContext()).getBytes(bitmap),
                        binding.linkBook.text.toString()))
                    Toast.makeText(requireContext(), "Book added successfully", Toast.LENGTH_SHORT).show()
                }


            }else{
                Toast.makeText(requireContext(), "Please select a picture!", Toast.LENGTH_SHORT).show()
            }


        }


        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==SELECT_PHOTO && resultCode== Activity.RESULT_OK && data!=null){



            val imageUri: Uri? = data.data
            val ac=activity as AdminActivity
            bitmap = MediaStore.Images.Media.getBitmap(ac.contentResolver, imageUri)



        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment addBookAdmin.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            addBookAdmin().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}