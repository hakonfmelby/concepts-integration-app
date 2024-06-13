package com.example.conceptsintegrationapp.model

data class Concept(
    val id: String,
    val subject: String,
    val prefLabel: String,
    val altLabel: String,
    val definition: ConceptDefinition
)
