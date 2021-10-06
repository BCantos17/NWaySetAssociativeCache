package com.simon

package object codingexercise {
  object CacheAlgorithm extends Enumeration {
    type CacheAlgorithm = Value
    val LRU, MRU, CUSTOM = Value
  }
}
