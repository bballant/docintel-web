package com.github.bballant.docintel.io

import scala.io.Source

/**
 * Date Created: 9/7/13
 * @author bballantine
 */
class QuestionsIO {
  def getCode(path: String): String = {
    val is = getClass.getResourceAsStream(path)
    if (is != null) Source.fromInputStream(is).mkString
    else ""
  }
}
