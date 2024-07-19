package com.example.unipath.jurusan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unipath.ui.theme.Bg
import com.example.unipath.ui.theme.UnipathTheme
import com.example.unipath.ui.theme.Primary
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.unipath.route.Graph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListJurusanScreen(listJurusan: List<Jurusan>, navHostController: NavHostController) {

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
                    IconButton(onClick = { navHostController.navigate(Graph.HOME) }) {
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
                        text = "Deskripsi Jurusan",
                        style = MaterialTheme.typography.headlineMedium.copy(color = Color.Black),
                    )
                })
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg)
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (listJurusan.isEmpty()) {
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                listJurusan.forEach { jurusan ->
                    CardListJurusan(
                        jurusanName = jurusan.jurusan,
                        deskripsi = jurusan.deskripsi
                    )
                }
            }
        }
    }
}

@Composable
fun CardListJurusan(jurusanName: String, deskripsi: String) {
    ElevatedCard(
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.Black,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(BorderStroke(2.dp, Primary), shape = RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = jurusanName, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = deskripsi, style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Preview
@Composable
fun ListJurusanScreenPreview() {
    UnipathTheme {

        val sampleHistory = listOf(
            Jurusan(1, "p1", "Cacingan", "banyak cacing"),
            Jurusan(1, "p1", "Cacingan", "banyak cacing"),
            Jurusan(1, "p1", "Cacingan", "banyak cacing"),
        )
        val mockNavController = rememberNavController()
        ListJurusanScreen(listJurusan = sampleHistory, navHostController = mockNavController)
    }
}