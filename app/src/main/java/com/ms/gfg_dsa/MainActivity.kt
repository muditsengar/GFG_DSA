package com.ms.gfg_dsa

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ms.gfg_dsa.ui.theme.GFG_DSATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GFG_DSATheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    TextModifiers()
                }
            }
        }
    }

    val s: MutableState<Int> = mutableStateOf(0)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.padding(20.dp)

    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GFG_DSATheme {
//        Text(text = "MS")
        Greeting("MS")
    }
}

@Preview(showBackground = true)
@Composable
fun TextModifiers() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "1",
            color = Color.Blue,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,

            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
//                .size(100.dp)
                .background(Color.Red)
                .clickable {
                    Toast
                        .makeText(context, "111", Toast.LENGTH_SHORT)
                        .show()
                }
//            .border(5.dp, Color.Blue)
//            .clip(CircleShape)
//            .background(Color.Yellow)
        )
        Text(
            text = "2",
            fontSize = 20.sp,
            modifier = Modifier
//                .padding(20.dp)
                .background(Color.Yellow)
//                .size(100.dp)
        )
        Text(
            text = "3",
            fontSize = 20.sp,
            modifier = Modifier
//                .padding(20.dp)
                .background(Color.Green)
//                .size(100.dp)
        )
        Text(
            text = "4",
            fontSize = 20.sp,
            modifier = Modifier
//                .padding(20.dp)
                .background(Color.Cyan)
//                .size(100.dp)
        )
    }
}
