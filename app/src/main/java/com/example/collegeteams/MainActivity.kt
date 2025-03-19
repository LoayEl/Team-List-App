package com.example.collegeteams

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collegeteams.ui.theme.CollegeTeamsTheme
import com.example.collegeteams.model.Team
import com.example.collegeteams.R
import com.example.collegeteams.data.Datasource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CollegeTeamsTheme {


            }
        }
    }
}

@Composable
fun TeamsApp() {
    val layoutDirection= LocalLayoutDirection.current
    Surface(
        modifier= Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start=WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end=WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection),

            ),
    ){
        TeamList(
            teamList = Datasource().loadTeams(),
        )
    }
}

@Composable
fun TeamCard(team:Team, modifier:Modifier=Modifier) {
    Card{
        Image(
            painter= painterResource(team.imageResourceId),
            contentDescription= stringResource(team.stringResourceId),
            modifier=Modifier
                .fillMaxWidth()
                .height(194.dp),
            contentScale = ContentScale.Crop
            )
        Text(
            text= LocalContext.current.getString(team.stringResourceId),
            modifier=Modifier.padding(16.dp),
            style=MaterialTheme.typography.headlineSmall
        )
    }
}
@Composable
fun TeamList(teamList:List<Team>, modifier: Modifier=Modifier){
    LazyColumn (modifier=modifier){
        items(teamList){team ->
            TeamCard(
                team=team,
                modifier= Modifier.padding(8.dp)
            )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun TeamCardPreview() {
    val sampleTeam = Datasource().loadTeams().first()

    CollegeTeamsTheme {
            TeamsApp()
        }
    }

