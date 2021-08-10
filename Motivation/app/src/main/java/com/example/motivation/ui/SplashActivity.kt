package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.databinding.ActivitySplashBinding
import com.example.motivation.infra.SecurityPreferences.MotivationConstants
import com.example.motivation.infra.SecurityPreferences.SecurityPreferences

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySplashBinding
    //context so existe depois  da fun onCreate
    private lateinit var mSecurityPreferences : SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mSecurityPreferences = SecurityPreferences(this)


//        Esconde o suportaction bar aquele barrinha que fica com o nome do projeto
        if (supportActionBar != null){
            supportActionBar!!.hide()
        }

        binding.buttonSave.setOnClickListener(this)

        // SharedPreferences - string, mode
        // ele usado para dados pequenos que nao mudam com frequencia  e acessos rapidos, cadastro de usuario ex tem q ser feito num banco de dados
        //A string é o nome do sharedpreferences o nome do arquivo q vai guardas as informacoes
        // O Mode tem no context - nesse caso o private = somente a minha aplicacao pode abrir o sharedPreferences
//        val shared = this.getSharedPreferences("Motivation", Context.MODE_PRIVATE)
        //sharedpreferences é em chave e valor entao vamos editar uma chave e string para o nome do usuario como valor
//        shared.edit().putString("key", "value").apply()


        val securityPreferences = SecurityPreferences(this)
        securityPreferences.storeString("","")
    }

    override fun onClick(view: View) {
        val id = view.id
        // se o id que for clicado for igual ao buttonSave roda a funcao que criamos
        if (id == R.id.buttonSave){
            handleSave()
        }
    }

    private fun handleSave(){
        val name = binding.editName.text.toString()

        if (name != ""){
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            //Intent é utilizado para varias coisas uma delas é navegacao entra activites
            //Usamos o contexto e vamos navegar para a mainactivity e o java cria uma instancia de MainActivity
            startActivity(Intent(this, MainActivity::class.java))
        } else{
            //contexto é a aplicacao em si
            //o this funciona pq tem AppCompatActivity la em cima
            // ou podemos colocar applicationContext tambem funciona
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_LONG).show()
        }
    }
}