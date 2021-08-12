package com.example.navigationdrawer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.navigationdrawer.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            sendEmail()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_principal, R.id.nav_services, R.id.nav_clients,
                R.id.nav_contact, R.id.nav_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun sendEmail() {
        //Abre a tela de telefone ja com o numero que quero
        val phone = "tel: 11951692476"
        val image = "https://static7.depositphotos.com/1005793/710/i/600/depositphotos_7100045-stock-photo-shell-on-the-beach.jpg"
        val maps = "https://www.google.com/maps/search/vila+lobos/@-23.5409157,-46.7380927,15z/data=!3m1!4b1"
//        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phone))
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(image))
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(maps))

        //Intent action send pra compartilhar, no caso vamos colocar o email e pra quem enviar
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("atendimento@atmconsultoria.com.br", "roberto.jesus@gmail.com.br"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contato pelo App")
        intent.putExtra(Intent.EXTRA_TEXT, "Mensagem automática")

        //esse set type marca qual o tipo da intent no caso message do tipo email, tem varios tipos de types pra colocar
        //so pesquisar por lista de mime types
//        intent.setType("message/rfc822")
//        intent.setType("text/plain")
        intent.setType("image/*")

        //cretechooser faz com que ele veja quem pode rodar essa intent e mostra pro usuario
        startActivity(Intent.createChooser(intent, "Compartilhar"))

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}