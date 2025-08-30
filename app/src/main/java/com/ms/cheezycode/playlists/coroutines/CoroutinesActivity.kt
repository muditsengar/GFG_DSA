package com.ms.cheezycode.playlists.coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ms.cheezycode.playlists.coroutines.ui.theme.GFG_DSATheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.SelectClause1
import kotlin.concurrent.thread

class CoroutinesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GFG_DSATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

fun runTaskOnThread() {

    CoroutineScope(Dispatchers.Main).launch { }

    runBlocking {
        val l: Job = launch {

        }

        val a: Deferred<String> = async {
            "asdf"
        }
        val deffered: String = a.await()
        val result: String = a.getCompleted()
        val selectClause: SelectClause1<String> = a.onAwait
        val isStarted = a.start()
//        a.

    }

    thread(start = true) {
//        for (0 ... 100000L){
//
//        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            fontSize = 20.sp,
            modifier = modifier.padding(20.dp)
        )
        Button(onClick = {}) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    GFG_DSATheme {
        Greeting2("Android")
    }
}