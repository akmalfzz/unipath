package com.example.unipath.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.unipath.R
import com.example.unipath.route.Graph
import com.example.unipath.ui.theme.CatcareexpertsystemTheme
import com.example.unipath.ui.theme.Bg

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    items: HomeScreenItems
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 30.dp),
            painter = painterResource(items.image),
            contentDescription = "unipath",
        )

        Button(
            onClick = { navHostController.navigate(Graph.SCREEN_PET_NAME) },
            colors = ButtonDefaults.buttonColors(Bg),
            border = BorderStroke(2.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            Text(text = "Mulai Tes", color = Color.Black)
        }

        Button(
            onClick = { navHostController.navigate(Graph.SCREEN_PENYAKIT) },
            colors = ButtonDefaults.buttonColors(Bg),
            border = BorderStroke(2.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            Text(text = "Deskripsi Jurusan", color = Color.Black)
        }

        Button(
            onClick = { navHostController.navigate(Graph.SCREEN_HISTORY) },
            colors = ButtonDefaults.buttonColors(Bg),
            border = BorderStroke(2.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            Text(text = "Riwayat Tes", color = Color.Black)
        }

        Button(
            onClick = { navHostController.navigate(Graph.SCREEN_HISTORY) },
            colors = ButtonDefaults.buttonColors(Bg),
            border = BorderStroke(2.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Tentang Kami", color = Color.Black)
        }
    }
}


@Preview
@Composable
private fun HomePreview() {
    CatcareexpertsystemTheme {
        HomeScreen(
            navHostController = rememberNavController(),
            items = HomeScreenItems(image = R.drawable.unipath)
        )
    }
}