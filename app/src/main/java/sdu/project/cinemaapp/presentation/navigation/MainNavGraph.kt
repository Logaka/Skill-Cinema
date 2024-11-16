package sdu.project.cinemaapp.presentation.ui.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import sdu.project.cinemaapp.presentation.navigation.BottomBarNavigation
import sdu.project.cinemaapp.presentation.ui.components.BottomBar
import sdu.project.cinemaapp.presentation.ui.screens.ProfileScreen
import sdu.project.cinemaapp.presentation.ui.screens.SearchScreen
import sdu.project.cinemaapp.presentation.ui.home.HomeScreen
import sdu.project.cinemaapp.presentation.ui.details.MovieDetailsScreen
import sdu.project.cinemaapp.presentation.ui.movieList.ListMoviesScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNavigation.Home.route
    ) {

        composable(BottomBarNavigation.Home.route) {
            Scaffold(
                bottomBar = { BottomBar(navController) }
            ) {
                HomeScreen(navController = navController)
            }
        }

        composable("details/{itemId}") { entry ->

            val itemId = entry.arguments?.getString("itemId")?.toIntOrNull()

            itemId?.let {
                MovieDetailsScreen(itemId)
                Log.d("ItemCheck", "Item ID is valid: $itemId")
            }
        }


        composable("list_screen/{title}") { backStackEntry ->

            val title = backStackEntry.arguments?.getString("title")

            title?.let { ListMoviesScreen(title = title, navController) }
        }

        composable(route = BottomBarNavigation.Search.route) {
            Scaffold(
                bottomBar = { BottomBar(navController) }
            ) {
                SearchScreen()
            }
        }
        composable(route = BottomBarNavigation.Profile.route) {
            Scaffold(
                bottomBar = { BottomBar(navController) }
            ) {
                ProfileScreen()
            }
        }
    }
}
/* TODO need to configure navigation and avoid duplicating screens {launchSingleTop = true} */