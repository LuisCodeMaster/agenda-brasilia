package com.luiscode.agendabrasilia.data

import com.luiscode.agendabrasilia.model.Event

val sampleEvents = listOf(
    Event(
        id = "1",
        title = "Festival de Tecnologia de Brasília",
        description = "Um evento dedicado à tecnologia, inovação e programação.",
        date = "30 ago",
        time = "14:00",
        location = "Brasília",
        category = "Tecnologia",
        price = "Grátis"
    ),
    Event(
        id = "2",
        title = "Show de Rock em Brasília",
        description = "Uma noite de música ao vivo com bandas de rock.",
        date = "30 ago",
        time = "20:00",
        location = "Asa Sul",
        category = "Shows",
        price = "R$ 40"
    ),
    Event(
        id = "3",
        title = "Feira Cultural de Brasília",
        description = "Arte, cultura, gastronomia e atrações para toda a família.",
        date = "31 ago",
        time = "10:00",
        location = "Plano Piloto",
        category = "Cultura",
        price = "Grátis"
    )
)
