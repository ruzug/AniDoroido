package com.yzr.anidoroido.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yzr.anidoroido.model.Anime

@Entity(
    tableName = "anime",
)
data class AnimeEntity (
    @PrimaryKey
    val id: Long,
    val nameRomaji: String,
    val imageLarge: String,
)

fun AnimeEntity.asExternalModel() = Anime(
    id = id,
    name = nameRomaji,
    imageUrl = imageLarge,
)