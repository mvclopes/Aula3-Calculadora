package com.mvclopes.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.btnConverter
import kotlinx.android.synthetic.main.activity_main.inputCv
import kotlinx.android.synthetic.main.activity_main.inputName
import kotlinx.android.synthetic.main.activity_main.labelResult

private const val POWER_CONVERTER_RATIO = 735

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adicionado listener para escutar evento de clique no botão de converter
        btnConverter.setOnClickListener { calculatePowerInWatts() }
    }

    private fun calculatePowerInWatts() {
        // Obtenção do valor de potência em Cavalo Vapor (CV) e nome do usuário
        val powerInCv = inputCv.text.toString()
        val name = inputName.text.toString()

        // Chamada ao método para validar os valores inseridos pelo usuário
        if (isValidInputs(powerInCv, name)) {
            converterPowerToWatts(powerInCv, name)
        } else {
            // Setando texto para situação de dados inválidos
            labelResult.text = getString(R.string.error)
        }

    }

    // Método para converter potência de Cavalo Vapor (CV) para Watts (W)
    private fun converterPowerToWatts(powerInCv: String, name: String) {
        // Conversão dos valores inseridos de String para Double (Texto para decimal)
        val powerValue = powerInCv.toDouble()

        val result = powerValue * POWER_CONVERTER_RATIO
        labelResult.text = getString(R.string.power_result, name, result.toString())
    }

    // Método para validar se os valores inseridos são válidos
    private fun isValidInputs(powerInCv: String, name: String): Boolean {
        return powerInCv.isNotEmpty() && name.isNotEmpty()
    }
}