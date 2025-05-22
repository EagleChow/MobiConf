package tekno.choweagle.mobiconf

import android.app.Application
import tekno.choweagle.mobiconf.lottie.LottieCompositionLoader

class MobiConfApplication : Application() {

    var mainActivity: MainActivity? = null

    override fun onCreate() {
        super.onCreate()

        LottieCompositionLoader.loadCompositions(
            this,
            R.raw.icon_background,
            R.raw.logo,
            R.raw.mobiconf
        ) { success -> mainActivity?.onAllLoadedCallback(success) }
        // TODO: Lottie加载完但是生命周期没加载完
    }
}