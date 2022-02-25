package com.keecoding.myportfolio

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keecoding.myportfolio.data.Project
import com.keecoding.myportfolio.ui.theme.MyPortfolioTheme
import org.intellij.lang.annotations.JdkConstants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPortfolioTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateCard()
                }
            }
        }
    }
}

@Composable
fun CreateCard() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(400.dp)
                .padding(12.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = Color.White
        ) {
            Column(
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                CreateImageProfile()
                Divider(thickness = 1.dp)
                Text(
                    text = "Riski Ilyas", 
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.app_color),
                    fontFamily = Font(resId = R.font.app_font).toFontFamily(),
                    style = MaterialTheme.typography.h3
                )
                Text(text = "@riskiilyas")
                Text(text = "Android Development Enthusiast | Informatics Student", textAlign = TextAlign.Center)
                Button(onClick = {
                    buttonClickedState.value = !buttonClickedState.value
                },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.app_color))
                ) {
                    Text(text = "Portfolio", color = Color.White)
                }
                if (buttonClickedState.value) {
                    PortfolioContents()
                } else {
                    Box {}
                }
            }


        }
    }
}

@Preview
@Composable
fun PortfolioContents() {
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)
    ) {
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Portfolio(data = listOf(
                Project("Sciroper", R.drawable.sciropericon, "Online Rock Paper Scissors Game"),
                Project("FlagQuizz", R.drawable.flagquizzicon, "Online Flag Quiz Game"),
                Project("News App", R.drawable.newsappicon, "App about International news"),
                Project("Calculator App", R.drawable.calculatorappicon, "Calculator App"),
                ))
        }
    }
}

@Composable
fun Portfolio(data: List<Project>) {
    LazyColumn {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)
                    .padding(16.dp)) {
                    CreateImageProfile(modifier = Modifier.size(100.dp), res = item.res, shape = RoundedCornerShape(4.dp))
                    Column(modifier = Modifier
                        .padding(start = 7.dp)) {
                        Text(text = item.name,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.h6)
                        Text(text = item.desc)
                    }
                }
            }
        }
    }
}

@Composable
fun CreateImageProfile(modifier: Modifier = Modifier, res: Int = R.drawable.my_pic, shape: RoundedCornerShape =  CircleShape) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = shape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = colorResource(id = R.color.app_color)
    ) {
        Image(
            painter = painterResource(id = res), contentDescription = "My Photo", contentScale = ContentScale.Fit
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyPortfolioTheme {
        Column {
            CreateCard()
        }

    }
}