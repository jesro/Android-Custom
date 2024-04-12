package com.example.myapplication

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.myapplication.ui.theme.MyApplicationTheme

class ComposePOCActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                WelcomeThemeDecoupledApi()
            }
        }
    }
}

@Preview(name = "Landscape Light", showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 640)
@Preview(name = "Landscape Dark", showSystemUi = true,
    showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.AUTOMOTIVE_1024p, widthDp = 640)
@Preview(name = "Portrait Light", showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true, device = Devices.PIXEL_4)
@Preview(name = "Portrait Dark", showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true, device = Devices.PIXEL_4)
@Composable
fun WelcomeThemeDecoupledApi() {
    Column {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 50.dp, bottom = 100.dp,
                    start = 16.dp, end = 16.dp
                )
                .border(
                    width = 1.dp, color = Color.Transparent,
                    shape = RoundedCornerShape(30.dp)
                )
        ) {
            BoxWithConstraints {
                val constraints = if (LocalContext.current.display?.rotation
                    == Surface.ROTATION_0 || LocalContext.current.display?.rotation
                    == Surface.ROTATION_180
                ) {  // minWidth < 600.dp
                    portrait(margin = 16.dp)
                } else {
                    landscape(margin = 16.dp)
                }
                ConstraintLayout(constraintSet = constraints) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(
                                width = 2.dp,
                                color = Color.Gray, shape = CircleShape
                            )
                            .layoutId("image"),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Person Name", fontWeight = FontWeight.Bold,
                        modifier = Modifier.layoutId("nameText")
                    )
                    Text(text = "India", modifier = Modifier.layoutId("locationText"))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .layoutId("rowStack")
                    ) {
                        Greeting(count = "30", title = "Posts")
                        Greeting(count = "150k", title = "Followers")
                        Greeting(count = "100", title = "Following")
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.layoutId("followButton")
                    ) {
                        Text(text = "Follow user")
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.layoutId("directMessage")
                    ) {
                        Text(text = "Direct Message")
                    }
                }
            }
        }
        Switch(checked = false,
            modifier = Modifier.fillMaxWidth(), onCheckedChange = {

        })
    }
}

//@Preview(name = "Landscape Mode", showSystemUi = true,
//    showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 640)
//@Preview(name = "Portrait Mode", showSystemUi = true,
//    showBackground = true, device = Devices.PIXEL_4)
@Composable
fun WelcomeDecoupledApi() {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
//            .fillMaxSize()
//            .wrapContentSize()
            .fillMaxWidth()
            .padding(
                top = 50.dp, bottom = 100.dp,
                start = 16.dp, end = 16.dp
            )
            .border(
                width = 1.dp, color = Color.Transparent,
                shape = RoundedCornerShape(30.dp)
            )
    ) {
        BoxWithConstraints {
            val constraints = if (minWidth < 600.dp) {
                portrait(margin = 16.dp)
            } else {
                landscape(margin = 16.dp)
            }
            ConstraintLayout(constraintSet = constraints) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = Color.Gray, shape = CircleShape
                        )
                        .layoutId("image"),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Person Name", fontWeight = FontWeight.Bold,
                    modifier = Modifier.layoutId("nameText")
                )
                Text(text = "India", modifier = Modifier.layoutId("locationText"))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("rowStack")
                ) {
                    Greeting(count = "30", title = "Posts")
                    Greeting(count = "150k", title = "Followers")
                    Greeting(count = "100", title = "Following")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.layoutId("followButton")) {
                    Text(text = "Follow user")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.layoutId("directMessage")) {
                    Text(text = "Direct Message")
                }
            }
        }
    }
}

