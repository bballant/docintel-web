package com.github.bballant.docintel.db

import java.sql.{PreparedStatement, Connection, DriverManager, ResultSet}

/**
 * Date Created: 9/7/13
 * @author bballantine
 */
object DbUtil {
  Class.forName("org.sqlite.JDBC")

  def getConnection = DriverManager.getConnection("jdbc:sqlite::resource:questions.db")

  def withConnection[A](fn: Connection => A): A = {
    val conn = getConnection
    try {
      fn(conn)
    } finally {
      if (conn != null) conn.close()
    }
  }

  def query[A](q: String, params: Seq[Any])(fn: ResultSet => A): A = {
    withConnection { conn =>
      var statement: PreparedStatement = null
      try {
        statement = conn.prepareStatement(q)
        params.zipWithIndex.foreach { case(param, i) =>
          val x = i + 1
          param match {
            case n: Int => statement.setInt(x, n)
            case n: Long => statement.setLong(x, n)
            case _ => statement.setString(x, param.toString)
          }
        }
        statement.setQueryTimeout(30)
        val rs = statement.executeQuery
        try fn(rs)
        finally if (rs != null) rs.close()
      } finally {
        if (statement != null) statement.close()
      }
    }
  }

  def list[A](q: String, params: Seq[Any])(fn: ResultSet => A): Seq[A] = {
    val result = collection.mutable.ListBuffer[A]()
    query(q, params) { rs =>
      while(rs.next()) result += fn(rs)
    }
    result.toSeq
  }

  def get[A](q: String, params: Seq[Any])(fn: ResultSet => A): Option[A] =
    list(q, params)(fn).headOption
}
