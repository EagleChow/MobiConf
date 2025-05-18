package tekno.choweagle.mobiconf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
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

    // Background animation
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Lottie (covers entire screen, cropped if needed)
//        LottieBackground(
//            modifier = Modifier.fillMaxSize(),
//            animationRes = R.raw.gradient_square
//        )

        // Main content column
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = screenHeight / 3),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo container (icon with background)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(300.dp)
            ) {
                // Logo background
                LottieAnimation(
                    modifier = Modifier.matchParentSize(),
                    animationRes = R.raw.gradient_square
                )

                // Logo icon
                LottieAnimation(
                    modifier = Modifier.size(210.dp),
                    animationRes = R.raw.logo
                )
            }

            // App name animation
            LottieAnimation(
                modifier = Modifier.size(300.dp).offset(y = (-150).dp),
                animationRes = R.raw.mobiconf // Assuming the text animation is also in the logo file
            )
        }
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
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

@Composable
fun LottieBackground(
    modifier: Modifier,
    animationRes: Int
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationRes))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier,
        contentScale = ContentScale.Crop // This will crop any parts that extend beyond screen
    )
}