package com.luiscode.agendabrasilia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform