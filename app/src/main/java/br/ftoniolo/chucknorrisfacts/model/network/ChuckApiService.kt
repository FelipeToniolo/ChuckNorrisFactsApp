package br.ftoniolo.chucknorrisfacts.model.network

import br.ftoniolo.chucknorrisfacts.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckApiService {
    @GET("categories")
    fun getCategories(): Call<ArrayList<String>>

    @GET("search")
    fun getFacts(
        @Query("query")
        query: String
    ) : Call<SearchResponse>
}