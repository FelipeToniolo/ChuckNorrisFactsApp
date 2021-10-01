package br.ftoniolo.chucknorrisfacts.presenter.search

interface ViewSearchHome {

    interface View {
        fun showDialogLoading()
        fun showFailure(message: String)
        fun hideDialogLoading()
        fun showResult(facts: ArrayList<String>)
    }
}