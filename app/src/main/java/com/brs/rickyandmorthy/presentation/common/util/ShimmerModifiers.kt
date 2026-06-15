package com.brs.rickyandmorthy.presentation.common.util

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.shimmerEffect(): Modifier = composed {
    // 1. Configurar la transición infinita
    val transition = rememberInfiniteTransition(label = "shimmer")

    // 2. Definir la animación del eje X (de izquierda a derecha)
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f, // Ajusta este valor según el ancho de tu componente
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200, // Duración de un ciclo de brillo
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_translation"
    )

    // 3. Definir los colores del degradado (Gris claro -> Blanco/Brillo -> Gris claro)
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    // 4. Crear el degradado lineal que se desplaza con la animación
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim, y = translateAnim)
    )

    // 5. Aplicar el fondo con el degradado
    this.background(brush)
}