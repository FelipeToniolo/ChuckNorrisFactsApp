package br.ftoniolo.chucknorrisfacts.model.data

import br.ftoniolo.chucknorrisfacts.model.network.WebClient
import br.ftoniolo.chucknorrisfacts.presenter.facts.FactsHome
import br.ftoniolo.chucknorrisfacts.presenter.search.SearchHome
import kotlinx.coroutines.*

class ChuckDataSource {
    fun getCategories(callback: SearchHome.Presenter) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.IO) {
                WebClient.api.getCategories().execute()
            }
            if (response.isSuccessful) {
                response.body()?.let { categories ->
                    val listCategories = categories.shuffled().take(8)

                    callback.onSuccess(listCategories as ArrayList<String>)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

    fun getFact(callback: FactsHome.Presenter, query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.IO) {
                WebClient.api.getFacts(query).execute()
            }
            if (response.isSuccessful) {
                response.body()?.let { isFact ->
                    callback.onSuccess(isFact)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }
}