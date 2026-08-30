package com.luiscode.agendabrasilia

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Event(
    val title: String,
    val date: String,
    val time: String,
    val location: String,
    val category: String,
    val price: String
)

private val sampleEvents = listOf(
    Event(
        title = "Festival de Tecnologia de Brasília",
        date = "30 ago",
        time = "14:00",
        location = "Brasília",
        category = "Tecnologia",
        price = "Grátis"
    ),
    Event(
        title = "Show de Rock em Brasília",
        date = "30 ago",
        time = "20:00",
        location = "Asa Sul",
        category = "Shows",
        price = "R$ 40"
    ),
    Event(
        title = "Feira Cultural de Brasília",
        date = "31 ago",
        time = "10:00",
        location = "Plano Piloto",
        category = "Cultura",
        price = "Grátis"
    )
)

@Composable
fun App() {
    MaterialTheme {
        AgendaBrasiliaApp()
    }
}

@Composable
private fun AgendaBrasiliaApp() {
    var searchText by remember { mutableStateOf("") }

    val filteredEvents = sampleEvents.filter { event ->
        event.title.contains(searchText, ignoreCase = true) ||
                event.category.contains(searchText, ignoreCase = true) ||
                event.location.contains(searchText, ignoreCase = true)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF7F7F7)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
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
                CategoryRow()
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
                    EmptyState()
                }
            } else {
                items(filteredEvents) { event ->
                    EventCard(event)
                }
            }
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Agenda Brasília",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Descubra o que está acontecendo",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF1565C0)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🔔",
                fontSize = 20.sp
            )
        }
    }
}

@Composable
private fun CategoryRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CategoryButton("Hoje")
        CategoryButton("Amanhã")
        CategoryButton("Grátis")
    }
}

@Composable
private fun CategoryButton(text: String) {
    TextButton(
        onClick = {},
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {
        Text(text)
    }
}

@Composable
private fun EventCard(event: Event) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = event.category.uppercase(),
                color = Color(0xFF1565C0),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = event.title,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text("📅 ${event.date} • ${event.time}")
            Text("📍 ${event.location}")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = event.price,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🔎",
            fontSize = 40.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Nenhum evento encontrado",
            fontWeight = FontWeight.Bold
        )
    }
}
