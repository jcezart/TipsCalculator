package com.example.tipscalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipscalculator.databinding.ActivityMainBinding
import com.example.tipscalculator.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Código para remover a StatusBar
       /* enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val totalTable = intent.getFloatExtra("totalTable", 0.0f)
        val people = intent.getIntExtra("people", 0)
        val percentage = intent.getFloatExtra("percentage", 0.0f)
        val totalPerPerson = intent.getFloatExtra("totalPerPerson", 0.0f)

        binding.tvPercentage.text = percentage.toString()
        binding.tvTotalTable.text = totalTable.toString()
        binding.tvTotalPerPerson.text = totalPerPerson.toString()
        binding.tvPeople.text = people.toString()

        binding.btnRedo.setOnClickListener {
            finish()
        }
    }
}