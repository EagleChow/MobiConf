package tekno.choweagle.mobiconf.lottie

import android.content.Context
import androidx.annotation.RawRes
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

object LottieCompositionLoader {
    // 使用线程安全的ConcurrentHashMap作为缓存
    private val compositionCache = ConcurrentHashMap<Int, LottieComposition>()

    // 使用固定线程池提高加载效率
    private val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    /**
     * 批量加载Lottie资源
     * @param context 上下文
     * @param rawIds 需要加载的raw资源ID数组
     * @param onAllLoaded 所有资源加载完成后的回调
     */
    fun loadCompositions(
        context: Context,
        @RawRes vararg rawIds: Int,
        onAllLoaded: (Boolean) -> Unit
    ) {
        // 过滤掉已经缓存的资源
        val idsToLoad = rawIds.filter { !compositionCache.containsKey(it) }.toIntArray()

        if (idsToLoad.isEmpty()) {
            // 所有资源都已缓存，直接回调
            onAllLoaded(true)
            return
        }

        // 使用CountDownLatch确保所有资源加载完成
        val latch = CountDownLatch(idsToLoad.size)
        var anyFailed = false

        idsToLoad.forEach { rawId ->
            executor.execute {
                try {
                    // 使用fromRawResSync同步加载方法提高速度
                    val result = LottieCompositionFactory.fromRawResSync(context, rawId).value
                    result?.let {
                        compositionCache[rawId] = it
                    } ?: run {
                        anyFailed = true
                    }
                } catch (e: Exception) {
                    anyFailed = true
                } finally {
                    latch.countDown()
                }
            }
        }

        // 等待所有加载完成
        executor.execute {
            try {
                latch.await()
                onAllLoaded(!anyFailed)
            } catch (e: InterruptedException) {
                onAllLoaded(false)
            }
        }
    }

    /**
     * 获取已缓存的composition
     * @param rawId raw资源ID
     * @return 对应的LottieComposition，如果不存在则返回null
     */
    fun getComposition(rawId: Int): LottieComposition? {
        return compositionCache[rawId]
    }

    /**
     * 清除缓存
     */
    // TODO: 什么时候清除缓存
    fun clearCache() {
        compositionCache.clear()
    }
}