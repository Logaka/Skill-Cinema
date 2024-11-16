package sdu.project.cinemaapp.presentation.ui.movieList

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import sdu.project.cinemaapp.R
import sdu.project.cinemaapp.domain.model.Movie
import sdu.project.cinemaapp.presentation.state.ScreenState
import sdu.project.cinemaapp.presentation.ui.components.MovieItemCard
import sdu.project.cinemaapp.presentation.ui.screens.ErrorScreen
import sdu.project.cinemaapp.presentation.ui.screens.LoaderScreen

@SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition")
@Composable
fun ListMoviesScreen(
    title: String,
    navController: NavController,
    viewModel: ListMoviesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val movies by viewModel.movies.collectAsStateWithLifecycle()

    when (state) {
        is ScreenState.Initial -> {}
        is ScreenState.Loading -> LoaderScreen()
        is ScreenState.Error -> ErrorScreen()
        is ScreenState.Success -> {
            ListMoviesLayout(title, movies) {
                viewModel.event(navController, it)
            }
        }
    }

}


@Composable
fun ListMoviesLayout(
    title: String,
    movies: List<Movie>,
    onEvent: (event: ListMoviesEvent) -> Unit
) {
    Log.d("ListMoviesLayout", "Movies: $movies")
    Column(
        modifier = Modifier
            .padding(26.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {

            IconButton(
                onClick = { onEvent(ListMoviesEvent.OnBackClick) },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.caret_left),
                    contentDescription = null
                )
            }

            Text(
                text = title,
                modifier = Modifier
                    .align(Alignment.Center),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.graphikblack)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF272727),
                    textAlign = TextAlign.Center
                )
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies) { movie ->
                MovieItemCard(movie) {
                    onEvent(ListMoviesEvent.OnItemClick(movie))
                }
            }
        }
    }
}