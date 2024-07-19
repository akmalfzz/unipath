package com.example.unipath.about

import android.media.MediaPlayer
import com.example.unipath.home.HomeScreenItems
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.unipath.route.Graph
import com.example.unipath.ui.theme.UnipathTheme
import com.example.unipath.ui.theme.Bg

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TentangScreen(
    navHostController: NavHostController,
    items: HomeScreenItems
) {
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.click) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Bg,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        mediaPlayer.start()
                        navHostController.navigate(Graph.HOME) }) {
                        Icon(
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp),
                            imageVector = Icons.Filled.ArrowBack, contentDescription = "back button"
                        )
                    }
                },
                title = {
                    Text(
                        text = "Tentang Kami",
                        style = MaterialTheme.typography.headlineMedium.copy(color = Color.Black),
                    )
                })
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg)
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(16.dp),
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
                textAlign = TextAlign.Justify,
                text = buildAnnotatedString {
                    append("UniPath adalah aplikasi sistem pakar yang dirancang untuk membantu calon mahasiswa dalam menentukan jurusan kuliah yang paling sesuai dengan minat mereka, khususnya jurusan yang berhubungan dengan komputer. Aplikasi ini dikembangkan dengan tujuan untuk mengurangi kebingungan yang sering dialami oleh calon mahasiswa dalam memilih jurusan yang tepat.")
                    append("\n")
                    append("\n")
                    append("Dalam dunia pendidikan tinggi, banyak jurusan komputer yang memiliki nama dan kurikulum yang hampir mirip, sehingga seringkali membuat calon mahasiswa bingung. UniPath hadir untuk memberikan panduan yang jelas dan terstruktur berdasarkan metode Certainty Factor, membantu pengguna untuk memahami perbedaan antara berbagai jurusan dan menemukan jurusan yang paling cocok dengan minat mereka.")
                    append("\n")
                    append("\n")
                    append("Dengan UniPath, pengguna dapat menjawab serangkaian pertanyaan terkait minat mereka, dan aplikasi akan memberikan rekomendasi jurusan yang paling sesuai berdasarkan analisis yang mendalam. UniPath dirancang untuk menjadi solusi yang mudah digunakan dan efektif dalam membantu calon mahasiswa membuat keputusan yang tepat mengenai pendidikan masa depan.")
                },
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                ),
                modifier = Modifier,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(20.dp))
            
            Text(
                textAlign = TextAlign.Center,
                text = "© UniPath by Akmal Fauzan"
            )
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    UnipathTheme {
        TentangScreen(
            navHostController = rememberNavController(),
            items = HomeScreenItems(image = R.drawable.unipath)
        )
    }
}