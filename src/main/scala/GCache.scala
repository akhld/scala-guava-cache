import java.util.concurrent.{Callable, TimeUnit}

import com.google.common.cache.CacheBuilder

class GCache[K, V](size: Int, ttl_minutes: Int) {

  private val cache = CacheBuilder.newBuilder()
    .recordStats()
    .maximumSize(size)
    .expireAfterAccess(ttl_minutes.toLong, TimeUnit.MINUTES)
    .asInstanceOf[CacheBuilder[K, V]]
    .build[K, V]

  def get(key: K, loader: Callable[V]) = cache.get(key, loader)

}


