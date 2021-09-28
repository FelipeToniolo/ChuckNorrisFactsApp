package br.ftoniolo.chucknorrisfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.ftoniolo.chucknorrisfacts.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
    }
}