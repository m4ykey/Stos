package com.m4ykey.text_markdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.mikepenz.markdown.model.ImageData
import com.mikepenz.markdown.model.ImageTransformer

val coil3ImageTransfer = object : ImageTransformer {
    @Composable
    override fun transform(link: String): ImageData? {
        val cleanUrl = remember(link) {
            link.trim().let { url ->
                when {
                    url.isBlank() -> null
                    url.startsWith("//") -> "https:$url"
                    url.startsWith("/") -> null
                    url.startsWith("http://") && !url.startsWith("https://") -> "https://$url"
                    else -> url
                }
            }
        } ?: return null

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data(cleanUrl)
                .crossfade(true)
                .diskCachePolicy(CachePolicy.ENABLED)
                .networkCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build()
        )

        var imageState by remember { mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty) }

        return when (imageState) {
            is AsyncImagePainter.State.Error -> null
            is AsyncImagePainter.State.Success -> {
                ImageData(
                    contentDescription = "Image from $cleanUrl",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    painter = painter
                )
            }
            is AsyncImagePainter.State.Loading -> {
                ImageData(
                    contentDescription = "Loading image",
                    painter = painter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            else -> {
                ImageData(
                    painter = painter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = "Image from $cleanUrl"
                )
            }
        }
    }
}