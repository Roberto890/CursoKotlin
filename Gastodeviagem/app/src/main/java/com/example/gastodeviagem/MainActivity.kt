package com.example.gastodeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.gastodeviagem.databinding.ActivityMainBinding
import java.lang.NumberFormatException


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //esse nao é necessario pq o codigo é direto
//        binding.buttonCalculate.setOnClickListener{
//            val a = ""
//        }

        //com isso a activity se comporta igual ao OnClickListener(ta instanciado na class agora)
        //o this é o mainActivity assim fica mais facil pq faz com menos codigo,
        //mas nao tem diferença de um pro outro
        binding.buttonCalculate.setOnClickListener(this)
//        binding.buttonCalculate2.setOnClickListener(this)

    }

    //criar funcoes assim tambem é ruim pq nao vamos saber direito e teriamos q olhar sempre o xml
//    fun teste(view: View) {
//
//    }

    override fun onClick(view: View) {
//        pegamos o id da view e com if separamos cada tipo de click
        val id = view.id
        if (id == R.id.buttonCalculate) {
            calculate()
        }
    }

    private fun calculate() {
        if (validationOK()) {

            try {
                val distance = binding.editDistance.text.toString().toFloat()
                val price = binding.editPrice.text.toString().toFloat()
                val autonomy = binding.editAutonomy.text.toString().toFloat()

                val totalValue = (distance * price) / autonomy
                binding.textTotalValue.text = "R$ ${"%.2f".format(totalValue)}"
            } catch (exception: NumberFormatException) {
                Toast.makeText(this, getString(R.string.informe_valores_validos), Toast.LENGTH_LONG)
                    .show()
            }

        } else {
            //Aparecer notificação caso o validationOK de falso
            Toast.makeText(this, getString(R.string.preeche_todos_campos), Toast.LENGTH_LONG).show()
        }


    }

    private fun validationOK(): Boolean {
        return (binding.editDistance.text.toString() != ""
                && binding.editPrice.text.toString() != ""
                && binding.editAutonomy.text.toString() != ""
                && binding.editAutonomy.text.toString() != "0")
    }
}