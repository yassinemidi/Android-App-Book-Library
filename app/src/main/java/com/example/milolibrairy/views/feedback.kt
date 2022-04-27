package com.example.milolibrairy.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.controller.ManageFeedback
import com.example.milolibrairy.databinding.FragmentFeedbackBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [feedback.newInstance] factory method to
 * create an instance of this fragment.
 */
class feedback : Fragment() {
    val args:feedbackArgs by navArgs()
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
        val view= inflater.inflate(R.layout.fragment_feedback, container, false)
        val binding=FragmentFeedbackBinding.bind(view)
        val book=ManageBook(requireContext()).findbook(args.bookId)
        val ac=activity as IndexActivity
        var note=0
        if(book!=null){
            val oldfd=ManageFeedback(requireContext()).findFeedbackUser(book.id,ac.b.getInt("id_user"))
            if(oldfd!=null){
                binding.comment.setText(oldfd.comment)
            }

            binding.title.setText(book.title)
            binding.author.setText(book.author)

            binding.imageView1.setOnClickListener {
                binding.imageView1.setImageResource(R.drawable.star_white)
                binding.imageView2.setImageResource(R.drawable.star_white)
                binding.imageView3.setImageResource(R.drawable.star_white)
                binding.imageView4.setImageResource(R.drawable.star_white)
                binding.imageView5.setImageResource(R.drawable.star_white)


                binding.imageView1.setImageResource(R.drawable.start_yellow)
                note=1



            }
            binding.imageView2.setOnClickListener {
                binding.imageView1.setImageResource(R.drawable.star_white)
                binding.imageView2.setImageResource(R.drawable.star_white)
                binding.imageView3.setImageResource(R.drawable.star_white)
                binding.imageView4.setImageResource(R.drawable.star_white)
                binding.imageView5.setImageResource(R.drawable.star_white)

                binding.imageView1.setImageResource(R.drawable.start_yellow)
                binding.imageView2.setImageResource(R.drawable.start_yellow)

                note=2
            }
            binding.imageView3.setOnClickListener {
                binding.imageView1.setImageResource(R.drawable.star_white)
                binding.imageView2.setImageResource(R.drawable.star_white)
                binding.imageView3.setImageResource(R.drawable.star_white)
                binding.imageView4.setImageResource(R.drawable.star_white)
                binding.imageView5.setImageResource(R.drawable.star_white)

                binding.imageView1.setImageResource(R.drawable.start_yellow)
                binding.imageView2.setImageResource(R.drawable.start_yellow)
                binding.imageView3.setImageResource(R.drawable.start_yellow)

                note=3
            }
            binding.imageView4.setOnClickListener {
                binding.imageView1.setImageResource(R.drawable.star_white)
                binding.imageView2.setImageResource(R.drawable.star_white)
                binding.imageView3.setImageResource(R.drawable.star_white)
                binding.imageView4.setImageResource(R.drawable.star_white)
                binding.imageView5.setImageResource(R.drawable.star_white)


                binding.imageView1.setImageResource(R.drawable.start_yellow)
                binding.imageView2.setImageResource(R.drawable.start_yellow)
                binding.imageView3.setImageResource(R.drawable.start_yellow)
                binding.imageView4.setImageResource(R.drawable.start_yellow)

                note=4
            }
            binding.imageView5.setOnClickListener {
                binding.imageView1.setImageResource(R.drawable.start_yellow)
                binding.imageView2.setImageResource(R.drawable.start_yellow)
                binding.imageView3.setImageResource(R.drawable.start_yellow)
                binding.imageView4.setImageResource(R.drawable.start_yellow)
                binding.imageView5.setImageResource(R.drawable.start_yellow)
                note=5
            }


            binding.button2.setOnClickListener {
                if(note!=0 && binding.comment.text.toString().isNotEmpty()){
                    if(oldfd==null){
                        ManageFeedback(requireContext()).addFeedback(com.example.milolibrairy.models.feedback(0,args.bookId,ac.b.getInt("id_user"),note,binding.comment.text.toString()))

                    }else{
                        oldfd.comment=binding.comment.text.toString()
                        oldfd.note=note
                        ManageFeedback(requireContext()).updateFeedback(oldfd)
                    }
                    Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(requireContext(), "Fill all the gaps!", Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment feedback.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            feedback().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}