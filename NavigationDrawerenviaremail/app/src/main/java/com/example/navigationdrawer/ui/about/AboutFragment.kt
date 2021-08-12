package com.example.navigationdrawer.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationdrawer.R
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFragment : Fragment() {
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

        val version = Element().setTitle("Versão 1.0")

        return AboutPage(activity)
            .setImage(R.drawable.logo)
            .setDescription("ATM consltoria tem missão de apoiar quem desejam alçancar" +
                    " o sucesso atráves da exelencia em gestao")
            .addGroup("Entre em contato")
            .addEmail("atendimento@atmconsultoria.com.br")
            .addWebsite("https://www.google.com.br", "Acesse nosso site")
            .addGroup("Redes Sociais")
            .addFacebook("MercadoDeSkyrim", "Facebook")
            .addTwitter("twitter", "Twitter")
            .addYoutube("youtube", "Youtube")
            .addPlayStore("com.facebook.katana", "Download")
            .addItem(version)
            .create()



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AboutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AboutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}