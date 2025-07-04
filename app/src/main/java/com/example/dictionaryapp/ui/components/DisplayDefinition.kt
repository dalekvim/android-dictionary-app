package com.example.dictionaryapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dictionaryapp.model.WordEntry

@Composable
fun DisplayDefinition(
    definition: List<WordEntry>,
    modifier: Modifier = Modifier
) {
    val entry = definition.firstOrNull()

    if (entry == null) {
        Text(
            text = "No definition found.",
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        return
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Word
            Text(
                text = entry.word,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Phonetics
            entry.phonetics
                .filter { !it.text.isNullOrBlank() }
                .forEach { phonetic ->
                    PlayablePhonetic(phonetic = phonetic)
                }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Meanings
        entry.meanings.forEach { meaning ->
            item {
                Text(
                    text = meaning.partOfSpeech,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            meaning.definitions.forEachIndexed { index, def ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "${index + 1}. ${def.definition}",
                                style = MaterialTheme.typography.bodyLarge
                            )

                            if (!def.example.isNullOrBlank()) {
                                Text(
                                    text = "\"${def.example}\"",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }

                            if (def.synonyms.isNotEmpty()) {
                                Text(
                                    text = "Synonyms: ${def.synonyms.joinToString()}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }

                            if (def.antonyms.isNotEmpty()) {
                                Text(
                                    text = "Antonyms: ${def.antonyms.joinToString()}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                }
            }

            if (meaning.synonyms.isNotEmpty()) {
                item {
                    Text(
                        text = "Overall synonyms: ${meaning.synonyms.joinToString()}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            if (meaning.antonyms.isNotEmpty()) {
                item {
                    Text(
                        text = "Overall antonyms: ${meaning.antonyms.joinToString()}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        item {
            if (entry.sourceUrls.isNotEmpty()) {
                Text(
                    text = "Source: ${entry.sourceUrls.first()}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            Text(
                text = "License: ${entry.license.name}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
