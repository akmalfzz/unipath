package com.example.unipath.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.unipath.about.TentangScreen
import com.example.unipath.diagnose.InputuserNameScreen
import com.example.unipath.diagnose.QuestionScreen
import com.example.unipath.history.HistoryScreen
import com.example.unipath.home.HomeScreen
import com.example.unipath.home.HomeScreenItems
import com.example.unipath.jurusan.ListJurusanScreen
import com.example.unipath.jurusan.JurusanViewModel
import com.example.unipath.start.StartScreen

@Composable
fun RootNav(
    navController: NavHostController,
    jurusanViewModel: JurusanViewModel,
    items: HomeScreenItems
){
    val listJurusan by jurusanViewModel.data.collectAsState()
    NavHost(navController = navController, route = Graph.ROOT, startDestination = Graph.START){
        composable(Graph.START){
            StartScreen(navController, items)
        }
        composable(Graph.HOME){
            HomeScreen(navController, items)
        }
        composable(Graph.SCREEN_JURUSAN){
            ListJurusanScreen(listJurusan = listJurusan,navHostController = navController)
        }
        composable(Graph.SCREEN_TENTANG){
            TentangScreen(navController, items)
        }
        composable(Graph.SCREEN_USER_NAME){
            InputuserNameScreen(navController, items)
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
    const val START = "route_start"
    const val HOME = "route_home"
    const val ROOT = "root_graph"
    const val SCREEN_JURUSAN = "root_jurusan"
    const val SCREEN_HISTORY = "root_history"
    const val SCREEN_TENTANG = "root_tentang"
    const val SCREEN_USER_NAME = "root_user_name"
    const val SCREEN_QUESTION = "root_question"
    const val DIAGNOSA_RESULT = "root_diagnosa_result"

}