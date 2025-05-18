package tekno.choweagle.mobiconf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}