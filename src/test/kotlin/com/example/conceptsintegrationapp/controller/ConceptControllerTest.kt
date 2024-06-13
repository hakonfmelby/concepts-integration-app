package com.example.conceptsintegrationapp.controller

import com.example.conceptsintegrationapp.model.Concept
import com.example.conceptsintegrationapp.model.ConceptDefinition
import com.example.conceptsintegrationapp.service.ConceptService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class ConceptControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var conceptService: ConceptService

    @Test
    fun `Should return concepts`() {
        every { conceptService.getConcepts(any()) } returns listOf(getTestConcept())

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/begrep")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.*").isArray)
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
    }

    @Test
    fun `Should return concept with id`() {
        every { conceptService.getConceptById(any()) } returns getTestConcept()

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/begrep/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.altLabel").value("altLabel"))
    }

    fun getTestConcept(): Concept {
        return Concept("1", "subject", "prefLabel", "altLabel", ConceptDefinition("tekst", Date()))
    }
}