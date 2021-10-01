package br.ftoniolo.chucknorrisfacts.model

import java.io.Serializable

data class SearchResponse(
    val result: ArrayList<FactsResultResponse>,
    val total: Int
):Serializable