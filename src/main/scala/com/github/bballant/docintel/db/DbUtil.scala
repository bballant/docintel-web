package com.github.bballant.docintel.db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

/**
 * Date Created: 9/7/13
 * @author bballantine
 */
object DbUtil {
  Class.forName("org.sqlite.JDBC")

  def getConnection = DriverManager.getConnection("jdbc:sqlite:questions.db")

  def withConnection[A](fn: Connection => A): A = {
    val conn = getConnection
    try {
      fn(conn)
    } finally {
      if (conn != null) conn.close()
    }
  }

  def query[A](q: String)(fn: ResultSet => A): A = {
    withConnection { conn =>
      val statement = conn.createStatement
      try {
        statement.setQueryTimeout(30)
        val rs = statement.executeQuery(q)
        try fn(rs)
        finally if (rs != null) rs.close()
      } finally {
        if (statement != null) statement.close()
      }
    }
  }

  def list[A](q: String)(fn: ResultSet => A): Seq[A] = {
    val result = collection.mutable.ListBuffer[A]()
    query(q) { rs =>
      while(rs.next()) result += fn(rs)
    }
    result.toSeq
  }
}
