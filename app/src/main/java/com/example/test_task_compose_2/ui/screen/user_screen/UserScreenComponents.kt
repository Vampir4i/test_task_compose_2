package com.example.test_task_compose_2.ui.screen.user_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.test_task_compose_2.R
import com.example.test_task_compose_2.domain.model_ui.UserUi
import com.example.test_task_compose_2.util.VibrateUtil

@Composable
fun UserScreenToolbar(
    label: String,
    toBackScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.primary
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(28.dp)
                .clickable {
                    VibrateUtil.vibrate(VibrateUtil.simpleClickVibrationEffect())
                    toBackScreen()
                },
            contentDescription = "arrow_back"
        )
        Text(
            text = label,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun AvatarCard(
    avatarUrl: String,
    name: String,
    githubUrl: String,
    shape: Shape,
    openWeb: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = shape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = avatarUrl,
                contentDescription = "user_avatar",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.git_logo_black),
                modifier = Modifier
                    .size(196.dp)
                    .padding(8.dp)
                    .clip(
                        RoundedCornerShape(50)
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = githubUrl,
                style = MaterialTheme.typography.h3.copy(
                    color = MaterialTheme.colors.onPrimary
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable {
                    VibrateUtil.vibrate(VibrateUtil.simpleClickVibrationEffect())
                    openWeb(githubUrl)
                }
            )
        }
    }
}

@Composable
fun UserInfoCard(
    location: String,
    email: String,
    twitterUsername: String,
    publicRepos: Int,
    publicGists: Int,
    followers: Int,
    following: Int,
    createdAt: String,
    isBookmarked: Boolean,
    addUserToDb: () -> Unit,
    deleteUserFromDb: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "User info",
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onBackground
            )
            Icon(
                painter = painterResource(
                    id = if (isBookmarked) R.drawable.bookmark_enable else R.drawable.bookmark_disable
                ),
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        if (isBookmarked)
                            VibrateUtil.vibrate(VibrateUtil.deleteFromDbVibrationEffect())
                        else VibrateUtil.vibrate(VibrateUtil.addToDbVibrationEffect())
                        if (isBookmarked) deleteUserFromDb() else addUserToDb()
                    },
                contentDescription = "item_bookmarked"
            )
        }
        if (email.isNotEmpty())
            ItemUserInfo(label = stringResource(id = R.string.email), info = email)
        if (location.isNotEmpty())
            ItemUserInfo(label = stringResource(id = R.string.location), info = location)
        if (twitterUsername.isNotEmpty())
            ItemUserInfo(
                label = stringResource(id = R.string.twitter_username),
                info = twitterUsername
            )
        ItemUserInfo(
            label = stringResource(id = R.string.public_repos),
            info = publicRepos.toString()
        )
        ItemUserInfo(
            label = stringResource(id = R.string.public_gists),
            info = publicGists.toString()
        )
        ItemUserInfo(label = stringResource(id = R.string.followers), info = followers.toString())
        ItemUserInfo(label = stringResource(id = R.string.following), info = following.toString())
        ItemUserInfo(label = stringResource(id = R.string.join_at), info = createdAt)
    }
}

@Composable
fun ItemUserInfo(
    label: String,
    info: String
) {
    Text(
        text = label,
        style = MaterialTheme.typography.h3,
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier.padding(vertical = 4.dp)
    )
    Text(
        text = info,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
fun UserInfoWithOrientation(
    userUi: UserUi,
    isBookmarked: Boolean,
    addUserToDb: () -> Unit,
    deleteUserFromDb: () -> Unit,
    openWeb: (String) -> Unit,
    shape: Shape,
    modifierAvatar: Modifier,
    modifierInfo: Modifier
) {
    AvatarCard(
        avatarUrl = userUi.avatarUrl,
        name = userUi.name,
        githubUrl = userUi.htmlUrl,
        shape = shape,
        openWeb = openWeb,
        modifier = modifierAvatar
    )
    UserInfoCard(
        location = userUi.location,
        email = userUi.email,
        twitterUsername = userUi.twitterUsername,
        publicRepos = userUi.publicRepos,
        publicGists = userUi.publicGists,
        followers = userUi.followers,
        following = userUi.following,
        createdAt = userUi.createdAt,
        isBookmarked = isBookmarked,
        addUserToDb = addUserToDb,
        deleteUserFromDb = deleteUserFromDb,
        modifier = modifierInfo
    )
}