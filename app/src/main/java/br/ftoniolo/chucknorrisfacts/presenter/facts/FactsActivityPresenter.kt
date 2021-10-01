package br.ftoniolo.chucknorrisfacts.presenter.facts

import android.annotation.SuppressLint
import android.content.Context
import br.ftoniolo.chucknorrisfacts.model.SearchResponse
import br.ftoniolo.chucknorrisfacts.model.data.ChuckDataSource
import br.ftoniolo.chucknorrisfacts.utils.Utils

@SuppressLint("StaticFieldLeak")
class FactsActivityPresenter(
    val view: ViewFactsHome.View,
    private val dataSource: ChuckDataSource,
    private val context: Context
) : FactsHome.Presenter {

    private val utils by lazy {
        Utils()
    }

    override fun factsAll(query: String) {
        if (utils.isInternetAvailable(context)) {
            this.view.showDialogLoading()
            this.dataSource.getFact(this, query)
        } else {
            onError(utils.errorConnection())
        }
    }

    override fun onSuccess(facts: SearchResponse) {
        this.view.showResult(facts)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideDialogLoading()
    }
}