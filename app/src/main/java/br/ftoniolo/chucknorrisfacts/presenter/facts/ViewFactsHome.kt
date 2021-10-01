package br.ftoniolo.chucknorrisfacts.presenter.facts

import br.ftoniolo.chucknorrisfacts.model.SearchResponse

interface ViewFactsHome {

    interface View {
        fun showDialogLoading()
        fun showFailure(message: String)
        fun hideDialogLoading()
        fun showResult(facts: SearchResponse)
    }
}