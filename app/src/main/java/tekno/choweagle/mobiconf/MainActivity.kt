package tekno.choweagle.mobiconf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.airbnb.lottie.compose.LottieAnimation
import tekno.choweagle.mobiconf.lottie.LottieCompositionLoader

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        (application as MobiConfApplication).let {
            it.mainActivity = this

            if (it.resultActivityCallback != null) onAllLoadedCallback(it.resultActivityCallback!!)
        }
    }

    fun onAllLoadedCallback(success: Boolean) {
        runOnUiThread {
            enableEdgeToEdge()
            setContent {
                // TODO: 第一次启动无动画
                if (success) LottieAnimationScreen()
                else TODO()
            }
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
            .padding(top = screenHeight / 2 - 150.dp),
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
                composition = LottieCompositionLoader.getComposition(R.raw.icon_background)
            )

            // Logo icon
            LottieAnimation(
                modifier = Modifier.size(65.dp),
                composition = LottieCompositionLoader.getComposition(R.raw.logo)
            )
        }

        // App name animation
        LottieAnimation(
            alignment = Alignment.Center,
            modifier = Modifier.size(300.dp),
            composition = LottieCompositionLoader.getComposition(R.raw.mobiconf) // Assuming the text animation is also in the logo file
        )
    }
}