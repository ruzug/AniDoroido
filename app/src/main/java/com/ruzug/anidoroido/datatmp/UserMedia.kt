package com.ruzug.anidoroido.datatmp

data class UserMedia(
    val score: Float?,
    val progress: Int,
    val progressVolumes: Int = 0,
    val category: Category,
    val media: Media
)