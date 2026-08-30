package com.luiscode.agendabrasilia.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            .background(Color(0xFFF7F7F7)),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
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

        items(filteredEvents) { event ->
            EventCard(event = event)
        }
    }
}

@Composable
private fun Header() {
    Column {
        Text(
            text = "Agenda Brasília",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Descubra o que está acontecendo em Brasília",
            color = Color.Gray
        )
    }
}

@Composable
private fun Category(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .background(
                Color.White,
                RoundedCornerShape(12.dp)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 10.dp
            ),
        fontSize = 13.sp
    )
}
