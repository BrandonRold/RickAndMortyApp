package com.brs.rickyandmorthy.presentation.common.components.topbar

data class TopBarState(
    val title: String = "",
    val subtitle: String = "",
    val showBackButton: Boolean = false,
    val showMenuButton: Boolean = false,
    val onBackClick: () -> Unit = {},
    val onMenuClick: () -> Unit = {}
)
