package com.simon.codingexercise

trait Cache {

  /**
   * get value in a cache given a key
   * @param key
   * @return
   */
  def get(key: Any): Any

  /**
   * put value in a cache given a key
   * @param key
   * @param value
   */
  def put(key: Any, value: Any): Unit

  /**
   * get size of the cache
   * @return
   */
  def size: Int

  /**
   * clear the entire cache of any user inputs
   */
  def clear: Unit
}
