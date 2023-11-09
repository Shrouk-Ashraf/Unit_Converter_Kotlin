package com.example.unitconverterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.unitconverterapp.databinding.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var fromKg :Boolean = false
    private var fromMeter :Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set default screen
        setDefaultsWeight("Enter in kilogram","In gram",R.drawable.kilogram,R.drawable.gram)
        setDefaultsDimension("Enter in meters","In Centimeter",R.drawable.meter,R.drawable.centimeter)


        binding.convert.setOnClickListener(View.OnClickListener {
            onConvertWeight()
        })
        binding.fromText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.toText.text =""
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var text = s.toString()
                if(!text.isEmpty()){
                    if(fromKg){
                        convertToKg(text)
                    } else{
                        convertToGram(text)
                    }
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.convertDimension.setOnClickListener(View.OnClickListener {
            onConvertDimension()
        })

        binding.fromTextDimension.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.toTextDimension.text =""
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var text =s.toString()
                if(!text.isEmpty()){
                    if(fromMeter){
                        convertToMeter(text)
                    } else{
                        convertToCentimeter(text)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                Toast.makeText(this@MainActivity, "End",Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun convertToKg(gram :String) {
        var kilograms = gram.toDouble() /1000
        binding.toText.text = kilograms.toString()
    }
    private fun convertToGram(kilogram: String){
        var grams = kilogram.toDouble() *1000
        binding.toText.text = grams.toString()
    }
    private fun  setDefaultsWeight(fromString: String, toString: String, fromIcon:Int, toIcon:Int){
        binding.fromText.hint = fromString
        binding.toText.hint = toString
        binding.fromIcon.setImageResource(fromIcon)
        binding.toIcon.setImageResource(toIcon)
    }
    private fun  setDefaultsDimension(fromString: String, toString: String, fromIcon:Int, toIcon:Int){
        binding.fromTextDimension.hint = fromString
        binding.toTextDimension.hint = toString
        binding.fromIconDimension.setImageResource(fromIcon)
        binding.toIconDimension.setImageResource(toIcon)
    }
    private fun onConvertWeight(){
        if(fromKg){ //convert from kilogram to gram
            setDefaultsWeight("Enter in kilogram","In gram",R.drawable.kilogram,R.drawable.gram)
            if(!binding.fromText.text.isEmpty()){
                var text =binding.fromText.text.toString()
                convertToGram(text)
            }
            fromKg = false
        }else{ //convert from gram to kilogram
            setDefaultsWeight("Enter in gram","In kilogram",R.drawable.gram,R.drawable.kilogram)
            if(!binding.fromText.text.isEmpty()){
                var text =binding.fromText.text.toString()
                convertToKg(text)
            }
            fromKg = true
        }
    }

    private fun convertToMeter(cm: String){
        var meter = cm.toDouble() /100
        binding.toTextDimension.text = meter.toString()
    }
    private fun convertToCentimeter(meter:String){
        var cm = meter.toDouble() * 100
        binding.toTextDimension.text =  cm.toString()
    }

    fun  onConvertDimension(){
        if(fromMeter){//convert from m to cm
            setDefaultsDimension("Enter in meters","In Centimeter",R.drawable.meter,R.drawable.centimeter)
            if(!binding.fromTextDimension.text.isEmpty()){
                var text = binding.fromTextDimension.text.toString()
                convertToCentimeter(text)
            }
            fromMeter =false
        }else{ //convert from cm to m
            setDefaultsDimension("Enter in centimeter", "In meter",R.drawable.centimeter,R.drawable.meter)
            if(!binding.fromTextDimension.text.isEmpty()){
                var text = binding.fromTextDimension.text.toString()
                convertToMeter(text)
            }
            fromMeter =true
        }
    }
}