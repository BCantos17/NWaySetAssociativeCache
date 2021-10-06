package com.simon.codingexercise

import org.scalatest.PrivateMethodTester
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class NSetCacheTest extends AnyFunSuite with Matchers {
  test("Init NSetCache") {
    val nSetCache = NSetCache(10, 2, CacheAlgorithm.LRU)

    nSetCache.numOfSet should be (10)
    nSetCache.entryPerSet should be (2)
    nSetCache.algorithm shouldEqual CacheAlgorithm.LRU
  }

  test("NSetCache should put key and value correctly") {
    val nSetCache = NSetCache(10, 2, CacheAlgorithm.MRU)
    nSetCache.put("some key", "some value")
    nSetCache.put("some key", "some value")
    nSetCache.put("some key4", "some value4")
    nSetCache.put("some key19", "some value19")
  }

  test("NSetCache should get value correctly") {
    val nSetCache = NSetCache(10, 2, CacheAlgorithm.LRU)
    nSetCache.put("some key", "some value")
    nSetCache.put("some key", "some value")
    nSetCache.put("some key4", "some value4")
    nSetCache.put("some key19", "some value19")

    val result1 = nSetCache.get("some key4")
    val result2 = nSetCache.get("some key19")
    val result3 = nSetCache.get("some key")

    result1 shouldEqual "some value4"
    result2 shouldEqual "some value19"
    result3 shouldEqual None
  }

  test("NSetCache should get size correctly") {
    val nSetCache = NSetCache(10, 2, CacheAlgorithm.LRU)
    val result = nSetCache.size

    result should be (20)
  }

  test("NSetCache should clear cache correctly") {
    val nSetCache = NSetCache(10, 2, CacheAlgorithm.LRU)
    nSetCache.put("some key", "some value")
    nSetCache.put("some key4", "some value4")
    nSetCache.put("some key19", "some value19")
    nSetCache.clear

    val result1 = nSetCache.get("some key4")
    val result2 = nSetCache.get("some key19")
    val result3 = nSetCache.get("some key")

    result1 shouldEqual None
    result2 shouldEqual None
    result3 shouldEqual None
  }
}
