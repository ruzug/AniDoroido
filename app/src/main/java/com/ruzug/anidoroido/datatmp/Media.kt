package com.ruzug.anidoroido.datatmp

sealed interface Media {
    val id: Long
    val name: String
    val imageUrl: String
}