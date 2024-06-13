package com.example.conceptsintegrationapp.service

import com.example.conceptsintegrationapp.http.ConceptHttpClient
import com.example.conceptsintegrationapp.model.Concept
import com.example.conceptsintegrationapp.model.ConceptDefinition
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


@SpringBootTest
class ConceptServiceTest {

    @MockK
    private lateinit var conceptHttpClient: ConceptHttpClient

    private lateinit var conceptService: ConceptService

    @BeforeEach
    fun setUp() {
        conceptService = ConceptService(conceptHttpClient)
    }


    @Test
    fun `Should get concepts on page 0, empty list on page 1`() {
        every { conceptHttpClient.requestConcepts(0) } returns listOf(getTestConcept())
        every { conceptHttpClient.requestConcepts(1) } returns emptyList()

        var concepts = conceptService.getConcepts(0)
        assertThat(concepts.size).isEqualTo(1)
        assertThat(concepts[0].id).isEqualTo("1")

        concepts = conceptService.getConcepts(1)
        assertThat(concepts.size).isEqualTo(0)
    }

    @Test
    fun `Should get concept by id 1, but not on id 2`() {
        every { conceptHttpClient.requestConceptById("1") } returns getTestConcept()
        every { conceptHttpClient.requestConceptById("2") } returns null

        var concept = conceptService.getConceptById("1")
        assertThat(concept!!.id).isEqualTo("1")
        assertThat(concept.subject).isEqualTo("subject")

        concept = conceptService.getConceptById("2")
        assertThat(concept).isEqualTo(null)
    }

    fun getTestConcept(): Concept {
        return Concept("1", "subject", "prefLabel", "altLabel", ConceptDefinition("tekst", Date()))
    }
}