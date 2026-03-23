package com.yzr.anidoroido.network.model

import android.annotation.SuppressLint
import com.yzr.anidoroido.model.Anime
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class MediaNetwork (
    val media: Media?,
) {

    @Serializable
    data class Media(
        val id: Long?,
        val title: Title?,
        val coverImage: CoverImage?,
    )

    @Serializable
    data class Title(
        val romaji: String?
    )

    @Serializable
    data class CoverImage(
        val large: String?
    )
}

fun MediaNetwork.asExternalModel(): Anime =
    Anime(
        id = media?.id?: 0,
        name = media?.title?.romaji.orEmpty(),
        imageUrl = media?.coverImage?.large.orEmpty()
    )