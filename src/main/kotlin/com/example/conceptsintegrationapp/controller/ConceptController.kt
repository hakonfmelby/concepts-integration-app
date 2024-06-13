package com.example.conceptsintegrationapp.controller

import com.example.conceptsintegrationapp.model.Concept
import com.example.conceptsintegrationapp.service.ConceptService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@Tag(
    name = "Concept Controller", description = "API endpoints to get list of concepts and a single concept by its ID"
)
class ConceptController(private val conceptService: ConceptService) {

    @Operation(
        summary = "Get concepts", description = "API to get concepts, with a RequestParam for pagination"
    )
    @GetMapping("/begrep")
    fun getConcepts(
        @RequestParam("page") page: Int?
    ): ResponseEntity<List<Concept>> {
        val concepts = conceptService.getConcepts(page)

        return if (concepts.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(concepts)
        }
    }

    @Operation(
        summary = "Get single concept", description = "API to get a single concept by its ID"
    )
    @GetMapping("/begrep/{id}")
    fun getConceptById(
        @PathVariable id: String
    ): ResponseEntity<Concept?> {
        val concept = conceptService.getConceptById(id)

        return if (concept == null) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(concept)
        }
    }
}