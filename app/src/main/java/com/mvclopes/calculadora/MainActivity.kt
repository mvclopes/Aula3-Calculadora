package com.mvclopes.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.btnCalculate
import kotlinx.android.synthetic.main.activity_main.inputAlcohol
import kotlinx.android.synthetic.main.activity_main.inputGas
import kotlinx.android.synthetic.main.activity_main.labelResult

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adicionado listener para escutar evento de clique no botão
        btnCalculate.setOnClickListener { calculatePrice() }
    }

    private fun calculatePrice() {
        // Obtenção dos valores de álcool e gasolina dos editTexts
        val alcoholPrice = inputAlcohol.text.toString()
        val gasPrice = inputGas.text.toString()

        // Chamada ao método para validar os valores inseridos pelo usuário
        if (isValidInputs(alcoholPrice, gasPrice)) {
            chooseBestOption(alcoholPrice, gasPrice)
        } else {
            labelResult.text = getString(R.string.error)
        }
    }

    /*
        Método para determinar qual combustível possui melhor custo benefício,
        setando resultado na label, de modo a informar o usuário.
     */

    private fun chooseBestOption(alcoholPrice: String, gasPrice: String) {
        // Conversão dos valores inseridos de String para Double (Texto para decimal)
        val alcoholValue = alcoholPrice.toDouble()
        val gasValue = gasPrice.toDouble()

        val result = alcoholValue / gasValue
        labelResult.text = getBestOptionLabel(result)
    }

    // Método para selecionar a string correta de acordo com o custo benefício (álcool/gasolina)
    private fun getBestOptionLabel(result: Double): String {
        val option = if (result >= 0.7) "gasolina" else "álcool"
        return getString(R.string.best_option_result, option)
    }

    // Método para validar se os valores inseridos nos campos de preço da gasolina e álcool são válidos
    private fun isValidInputs(alcoholPrice: String, gasPrice: String): Boolean {
        return !(alcoholPrice.isEmpty() || gasPrice.isEmpty())
    }
}