package br.ftoniolo.chucknorrisfacts.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.ftoniolo.chucknorrisfacts.databinding.ActivityFactsBinding
import br.ftoniolo.chucknorrisfacts.model.FactsResultResponse
import br.ftoniolo.chucknorrisfacts.model.SearchResponse
import br.ftoniolo.chucknorrisfacts.model.data.ChuckDataSource
import br.ftoniolo.chucknorrisfacts.presenter.facts.FactsActivityPresenter
import br.ftoniolo.chucknorrisfacts.presenter.facts.ViewFactsHome
import br.ftoniolo.chucknorrisfacts.ui.adapters.FactsListAdapter
import br.ftoniolo.chucknorrisfacts.utils.LoadingDialog
import br.ftoniolo.chucknorrisfacts.utils.Utils

class FactsActivity : AppCompatActivity(), ViewFactsHome.View {

    private var category: String? = null
    private val loading = LoadingDialog(this)
    private val factsListAdapter = FactsListAdapter()
    private val dataSource = ChuckDataSource()
    private lateinit var presenter: FactsActivityPresenter
    private val utils by lazy {
        Utils()
    }

    companion object {
        private const val SEARCH_KEY = "search"
    }

    private lateinit var binding: ActivityFactsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        intent.extras?.let { categorySend ->
            category = categorySend.get(SEARCH_KEY) as String
        }

        category?.let {
            loadSearch(it)
            binding.toolbarTitle.text = category
            binding.recyclerMain.visibility = View.VISIBLE
            binding.gifImageView.visibility = View.GONE
            binding.text.visibility = View.GONE
        }

        with(binding.recyclerMain) {
            adapter = factsListAdapter
            layoutManager = LinearLayoutManager(this@FactsActivity)
        }

        binding.fab.setOnClickListener {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }

        shareFact()
    }

    override fun showDialogLoading() {
        loading.showDialogLoading()
    }

    override fun showFailure(message: String) {
        Toast.makeText(this@FactsActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun hideDialogLoading() {
        loading.hideDialogLoading()
    }

    override fun showResult(facts: SearchResponse) {
        if (facts.result.isNotEmpty()) {
            val mutableListResult = mutableListOf<FactsResultResponse>()
            for (item in facts.result) {
                mutableListResult.add(item)
            }
            factsListAdapter.differ.submitList(mutableListResult)
        } else {
            val errorPhrases = utils.errorPhrases()
            factsListAdapter.differ.submitList(errorPhrases.result)
        }
    }

    private fun shareFact() {
        factsListAdapter.setOnClickListener { category ->
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareSubject = "Chuck Norris Facts:\n\n" + category.value + "\n\n" + category.url
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareSubject)
            startActivity(Intent.createChooser(sharingIntent, "Chuck Norris Facts"))
        }
    }

    private fun loadSearch(query: String) {
            presenter = FactsActivityPresenter(this, dataSource, this)
            presenter.factsAll(query)
    }
}