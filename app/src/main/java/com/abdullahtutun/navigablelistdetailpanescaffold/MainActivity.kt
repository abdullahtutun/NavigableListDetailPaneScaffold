@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.abdullahtutun.navigablelistdetailpanescaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdullahtutun.navigablelistdetailpanescaffold.ui.theme.NavigableListDetailPaneScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigableListDetailPaneScaffoldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    ListDetailLayout(
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}

@Composable
fun ListDetailLayout(modifier: Modifier = Modifier){
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        modifier = modifier,
        navigator = navigator,
        listPane = {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(100) {
                    Text(
                        text = "Item $it",
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .clickable {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail,
                                    content = "Item $it"
                                )
                            }
                            .padding(16.dp)
                    )
                }
            }
        },
        detailPane = {
            val content = navigator.currentDestination?.content?.toString() ?: "Select an item"
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = content)
                Row {
                    AssistChip(
                        onClick = {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Extra,
                                content = "Option 1"
                            )
                        },
                        label = {
                            Text(text = "Option 1")
                        }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    AssistChip(
                        onClick = {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Extra,
                                content = "Option 2"
                            )
                        },
                        label = {
                            Text(text = "Option 2")
                        }
                    )
                }
            }
        },
        extraPane = {
            val content = navigator.currentDestination?.content?.toString() ?: "Select an option"
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = content)
            }
        }
    )

}

