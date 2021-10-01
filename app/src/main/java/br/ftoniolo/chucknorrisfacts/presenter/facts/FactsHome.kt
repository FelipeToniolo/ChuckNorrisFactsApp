package br.ftoniolo.chucknorrisfacts.presenter.facts

import br.ftoniolo.chucknorrisfacts.model.SearchResponse

interface FactsHome {
    interface Presenter {
        fun factsAll(query: String)
        fun onSuccess(facts: SearchResponse)
        fun onError(message:String)
        fun onComplete()
    }
}