package com.brs.rickyandmorthy.presentation.common.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brs.rickyandmorthy.presentation.feature.character.list.component.state.StatusCharacter
import com.brs.rickyandmorthy.ui.theme.GrayStatus
import com.brs.rickyandmorthy.ui.theme.GreenStatus
import com.brs.rickyandmorthy.ui.theme.RedStatus

@Composable
fun StatusIndicator(
    status : StatusCharacter,
    nameStatus : String
    ){
    val  targetColor = when (status){
        StatusCharacter.ALIVE -> GreenStatus
        StatusCharacter.DEAD -> RedStatus
        StatusCharacter.UNKNOWN -> GrayStatus
    }

    val infiniteTransition = rememberInfiniteTransition()

    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val animatedColor by animateColorAsState(targetColor)
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp) ,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(20.dp))
            .padding(2.dp)
            .fillMaxWidth(0.5f)
    ) {
        Box(modifier = Modifier
            .size(16.dp)
            .graphicsLayer() {
                scaleX = pulse
                scaleY = pulse
            }
            .clip(CircleShape)
            .background(animatedColor)
        )
        Text(text =  nameStatus.uppercase() ,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}