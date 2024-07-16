package com.example.unipath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.unipath.home.HomeScreenItems
import com.example.unipath.penyakit.PenyakitViewModel
import com.example.unipath.route.RootNav
import com.example.unipath.ui.theme.CatcareexpertsystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val penyakitViewModel: PenyakitViewModel = ViewModelProvider(this)[PenyakitViewModel::class.java]
        setContent {
            CatcareexpertsystemTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        RootNav(navController = rememberNavController(), penyakitViewModel =  penyakitViewModel, items = HomeScreenItems(image = R.drawable.unipath))
                    }
                }
            }
        }
    }
}
