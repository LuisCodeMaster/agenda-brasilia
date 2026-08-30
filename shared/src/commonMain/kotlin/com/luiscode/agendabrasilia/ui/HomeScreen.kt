package com.luiscode.agendabrasilia.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luiscode.agendabrasilia.data.sampleEvents

@Composable
fun HomeScreen() {
    var searchText by remember { mutableStateOf("") }

    val filteredEvents = sampleEvents.filter { event ->
        event.title.contains(searchText, ignoreCase = true) ||
            event.category.contains(searchText, ignoreCase = true) ||
            event.location.contains(searchText, ignoreCase = true)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            top = 20.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Header()
        }

        item {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = {
                    Text("Buscar eventos")
                },
                leadingIcon = {
                    Text("🔎")
                },
                shape = RoundedCornerShape(14.dp)
            )
        }

        item {
            Text(
                text = "Categorias",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Category("🎵 Shows")
                Category("🎭 Cultura")
                Category("🎓 Cursos")
            }
        }

        item {
            Text(
                text = "Eventos próximos",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        if (filteredEvents.isEmpty()) {
            item {
                Text(
                    text = "Nenhum evento encontrado.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
            }
        } else {
            items(filteredEvents) { event ->
                EventCard(event = event)
            }
        }
    }
}

@Composable
private fun Header() {
    Column {
        Text(
            text = "Agenda Brasília",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Descubra o que está acontecendo em Brasília",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun Category(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .then(
                Modifier
            ),
        fontSize = 13.sp
    )
}
