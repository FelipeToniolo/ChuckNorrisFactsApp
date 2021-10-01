package br.ftoniolo.chucknorrisfacts.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import br.ftoniolo.chucknorrisfacts.databinding.ActivitySearchBinding
import br.ftoniolo.chucknorrisfacts.model.data.ChuckDataSource
import br.ftoniolo.chucknorrisfacts.presenter.search.SearchActivityPresenter
import br.ftoniolo.chucknorrisfacts.presenter.search.ViewSearchHome
import br.ftoniolo.chucknorrisfacts.ui.adapters.CategoriesListAdapter
import br.ftoniolo.chucknorrisfacts.utils.LoadingDialog
import br.ftoniolo.chucknorrisfacts.utils.filterMinLength

class SearchActivity : AppCompatActivity(), ViewSearchHome.View {

    private lateinit var presenter: SearchActivityPresenter
    private val loading = LoadingDialog(this)
    private val dataSource = ChuckDataSource()
    private val categoriesListAdapter by lazy {
        CategoriesListAdapter()
    }

    companion object {
        private const val SEARCH_KEY = "search"
    }

    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        configToolbar()
        presenter = SearchActivityPresenter(this, dataSource, this@SearchActivity)
        presenter.getCategories()
        configRecyclerCategories()
    }
    override fun showDialogLoading() {
        loading.showDialogLoading()
    }

    override fun showFailure(message: String) {
        Toast.makeText(this@SearchActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun hideDialogLoading() {

        loading.hideDialogLoading()

    }

    override fun showResult(facts: ArrayList<String>) {
        categoriesListAdapter.differ.submitList(facts)
    }
    private fun configToolbar() {
        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, FactsActivity::class.java)
            startActivity(intent)
            finish()
        }
        
        binding.toolbarEdittext.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val search = binding.toolbarEdittext.text.toString()
                val intent = Intent(this, FactsActivity::class.java)
                intent.putExtra(SEARCH_KEY, search)
                startActivity(intent)
                finish()
                return@OnEditorActionListener true
            }
            false
        })

    }

    private fun configRecyclerCategories() {
        with(binding.recyclerCategory) {
            adapter = categoriesListAdapter
            layoutManager = GridLayoutManager(this@SearchActivity, 3)
        }

        categoriesListAdapter.setOnClickListener { category ->
            val intent = Intent(this, FactsActivity::class.java)
            intent.putExtra(SEARCH_KEY, category)
            startActivity(intent)
            finish()
        }
    }
}