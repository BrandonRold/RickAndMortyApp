package com.brs.rickyandmorthy.presentation.feature.character.list.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brs.rickyandmorthy.presentation.common.components.FavoriteIcon
import com.brs.rickyandmorthy.presentation.common.components.LimitText
import com.brs.rickyandmorthy.presentation.common.components.StatusIndicator
import com.brs.rickyandmorthy.presentation.feature.character.list.component.state.StatusCharacter
import com.brs.rickyandmorthy.presentation.feature.character.list.model.CharacterUi
import com.brs.rickyandmorthy.ui.theme.RickyAndMorthyTheme

@Composable
fun CharacterCard(
    character: CharacterUi,
    modifier: Modifier = Modifier
) {
    Card(modifier = Modifier
        .height(180.dp)
        .padding(10.dp),
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme
                .colorScheme.surface)
        ,


    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier
                .weight(0.4f)
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.Gray)){
                Row(Modifier
                    .padding(10.dp)
                    .align(Alignment.TopStart)
                    ) {
                    StatusIndicator(character.status, character.status.name)
                }

            }

            Column(modifier = Modifier
                .weight(0.6f)
                .padding(10.dp)) {
                 Row() {
                     Column(Modifier.weight(3f)){
                         LimitText(
                             text = character.name,
                             style = MaterialTheme.typography.titleLarge
                         )
                         LimitText(
                             text = character.species,
                             style = MaterialTheme.typography.bodyMedium
                         )
                         Spacer(Modifier.padding(1.dp))

                     }

                     Box(Modifier.weight(1f)){
                         FavoriteIcon(modifier = Modifier.align(Alignment.TopEnd))
                     }

                 }
                 Column() {
                     HorizontalDivider(
                         modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                         thickness = 1.dp,
                     )
                     LimitText(
                         text = "ORIGEN",
                         style = MaterialTheme.typography.bodySmall
                     )
                     LimitText(
                         text = character.origin,
                         style = MaterialTheme.typography.bodyMedium
                     )
                     Spacer(Modifier.padding(5.dp))
                     LimitText(
                         text = "LAST SEEN",
                         style = MaterialTheme.typography.bodySmall
                     )
                     LimitText(
                         text = character.lastSeen,
                         style = MaterialTheme.typography.bodyMedium
                     )

                 }
            }

        }
    }
}
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun CharacterCardPreview(){
    RickyAndMorthyTheme() {

    }
}