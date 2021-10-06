package com.simon.codingexercise

import scala.util.Try

object Main {
  def main(args: Array[String]): Unit = {
    val nSetCache = NSetCache(10, 2, CacheAlgorithm.LRU)

    nSetCache.put(1,1)
    nSetCache.put(1553498932L, 12.4554)
    nSetCache.put("an array", Array(1,2,3,4,5,6,7,8,9))

    println(nSetCache.get(1))
    println(nSetCache.get(1553498932L))
    println(Try(nSetCache.get("an array").asInstanceOf[Array[Int]].mkString(",")).getOrElse(nSetCache.get("an array")))

    println(nSetCache.size)

    nSetCache.clear

    println(nSetCache.get(1))
    println(nSetCache.get(1553498932L))
    println(Try(nSetCache.get("an array").asInstanceOf[Array[Int]].mkString(",")).getOrElse(nSetCache.get("an array")))

    val someType = CacheAlgorithm(1)
  }
}
