package com.example.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.GuestListener
import com.example.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private lateinit var mlistener: GuestListener
    private val mAdapter: GuestAdapter = GuestAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        allGuestsViewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_all, container, false)

        //É AQUELE BAGULHO QUE O CAIQUE FALOU DE REPETE VARIAS VEZES O MESMO ARQUIVO UM MONTE DE VEZES
        //RecycleView - utiliza para fazer a listagem(3 passos pra usar)
        //1 - Obter a recycler
            //tem que usar o root porque é ela que esta com a criação do fragment_all(esta dentro da fragment)
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)

        //2 - Definir um layout (não é um layout visual e sim de comportamento)
        recycler.layoutManager = LinearLayoutManager(context)

        //3 - Definir um adapter (vai pegar os dados e juntar com o layout ai gerando os dados na tela)
        recycler.adapter = mAdapter

        //criamos uma variavel mListener e adicionamos o objeto Guestlistner nela
        //depois usamos o mAdapter e passamos o mlistener para ele nao ir null para o GuestAdapter
        mlistener = object : GuestListener {
            override fun onClick(id: Int) {
                //quando clicar abrimos uma nova tela com os dados do usuario para alteração igual o MainActivity
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
        //roda a funcao de adicionar o guestlistener para n ficar null no GuestAdapter
        mAdapter.attachListener(mlistener)

        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        allGuestsViewModel.load()
    }

    private fun observer(){
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }

}