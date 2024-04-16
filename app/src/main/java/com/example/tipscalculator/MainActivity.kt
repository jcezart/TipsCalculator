package com.example.tipscalculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipscalculator.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(){


   private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var percentage: Float = 0.0f

        // 10%
        binding.rbOptionOne.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                percentage = 0.1f
            }
        }
        // 15%
        binding.rbOptionTwo.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                percentage = 0.15f
            }
        }
        // 20%
        binding.rbOptionThree.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                percentage = 0.2f
            }
        }

        binding.btnClean.setOnClickListener {
            clean()


        }

        binding.btnDone.setOnClickListener {

            val totalTableText : String = binding.tieTotal.text.toString()
            val nPeopleText: String = binding.tieNumPeople.text.toString()

            if (totalTableText.isNotEmpty() && nPeopleText.isNotEmpty()) {
                var TotalTable: Float = totalTableText.toFloat()
                val people: Int = nPeopleText.toInt()

                val totalTemp: Float = (TotalTable / people)
                val totalPerPerson: Float = totalTemp + (totalTemp * percentage)
                TotalTable += (TotalTable * percentage)
                percentage *= 100.0f

                val intent = Intent(this, SummaryActivity::class.java)
                intent.apply {

                    putExtra("totalTable", TotalTable)
                    putExtra("people", people)
                    putExtra("percentage", percentage)
                    putExtra("totalPerPerson", totalPerPerson)
                }
                clean()
                startActivity(intent)

            }else {
                Toast.makeText(this, "Fill all the fields.", Toast.LENGTH_LONG).show()
            }

        }


    }
    private fun clean(){
        binding.tvResult.text = ""
        binding.tieTotal.setText("")
        binding.tieNumPeople.setText("")
        binding.rbOptionOne.isChecked = false
        binding.rbOptionTwo.isChecked = false
        binding.rbOptionThree.isChecked = false
        val hideKeyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        hideKeyboard.hideSoftInputFromWindow(binding.root.windowToken,0)
    }
}

