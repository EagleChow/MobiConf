package tekno.choweagle.mobiconf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        enableEdgeToEdge()
        setContent {
            LoginScreen()
        }
    }
}

@Preview
@Composable
fun LoginScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Background Animation
            LottieAnimation(
                composition = rememberLottieComposition(
                    spec = LottieCompositionSpec.Asset("gradient_square.json")
                ).value, modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ).apply {
                LottieAnimation(
                    composition = rememberLottieComposition(
                        spec = LottieCompositionSpec.Asset("logo.json")
                    ).value, modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Title Animation
            LottieAnimation(
                composition = rememberLottieComposition(
                    spec = LottieCompositionSpec.Asset("mobiconf.json")
                ).value, modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(top = 32.dp)
            )
        }
    }
}