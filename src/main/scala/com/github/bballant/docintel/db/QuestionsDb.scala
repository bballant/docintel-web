package com.github.bballant.docintel.db

import com.github.bballant.docintel.model.Question
import com.github.bballant.docintel.io.QuestionsIO
import org.slf4j.LoggerFactory
import java.sql.ResultSet

/**
 * Date Created: 9/7/13
 * @author bballantine
 */
class QuestionsDb(questionsIo: QuestionsIO) {

  val logger = LoggerFactory.getLogger(getClass)
  logger.info("Questions Db")

  import DbUtil._

  private def rsToQuestion(rs: ResultSet): Question = {
    Question (
      rs.getLong("id"),
      rs.getInt("number"),
      rs.getInt("unit"),
      rs.getString("title"),
      rs.getString("question"),
      rs.getString("explanation"),
      questionsIo.getCode(rs.getString("code_path"))
    )
  }

  def getQuestions: Seq[Question] =
    list("select * from questions where deleted is null", Seq.empty) { rsToQuestion _ }

  def getQuestion(id: Long): Option[Question] =
    get("select * from questions where deleted is null and id = ?", Seq(id)) { rsToQuestion _ }
}
