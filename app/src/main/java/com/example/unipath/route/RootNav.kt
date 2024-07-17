package com.example.unipath.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.unipath.diagnose.InputpetNameScreen
import com.example.unipath.diagnose.QuestionScreen
import com.example.unipath.history.HistoryScreen
import com.example.unipath.home.HomeScreen
import com.example.unipath.home.HomeScreenItems
import com.example.unipath.penyakit.ListPenyakitScreen
import com.example.unipath.penyakit.PenyakitViewModel

@Composable
fun RootNav(
    navController: NavHostController,
    penyakitViewModel: PenyakitViewModel,
    items: HomeScreenItems
){
    val listPenyakit by penyakitViewModel.data.collectAsState()
    NavHost(navController = navController, route = Graph.ROOT, startDestination = Graph.HOME){
        composable(Graph.HOME){
            HomeScreen(navController, items)
        }
        composable(Graph.SCREEN_PENYAKIT){
            ListPenyakitScreen(listPenyakit = listPenyakit,navHostController = navController)
        }
        composable(Graph.SCREEN_PET_NAME){
            InputpetNameScreen(navController, items)
        }
        composable(Graph.SCREEN_QUESTION){
            QuestionScreen(navController)
        }
        composable(Graph.SCREEN_HISTORY) {
            HistoryScreen(navHostController = navController)
        }
    }

}

object Graph {
    const val HOME = "route_home"
    const val ROOT = "root_graph"
    const val SCREEN_PENYAKIT = "root_penyakit"
    const val SCREEN_HISTORY = "root_history"
    const val SCREEN_PET_NAME = "root_pet_name"
    const val SCREEN_QUESTION = "root_question"
    const val DIAGNOSA_RESULT = "root_diagnosa_result"

}