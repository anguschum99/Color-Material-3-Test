package com.example.bastyle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bastyle.data.Student
import com.example.bastyle.data.students
import com.example.compose.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    StudentApp()
                }
            }
        }
    }
}

@Composable
fun StudentApp() {
    Scaffold(
        topBar = {
            TopBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(students) {
                StudentCard(
                    student = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}


@Composable
fun StudentCard(
    student: Student,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StudentIcon(studentIcon = student.imageResourceId)
                StudentInfo(studentName = student.name, age = student.age)
                Spacer(modifier = Modifier.weight(2f))
                StudentItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded){
                StudentHobby(studentHobby = student.hobby,
                    modifier = Modifier.padding(
                        dimensionResource(id = R.dimen.padding_medium),
                        dimensionResource(id = R.dimen.padding_small),
                        dimensionResource(id = R.dimen.padding_small),
                        dimensionResource(id = R.dimen.padding_medium)
                    )
                )
            }

        }
    }
}

@Composable
fun StudentHobby(
    @StringRes studentHobby:Int,
    modifier:Modifier = Modifier
){
    Column(modifier=modifier) {
        Text(
            text = stringResource(R.string.hobbies),
            style = MaterialTheme.typography.labelMedium
        )
        Text(text = stringResource(studentHobby),
            style = MaterialTheme.typography.bodyLarge
        )
    }

}


@Composable
fun StudentInfo(
    @StringRes studentName: Int,
    age: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = studentName),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = stringResource(R.string.yo, age),
            style = MaterialTheme.typography.bodyLarge
        )

    }
}

@Composable
fun StudentIcon(
    @DrawableRes studentIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = studentIcon),
        contentDescription = null,
        modifier = Modifier
            .size(90.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun StudentItemButton(
    expanded:Boolean,
    onClick:() -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(
        onClick = onClick,
        modifier = modifier) {
        Icon(
            imageVector = if (expanded) {
                Icons.Filled.KeyboardArrowDown
            }
            else
                Icons.Filled.KeyboardArrowUp,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.ba_logo_1_webp),
                contentDescription = null,
                modifier = Modifier
                    .size((200.dp))
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }
    )
}


@Preview
@Composable
fun StudentPreview() {
    AppTheme(darkTheme = true) {
        StudentApp()
    }
}