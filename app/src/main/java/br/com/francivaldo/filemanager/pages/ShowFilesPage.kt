package br.com.francivaldo.filemanager.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.francivaldo.filemanager.MainActivity

@Composable
fun ShowFilesPage() {
    val context = LocalContext.current as MainActivity
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(8.dp)
            ) {
                Spacer(modifier = Modifier.size(8.dp))
                IconButton(
                    content = {
                        Icon(
                            painter = painterResource(id = br.com.francivaldo.filemanager.R.drawable.ic_left_menu),
                            contentDescription = "icon left menu"
                        )
                    },
                    onClick = {

                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colors.surface)
                ) {
                    Text(text = context.viewModel.localFile)
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        content = { Icon(painter = painterResource(id = br.com.francivaldo.filemanager.R.drawable.ic_more), contentDescription = "icon more options") },
                        onClick = {

                        }
                    )
                }
            }
            if(context.viewModel.isFileEmpty)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "File empty")
                }
            else
                LazyColumn(
                    modifier = Modifier
                        .background(MaterialTheme.colors.primary)
                        .fillMaxSize()
                ) {
                items(context.viewModel.listUiFiles) {
                    Box(modifier = Modifier
                        .clickable {
                            it.onClik.invoke()
                        }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = it.ImageRes),
                                contentDescription = "image file",
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                            Text(text = it.fileName)
                        }
                    }
                }
            }
        }
    }
}