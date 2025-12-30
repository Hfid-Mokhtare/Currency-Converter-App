package com.example.myapplication3

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    // currency in the app
    private val amirican_dolare = "United States Dollar"
    private val europien_euro = "Euro"
    private val algirian_dinar = "Algerian Dinar"
    private val morocan_dirham = "Moraccan Dirham"

    // map of curency values
    val values = mapOf(
        amirican_dolare to 1.0,
        europien_euro to 0.85,
        algirian_dinar to 129.53,
        morocan_dirham to 9.10

    )


    // all view in the layout of activity_main
    lateinit var convertBtn: Button
    lateinit var amountEt: TextInputEditText
    lateinit var resultEt: TextInputEditText
    lateinit var toInputList: AutoCompleteTextView
    lateinit var fromInputList: AutoCompleteTextView
//    lateinit var toDropDownMenu: TextInputLayout
//    lateinit var fromDropDownMenu: TextInputLayout




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findAllViewById()
        currencyDropDownMenu()
        convertCurrency()
    }

    private fun findAllViewById() {
        convertBtn = findViewById(R.id.convert_btn)
        toInputList = findViewById(R.id.to_auto_complete_text_view)
        fromInputList = findViewById(R.id.from_auto_complete_text_view)
        amountEt = findViewById(R.id.amounte_edit_text)
        resultEt = findViewById(R.id.result_edit_text)
//        fromDropDownMenu=findViewById(R.id.from_input_layout)
//        toDropDownMenu=findViewById(R.id.to_input_layout)
    }

    private fun currencyDropDownMenu() {
        val currencyList = listOf(amirican_dolare, europien_euro, algirian_dinar, morocan_dirham)
        val adapter = ArrayAdapter(this, R.layout.drop_down_menu_list, currencyList)
        toInputList.setAdapter(adapter)
        fromInputList.setAdapter(adapter)
    }

    private fun convertCurrency() {
        convertBtn.setOnClickListener {
            calculateResult()
        }
        amountEt.addTextChangedListener() {
            calculateResult()
        }
    }

    private fun calculateResult(){
        if(amountEt.text.toString().isNotEmpty() and fromInputList.text.toString().isNotEmpty() and toInputList.text.toString().isNotEmpty() ) {
            val amount = amountEt.text.toString().toDouble()
            val currencyFromField = values[fromInputList.text.toString()]
            val currencyToField = values[toInputList.text.toString()]

            val result = convert(currencyFromField!!, currencyToField!!, amount)
            val formattedRsult= String.format("%.3f",result)
            resultEt.setText(formattedRsult)

        }else{
            amountEt.error="field is required"
            fromInputList.error="field is required"
            toInputList.error="field is required"
        }
    }
    private fun convert(from: Double, to: Double, amount: Double): Double {
        val result=(amount.times(to))/from

        return result
    }



}



