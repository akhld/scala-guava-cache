import java.util.UUID
import java.util.concurrent.Callable

import org.scalatest.FunSuite

class GCacheTest extends FunSuite {

  test("guava cache") {

    val cache = new GCache[String, String](10000, 10)
    assert(cache.get("one", GCacheLoader("one")).startsWith("one"))
    assert(cache.get("one", GCacheLoader("one")).startsWith("one"))
    assert(cache.get("two", GCacheLoader("two")).startsWith("two"))

  }

  case class GCacheLoader(key: String) extends Callable[String] {
    override def call(): String = {
      println("Some expensive operation here")
      s"$key-${UUID.randomUUID().toString}"
    }
  }


}
