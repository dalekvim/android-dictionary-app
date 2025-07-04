package com.example.dictionaryapp.model

import kotlinx.serialization.Serializable

data class WordEntry(
    val word: String,
    val phonetics: List<Phonetic>,
    val meanings: List<Meaning>,
    val license: License,
    val sourceUrls: List<String>
)

@Serializable
data class Phonetic(
    val audio: String,
    val sourceUrl: String? = null,
    val license: License? = null,
    val text: String? = null
)

@Serializable
data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>,
    val synonyms: List<String>,
    val antonyms: List<String>
)

@Serializable
data class Definition(
    val definition: String,
    val synonyms: List<String>,
    val antonyms: List<String>,
    val example: String? = null
)

@Serializable
data class License(
    val name: String,
    val url: String
)
