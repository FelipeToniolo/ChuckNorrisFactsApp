package br.ftoniolo.chucknorrisfacts.utils

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import br.ftoniolo.chucknorrisfacts.model.FactsResultResponse
import br.ftoniolo.chucknorrisfacts.model.SearchResponse

@Suppress("DEPRECATION")
class Utils {


    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }

            }
        }
        return result
    }

    fun errorConnection(): String{
        return "SELECT ROUNDHOUSE_KICK FROM CHUC… Lost connection...\nChuck Norris App,\nDid not detect any internet connection".uppercase()
    }

    fun errorPhrases(): SearchResponse {
        val list = mutableListOf<SearchResponse>()

        val phrases1 = SearchResponse(
            result = arrayListOf(
                FactsResultResponse(
                    categories = null,
                    created_at = null,
                    icon_url = null,
                    id = null,
                    updated_at = null,
                    url = null,
                    value = "Chuck found 0 results from your search, but I'll tell you something cool.\nChuck Norris lost his virginity before your father.".uppercase()
                )
            ), total = 0
        )

        val phrases2 = SearchResponse(
            result = arrayListOf(
                FactsResultResponse(
                    categories = null,
                    created_at = null,
                    icon_url = null,
                    id = null,
                    updated_at = null,
                    url = null,
                    value = "Chuck found 0 results from your search, but I'll tell you something cool.\nMegabyte, Gigabyte, Terabyte, Petabyte, Exabyte, Chuckbite.".uppercase()
                )
            ), total = 0
        )

        val phrases3 = SearchResponse(
            result = arrayListOf(
                FactsResultResponse(
                    categories = null,
                    created_at = null,
                    icon_url = null,
                    id = null,
                    updated_at = null,
                    url = null,
                    value = "Chuck found 0 results from your search, but I'll tell you something cool.\nChuck Norris kills two stones with one bird.".uppercase()
                )
            ), total = 0
        )

        val phrases4 = SearchResponse(
            result = arrayListOf(
                FactsResultResponse(
                    categories = null,
                    created_at = null,
                    icon_url = null,
                    id = null,
                    updated_at = null,
                    url = null,
                    value = "Chuck found 0 results from your search, but I'll tell you something cool.\nWhen urinating, Chuck Norris can easily pierce titanium.".uppercase()
                )
            ), total = 0
        )

        val phrases5 = SearchResponse(
            result = arrayListOf(
                FactsResultResponse(
                    categories = null,
                    created_at = null,
                    icon_url = null,
                    id = null,
                    updated_at = null,
                    url = null,
                    value = "Chuck found 0 results from your search, but I'll tell you something cool.\nChuck norris scores Olympic goal in foosball".uppercase()
                )
            ), total = 0
        )

        val phrases6 = SearchResponse(
            result = arrayListOf(
                FactsResultResponse(
                    categories = null,
                    created_at = null,
                    icon_url = null,
                    id = null,
                    updated_at = null,
                    url = null,
                    value = "Chuck found 0 results from your search, but I'll tell you something cool.\nI don't remember well about this subject".uppercase()
                )
            ), total = 0
        )

        list.addAll(listOf(phrases1, phrases2, phrases3, phrases4, phrases5, phrases6))

        return list.random()
    }
}