private fun portrait(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val locationText = createRefFor("locationText")
        val rowStack = createRefFor("rowStack")
        val followButton = createRefFor("followButton")
        val directMessage = createRefFor("directMessage")
        val guideline = createGuidelineFromTop(0.1F)
        constrain(image) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(nameText){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(locationText) {
            top.linkTo(nameText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowStack) {
            top.linkTo(locationText.bottom)
        }
        constrain(followButton){
            top.linkTo(rowStack.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(directMessage.start)
            width = Dimension.wrapContent
        }
        constrain(directMessage){
            top.linkTo(rowStack.bottom, margin = margin)
            start.linkTo(followButton.end)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom, margin = margin)
            width = Dimension.wrapContent
        }
    }
}

private fun landscape(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val locationText = createRefFor("locationText")
        val rowStack = createRefFor("rowStack")
        val followButton = createRefFor("followButton")
        val directMessage = createRefFor("directMessage")
        val guideline = createGuidelineFromTop(0.1F)
        constrain(image) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }
        constrain(nameText){
            start.linkTo(image.start)
            end.linkTo(image.end)
            top.linkTo(image.bottom)
        }
        constrain(locationText) {
            top.linkTo(nameText.bottom)
            start.linkTo(nameText.start)
            end.linkTo(nameText.end)
            bottom.linkTo(parent.bottom, margin = margin)
        }
        constrain(rowStack) {
            top.linkTo(image.top)
            start.linkTo(image.end, margin = margin)
            end.linkTo(parent.end)
        }
        constrain(followButton){
            top.linkTo(rowStack.bottom, margin = margin)
            start.linkTo(rowStack.start, margin = margin)
            end.linkTo(directMessage.start)
            bottom.linkTo(locationText.bottom)
            width = Dimension.wrapContent
        }
        constrain(directMessage){
            top.linkTo(followButton.top)
            bottom.linkTo(followButton.bottom)
            start.linkTo(followButton.end)
            end.linkTo(rowStack.end, margin = margin)
            width = Dimension.wrapContent
        }
    }
}

@Composable
fun WelcomeWithConstraint() {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(
                top = 100.dp, bottom = 100.dp,
                start = 16.dp, end = 16.dp
            )
            .border(
                width = 1.dp, color = Color.Transparent,
                shape = RoundedCornerShape(30.dp)
            )
    ) {
        ConstraintLayout {
            val (image, nameText, locationText, rowStack,
                followButton, directMessage) = createRefs()
            val guideline = createGuidelineFromTop(0.1F)
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Gray, shape = CircleShape
                    )
                    .constrainAs(image) {
                        top.linkTo(guideline)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop
            )
            Text(text = "Person Name",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(nameText){
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(text = "India",
                modifier = Modifier.constrainAs(locationText) {
                    top.linkTo(nameText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .constrainAs(rowStack) {
                        top.linkTo(locationText.bottom)
                    }
            ) {
                Greeting(count = "30", title = "Posts")
                Greeting(count = "150k", title = "Followers")
                Greeting(count = "100", title = "Following")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.constrainAs(followButton){
                    top.linkTo(rowStack.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(directMessage.start)
                    width = Dimension.wrapContent
                }) {
                Text(text = "Follow user")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.constrainAs(directMessage){
                    top.linkTo(rowStack.bottom, margin = 16.dp)
                    start.linkTo(followButton.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = Dimension.wrapContent
                }) {
                Text(text = "Direct Message")
            }
        }
    }
}

@Composable
fun WelcomeWithoutConstraint() {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(
                top = 100.dp, bottom = 100.dp, start = 16.dp,
                end = 16.dp
            )
            .border(
                width = 1.dp, color = Color.Transparent,
                shape = RoundedCornerShape(30.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Gray, shape = CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
            Text(text = "Person Name", fontWeight = FontWeight.Bold)
            Text(text = "India")
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Greeting(count = "30", title = "Posts")
                Greeting(count = "150k", title = "Followers")
                Greeting(count = "100", title = "Following")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Follow user")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Direct Message")
                }
            }
        }
    }
}

@Composable
fun Greeting(count: String, title: String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}
