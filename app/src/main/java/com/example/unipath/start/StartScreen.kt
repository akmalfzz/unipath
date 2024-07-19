package com.example.unipath.start

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.unipath.R
import com.example.unipath.home.HomeScreenItems
import com.example.unipath.route.Graph
import com.example.unipath.ui.theme.Bg
import com.example.unipath.ui.theme.Tombol
import com.example.unipath.ui.theme.UnipathTheme

@Composable
fun StartScreen(
    navHostController: NavHostController,
    items: HomeScreenItems
) {
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.button) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            painter = painterResource(items.image),
            contentDescription = "unipath",
        )

        Text(
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                append("Find Your Passion,")
                append("\n")
                append("Shape Your")
                append("\n")
                append("Future.")
            },
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 40.sp,
            ),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 50.dp),
            color = Color.Black,
            )
        
        Button(
            onClick = {
                mediaPlayer.start()
                navHostController.navigate(Graph.HOME) },
            colors = ButtonDefaults.buttonColors(Tombol),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .padding(horizontal = 30.dp)
        ) {
            Text(text = "Masuk", color = Color.White)
        }
    }
}


@Preview
@Composable
private fun HomePreview() {
    UnipathTheme {
        StartScreen(
            navHostController = rememberNavController(),
            items = HomeScreenItems(image = R.drawable.unipath)
        )
    }
}