package com.yzr.anidoroido.data.model

import com.yzr.anidoroido.database.model.AnimeEntity
import com.yzr.anidoroido.network.model.MediaNetwork

fun MediaNetwork.asEntity() = AnimeEntity(
    id = media?.id ?: 0L,
    nameRomaji = media?.title?.romaji ?: "",
    imageLarge = media?.coverImage?.large ?: "",
)