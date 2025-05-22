package tekno.choweagle.mobiconf

import android.app.Application
import tekno.choweagle.mobiconf.lottie.LottieCompositionLoader

class MobiConfApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        LottieCompositionLoader.load(this, R.raw.icon_background, R.raw.logo, R.raw.mobiconf)
    }
}