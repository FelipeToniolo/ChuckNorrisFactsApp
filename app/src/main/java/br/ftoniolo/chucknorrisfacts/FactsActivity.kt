package br.ftoniolo.chucknorrisfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.ftoniolo.chucknorrisfacts.databinding.ActivityFactsBinding

class FactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
    }
}