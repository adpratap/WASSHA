package com.noreplypratap.wassha.model

data class UiJokeModel(
    val id: Int?,
    val joke: String?,
    val isLocal: Boolean,
    val wordCount: Int = 0,
    val wordLength: Int = 0
)

fun ModelJoke.toUiModel(isLocal: Boolean,wordCount: Int,wordLength: Int): UiJokeModel {
    return UiJokeModel(
        id = this.id,
        joke = this.joke,
        isLocal = isLocal,
        wordCount = wordCount,
        wordLength = wordLength
    )
}