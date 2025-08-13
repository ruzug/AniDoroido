package com.ruzug.anidoroido.datatmp

data class Anime(
    override val id: Long = 0,
    override val name: String = "",
    override val imageUrl: String = "",
    val episodes: Int = 0,
): Media