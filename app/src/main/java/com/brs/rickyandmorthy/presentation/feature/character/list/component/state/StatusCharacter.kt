package com.brs.rickyandmorthy.presentation.feature.character.list.component.state

enum class StatusCharacter {
    ALIVE,
    DEAD,
    UNKNOWN;

    companion object {
        fun from(value: String?): StatusCharacter = when (value?.lowercase()) {
            "alive" -> ALIVE
            "dead" -> DEAD
            else -> UNKNOWN
        }
    }
}