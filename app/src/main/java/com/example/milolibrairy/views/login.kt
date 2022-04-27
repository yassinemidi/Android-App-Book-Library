package com.example.milolibrairy.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.controller.ManageReservation
import com.example.milolibrairy.controller.ManageUser
import com.example.milolibrairy.controller.fVefify
import com.example.milolibrairy.databinding.FragmentLoginBinding
import com.example.milolibrairy.models.book
import com.example.milolibrairy.models.reservation
import com.example.milolibrairy.models.user

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [login.newInstance] factory method to
 * create an instance of this fragment.
 */
class login : Fragment() {
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
        val view= inflater.inflate(R.layout.fragment_login, container, false)
        val binding=FragmentLoginBinding.bind(view)
        val btnlogin=binding.btnlogin
        val etemail=binding.etemail
        val etpassword=binding.etpassword
        val i=Intent(activity, IndexActivity::class.java)
        val iadm=Intent(activity, AdminActivity::class.java)
        val b=Bundle()
        btnlogin.setOnClickListener {
            if(fVefify().verifyEmail(etemail.text.toString()) && etpassword.text.toString().isNotEmpty()){


            val usr=ManageUser(requireContext()).login(etemail.text.toString(),etpassword.text.toString())
            if(usr!= null){
                if(usr.type==1 || usr.type==2){
                    b.putInt("id_user",usr.id)
                    b.putString("nom_user",usr.nom)
                    b.putString("prenom_user",usr.prenom)
                    b.putString("email_user",usr.email)
                    b.putString("password_user",usr.password)
                    b.putInt("type_user",usr.type)
                    i.putExtras(b)
                    startActivity(i)
                }else if(usr.type==0){
                    b.putInt("id_user",usr.id)
                    b.putString("nom_user",usr.nom)
                    b.putString("prenom_user",usr.prenom)
                    b.putString("email_user",usr.email)
                    b.putString("password_user",usr.password)
                    b.putInt("type_user",usr.type)
                    iadm.putExtras(b)
                    startActivity(iadm)
                }

            }else{
                Toast.makeText(requireContext(), "Login or password incorrect!", Toast.LENGTH_SHORT).show()
            }

            }else{
                Toast.makeText(requireContext(), "Please write valid informations!", Toast.LENGTH_SHORT).show()

            }







        }


        val txtregister=binding.txtregister
        txtregister.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login_to_register)
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
         * @return A new instance of fragment login.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            login().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}