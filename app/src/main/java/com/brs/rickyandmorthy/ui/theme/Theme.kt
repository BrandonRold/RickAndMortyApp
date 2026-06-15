package com.brs.rickyandmorthy.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DarkColorScheme = darkColorScheme(
    primary = PortalGreen,
    onPrimary = OnPortalGreen,
    primaryContainer = PortalGreenContainer,
    onPrimaryContainer = OnPortalGreenContainer,
    secondary = CyberPink,
    onSecondary = OnCyberPink,
    secondaryContainer = CyberPinkContainer,
    onSecondaryContainer = OnCyberPinkContainer,
    tertiary = IceBlue,
    onTertiary = OnIceBlue,
    tertiaryContainer = IceBlueContainer,
    onTertiaryContainer = OnIceBlueContainer,
    error = DeadRed,
    onError = OnDeadRed,
    errorContainer = DeadRedContainer,
    onErrorContainer = OnDeadRedContainer,
    background = VoidBrown,
    onBackground = OnVoidBrown,
    surface = VoidBrown,
    onSurface = OnVoidBrown,
    surfaceVariant = VoidBrownHighest,
    onSurfaceVariant = OnVoidBrownVariant,
    outline = OutlineColors,
    outlineVariant = OutlineVariantColors,
    surfaceContainerLowest = VoidBrownLowest,
    surfaceContainerLow = VoidBrownLow,
    surfaceContainer = VoidBrownContainer,
    surfaceContainerHigh = VoidBrownHigh,
    surfaceContainerHighest = VoidBrownHighest,
)

private val LightColorScheme = lightColorScheme(
    primary = PortalGreen,
    onPrimary = OnPortalGreen,
    primaryContainer = PortalGreenContainer,
    onPrimaryContainer = OnPortalGreenContainer,
    secondary = CyberPink,
    onSecondary = OnCyberPink,
    secondaryContainer = CyberPinkContainer,
    onSecondaryContainer = OnCyberPinkContainer,

    tertiary = IceBlue,
    onTertiary = OnIceBlue,
    tertiaryContainer = IceBlueContainer,
    onTertiaryContainer = OnIceBlueContainer,
    error = DeadRed,
    onError = OnDeadRed,
    errorContainer = DeadRedContainer,
    onErrorContainer = OnDeadRedContainer,
    background = OnVoidBrown,
    onBackground = VoidBrown,

    surface = OnVoidBrown,
    onSurface = VoidBrown,
    surfaceVariant = OnVoidBrownVariant,
    onSurfaceVariant = VoidBrownHighest,

    outline = OutlineColors,
    outlineVariant = OutlineVariantColors,
    surfaceContainerLowest = IceBlue,
    surfaceContainerLow = IceBlueContainer,
    surfaceContainer = CyberPinkContainer,
    surfaceContainerHigh = PortalGreenContainer,
    surfaceContainerHighest = PortalGreen

)

val AppTypography = Typography(
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,

    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 16.sp,

        )

)

@Composable
fun RickyAndMorthyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

  /*  val titleColor = if (darkTheme) TitleDark else TitleLight
    val bodyColor = if (darkTheme) BodyDark else BodyLight

    val typography = AppTypography.copy(
        titleLarge = AppTypography.titleLarge.copy(color = titleColor),
        titleMedium = AppTypography.titleMedium.copy(color = titleColor),
        bodyLarge = AppTypography.bodyLarge.copy(color = bodyColor),
        bodyMedium = AppTypography.bodyMedium.copy(color = bodyColor),
        bodySmall = AppTypography.bodySmall.copy(color = bodyColor)
    )*/

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}