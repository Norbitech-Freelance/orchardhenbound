package com.example.orchardhenbound.ui.presentation.game.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.ui.presentation.components.TitleOutlinedText
import com.example.orchardhenbound.ui.presentation.components.BackButton
import com.example.orchardhenbound.ui.presentation.components.FullScreenBackground
import com.example.orchardhenbound.ui.presentation.components.ScorePlate
import com.example.orchardhenbound.utils.extensions.clickableNoRipple

@Composable
fun GameOverOverlay(
    score: Int,
    onPlayAgain: () -> Unit,
    onExit: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Box(modifier = Modifier.fillMaxSize()) {

        FullScreenBackground(
            backgroundRes = R.drawable.bg_pause,
            contentDescription = null
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1.2f))

            Image(
                painter = painterResource(id = R.drawable.img_game_over),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .aspectRatio(1.3f),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.weight(0.3f))

            ScorePlate(
                score = score
            )

            Spacer(modifier = Modifier.weight(0.4f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                BackButton(
                    onClick = onExit,
                )

                Spacer(modifier = Modifier.width(16.dp))

                PlayAgainButton(
                    text = stringResource(R.string.game_play_again),
                    onClick = onPlayAgain,
                    modifier = Modifier.width(screenWidth * 0.53f)
                )
            }

            Spacer(modifier = Modifier.weight(2.2f))
        }
    }
}

@Composable
fun PlayAgainButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .aspectRatio(3.66f)
            .clickableNoRipple { onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.btn_play_again_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        TitleOutlinedText(
            text = text,
            maxFontSize = 28.sp
        )
    }
}

@Preview(showBackground = true, device = "spec:width=412dp,height=892dp,orientation=portrait")
@Composable
fun GameOverOverlayPreview() {
    MaterialTheme {
        GameOverOverlay(
            score = 90000,
            onPlayAgain = {},
            onExit = {}
        )
    }
}