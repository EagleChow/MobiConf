package tekno.choweagle.mobiconf.lottie

import android.content.Context
import androidx.annotation.RawRes
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap

object LottieCompositionLoader {
    private val cache = ConcurrentHashMap<Int, LottieComposition>() // Key: R.raw.xxx
    private val scope = CoroutineScope(Dispatchers.IO)

    /**
     * 加载一个或多个 Lottie 动画（自动缓存）
     * @param context Context
     * @param resIds 要加载的资源 ID (R.raw.xxx)
     */
    fun load(context: Context, @RawRes vararg resIds: Int) {
        resIds.forEach { resId ->
            scope.launch {
                LottieCompositionFactory.fromRawRes(context, resId)
                    .addListener { composition -> cache[resId] = composition }
            }
        }
    }

    /** 获取已缓存的 Composition（若无则返回 null） */
    fun get(resId: Int): LottieComposition? = cache[resId]

    /** 清除缓存 */
    fun clear() = cache.clear()
}