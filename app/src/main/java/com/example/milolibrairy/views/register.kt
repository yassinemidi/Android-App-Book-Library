package com.example.milolibrairy.views

import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageUser
import com.example.milolibrairy.controller.fVefify
import com.example.milolibrairy.databinding.FragmentRegisterBinding
import com.example.milolibrairy.models.user

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [register.newInstance] factory method to
 * create an instance of this fragment.
 */
class register : Fragment() {
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
        val view= inflater.inflate(R.layout.fragment_register, container, false)
        val binding=FragmentRegisterBinding.bind(view)

        val btnregister=binding.btnregister
        val txtlogin=binding.txtlogin
        val etnom=binding.etnom
        val etprenom=binding.etprenom
        val etemail=binding.etemail
        val etpassword=binding.etpassword
        val etconfpassword=binding.etconfirmPassword
        val btnckprof=binding.rbtnprof
        val btncketud=binding.rbtnetud
        var type_user=0


        btnregister.setOnClickListener {
            if(btncketud.isChecked){
                type_user=2
            }
            if(btnckprof.isChecked){
                type_user=1
            }

            if(fVefify().verifyEmail(etemail.text.toString()) && fVefify().verifyPassword(etpassword.text.toString(),etconfpassword.text.toString()) && etnom.text.toString().isNotEmpty() && etprenom.text.toString().isNotEmpty()){
                ManageUser(requireContext()).AddUser(user(1,
                    etnom.text.toString(),
                    etprenom.text.toString(),
                    etemail.text.toString(),
                    etpassword.text.toString(),
                    type_user))

                Navigation.findNavController(view).navigate(R.id.action_register_to_login)
            }else{
                Toast.makeText(requireContext(), "Informations incorrect!", Toast.LENGTH_SHORT).show()

            }



        }


        txtlogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_register_to_login)
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
         * @return A new instance of fragment register.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            register().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}