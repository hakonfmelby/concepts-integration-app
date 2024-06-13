package com.example.conceptsintegrationapp.service

import com.example.conceptsintegrationapp.http.ConceptHttpClient
import com.example.conceptsintegrationapp.model.Concept
import org.springframework.stereotype.Service


@Service
class ConceptService(private val conceptHttpClient: ConceptHttpClient) {

    fun getConcepts(pageParam: Int?): List<Concept> {
        var page = pageParam

        if (page == null) {
            page = 0
        }

        return conceptHttpClient.requestConcepts(page)
    }

    fun getConceptById(id: String): Concept? {
        return conceptHttpClient.requestConceptById(id)
    }

}