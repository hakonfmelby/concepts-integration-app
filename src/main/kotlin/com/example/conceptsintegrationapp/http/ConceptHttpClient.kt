package com.example.conceptsintegrationapp.http

import com.example.conceptsintegrationapp.model.Concept
import com.example.conceptsintegrationapp.service.ConceptService
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.Call
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ConceptHttpClient {

    private val okHttpClient: OkHttpClient = OkHttpClient()

    private val objectMapper: ObjectMapper = ObjectMapper()

    private final val conceptUrl = "https://search.fellesdatakatalog.digdir.no/concepts"


    fun requestConcepts(page: Int): List<Concept> {
        val formBody: RequestBody = FormBody.Builder()
            .add("page", page.toString())
            .build()

        val request = Request.Builder()
            .url(conceptUrl)
            .post(formBody)
            .build()

        val call: Call = okHttpClient.newCall(request)
        val response: Response = call.execute()

        if (!response.isSuccessful) {
            LOG.error("Failed to retrieve concepts: $response")
            return emptyList()
        }

        return objectMapper.reader().readValue(response.body!!.string())
    }

    fun requestConceptById(id: String): Concept? {
        val formBody: RequestBody = FormBody.Builder()
            .add("id", id)
            .build()

        val request = Request.Builder()
            .url(conceptUrl)
            .post(formBody)
            .build()

        val call: Call = okHttpClient.newCall(request)
        val response: Response = call.execute()

        if (!response.isSuccessful) {
            LOG.error("Failed to retrieve concept by id: $response")
            return null
        }

        return objectMapper.reader().readValue(response.body!!.string())
    }

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(ConceptService::class.java)
    }
}