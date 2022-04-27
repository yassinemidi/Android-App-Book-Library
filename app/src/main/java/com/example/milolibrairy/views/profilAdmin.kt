package com.example.milolibrairy.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageUser
import com.example.milolibrairy.controller.fVefify
import com.example.milolibrairy.databinding.FragmentProfilAdminBinding
import com.example.milolibrairy.databinding.FragmentProfileBinding
import com.example.milolibrairy.models.user

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [profilAdmin.newInstance] factory method to
 * create an instance of this fragment.
 */
class profilAdmin : Fragment() {
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
        val view= inflater.inflate(R.layout.fragment_profil_admin, container, false)

        val binding=FragmentProfilAdminBinding.bind(view)

        val activity=activity as AdminActivity



       val nom=binding.etlastNamepa
        val prenom=binding.etprenompa
        val email=binding.etemailpa
        val password=binding.etpasswordpa
        val confpassword=binding.etconfirmPassworda
        val btn=binding.btnUpdatea



        nom.setText(activity.b.getString("nom_user"))
        prenom.setText(activity.b.getString("prenom_user"))
        email.setText(activity.b.getString("email_user"))



        btn.setOnClickListener {
            if(fVefify().verifyPassword(password.text.toString(),confpassword.text.toString()) && fVefify().verifyEmail(email.text.toString()) && nom.text.toString().isNotEmpty() && prenom.text.toString().isNotEmpty() ){
                ManageUser(requireContext()).updateuser(user(activity.b.getInt("id_user"),nom.text.toString(),prenom.text.toString(),email.text.toString(), password.text.toString(),activity.b.getInt("type_user")))
                activity.b.putString("nom_user",nom.text.toString())
                activity.b.putString("prenom_user",prenom.text.toString())
                activity.b.putString("email_user",email.text.toString())
                activity.b.putString("password_user",password.text.toString())
            }else{
                Toast.makeText(requireContext(), "Please write a correct informations!", Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment profilAdmin.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            profilAdmin().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}