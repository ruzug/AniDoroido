package com.ruzug.anidoroido.datatmp

data class Manga(
    override val id: Long = 0,
    override val name: String = "",
    override val imageUrl: String = "",
    val volumes: Int = 0,
    val chapters: Int = 0
): Media