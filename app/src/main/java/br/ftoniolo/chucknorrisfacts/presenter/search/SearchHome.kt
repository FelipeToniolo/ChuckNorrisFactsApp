package br.ftoniolo.chucknorrisfacts.presenter.search

interface SearchHome {
    interface Presenter {
        fun getCategories()
        fun search(query: String)
        fun onSuccess(facts: ArrayList<String>)
        fun onError(message:String)
        fun onComplete()
    }
}