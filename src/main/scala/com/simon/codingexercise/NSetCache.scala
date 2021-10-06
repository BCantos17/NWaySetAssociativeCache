package com.simon.codingexercise

import jdk.jshell.spi.ExecutionControl.NotImplementedException

/**
 *
 * @param numOfSet
 * @param entryPerSet
 * @param algorithm
 * @param cacheArray An Array of tuple representing hashKey, value, timestamp
 */
class NSetCache (
                  val numOfSet: Int,
                  val entryPerSet: Int,
                  val algorithm: CacheAlgorithm.Value,
                  private val cacheArray: Array[(Option[Int], Option[Any], Option[Long])]
                ) extends Cache {

  final def get(key: Any): Any = {
    val hashKey = key.hashCode().abs
    val startIndex = getStartIndex(hashKey)
    val endIndex = getEndIndex(startIndex)

    // Look for value in the set and updates time if found
    for(i <- startIndex to endIndex) {
      if(cacheArray(i)._1.isDefined && cacheArray(i)._1.get == hashKey) {
        cacheArray(i) = (cacheArray(i)._1, cacheArray(i)._2, Some(System.nanoTime()))
        return cacheArray(i)._2.get
      }
    }

    None
  }

  final def put(key: Any, value: Any): Unit = {
    val hashKey = key.hashCode().abs
    val startIndex = getStartIndex(hashKey)
    val endIndex = getEndIndex(startIndex)

    val newEntry = (Some(hashKey), Some(value), Some(System.nanoTime()))

    var indexToInsert = 0
    var hasEmpty = false

    // Looks for empty spaces or matching hashkeys
    for (i <- startIndex to endIndex) {
      if (cacheArray(i)._1.isEmpty && !hasEmpty) {
        indexToInsert = i
        hasEmpty = true
      } else if (cacheArray(i)._1.isDefined && cacheArray(i)._1.get == hashKey) {
        cacheArray(i) = newEntry
        return
      }
    }

    // Insert into empty space else use algorithm to replace value at index
    if(hasEmpty) cacheArray(indexToInsert) = newEntry
    else cacheArray(getIndexToReplace(startIndex, endIndex)) = newEntry
  }

  final def size: Int = numOfSet * entryPerSet

  final def clear: Unit = for(i <- 0 until cacheArray.size) cacheArray(i) = (None, None, None)

  private def getIndexToReplace(startIndex: Int, endIndex: Int): Int ={
    algorithm match {
      case CacheAlgorithm.LRU => getLeastRecentlyUsed(startIndex, endIndex)
      case CacheAlgorithm.MRU => getMostRecentlyUsed(startIndex, endIndex)
      case CacheAlgorithm.CUSTOM => getIndexWithCustomAlgorithm(startIndex, endIndex)
    }
  }

  private def getStartIndex(hashKey: Int): Int = (hashKey % numOfSet) * entryPerSet

  private def getEndIndex(startIndex: Int): Int = startIndex + entryPerSet - 1

  /**
   * Algorithm to get the least recently used index in a given set
   * @param startIndex
   * @param endIndex
   * @return
   */
  private def getLeastRecentlyUsed(startIndex: Int, endIndex: Int): Int = {
    var index = startIndex
    var timeStamp = cacheArray(index)._3.get

    for(i <- startIndex to endIndex) {
      if(timeStamp > cacheArray(i)._3.get){
        index = i
        timeStamp = cacheArray(i)._3.get
      }
    }
    index
  }

  /**
   * Algorithm to get the most recently used index in a given set
   * @param startIndex
   * @param endIndex
   * @return
   */
  private def getMostRecentlyUsed(startIndex: Int, endIndex: Int): Int = {
    var index = startIndex
    var timeStamp = cacheArray(index)._3.get

    for(i <- startIndex to endIndex) {
      if(timeStamp < cacheArray(i)._3.get){
        index = i
        timeStamp = cacheArray(i)._3.get
      }
    }
    index
  }

  protected def getIndexWithCustomAlgorithm(startIndex: Int, endIndex: Int): Int = {
    throw new NotImplementedException("Custom algorithm not implemented. Override method and implement to use")
  }

}

object NSetCache {
  def apply(numberSet: Int, numberEntry: Int, algorithm: CacheAlgorithm.Value): NSetCache =
    new NSetCache(
      numberSet,
      numberEntry,
      algorithm,
      Array.fill[(Option[Int], Option[Any], Option[Long])](numberSet * numberEntry)(None, None, None)
    )

}