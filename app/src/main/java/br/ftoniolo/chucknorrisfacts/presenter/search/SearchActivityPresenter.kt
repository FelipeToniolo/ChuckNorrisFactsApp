package br.ftoniolo.chucknorrisfacts.presenter.search

import android.content.Context
import br.ftoniolo.chucknorrisfacts.model.data.ChuckDataSource
import br.ftoniolo.chucknorrisfacts.utils.Utils

class SearchActivityPresenter(
    val view: ViewSearchHome.View,
    private val dataSource: ChuckDataSource,
    private val context: Context,
) : SearchHome.Presenter {

    private val utils by lazy {
        Utils()
    }

    override fun getCategories() {
        if (utils.isInternetAvailable(context)) {
            this.view.showDialogLoading()
            this.dataSource.getCategories(this)

        } else {
            onError(utils.errorConnection())
        }
    }

    override fun search(query: String) {
        if (utils.isInternetAvailable(context)) {
            this.view.showDialogLoading()
        }
    }

    override fun onSuccess(facts: ArrayList<String>) {
        this.view.showResult(facts)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideDialogLoading()
    }
}