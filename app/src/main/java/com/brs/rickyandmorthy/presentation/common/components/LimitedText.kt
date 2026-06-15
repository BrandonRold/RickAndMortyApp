package com.brs.rickyandmorthy.presentation.common.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun LimitText(
    text : String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    maxLines: Int = 1
    ) {
        Text(
            text = text,
            style = style,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
}
