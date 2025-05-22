package tekno.choweagle.mobiconf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        enableEdgeToEdge()
        setContent {
            LottieAnimationScreen()
        }
    }
}

@Preview
@Composable
fun LottieAnimationScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = screenHeight / 3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo container (icon with background)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(150.dp)
        ) {
            // Logo background
            LottieAnimation(
                modifier = Modifier.matchParentSize(),
                animationRes = R.raw.icon_background
            )

            // Logo icon
            LottieAnimation(
                modifier = Modifier.size(65.dp),
                animationRes = R.raw.logo
            )
        }

        // App name animation
        LottieAnimation(
            modifier = Modifier
                .size(300.dp)
                .offset(y = (-120).dp),
            animationRes = R.raw.mobiconf // Assuming the text animation is also in the logo file
        )
    }
}

@Composable
fun LottieAnimation(
    modifier: Modifier,
    animationRes: Int
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationRes))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}