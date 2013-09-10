package com.github.bballant.docintel.web

import org.scalatra._
import com.github.bballant.docintel.db.QuestionsDb
import com.github.bballant.docintel.io.QuestionsIO
import org.slf4j.LoggerFactory

class DocintelWebServlet extends DocintelWebStack {

  val logger = LoggerFactory.getLogger(getClass)

  val questionsDb = new QuestionsDb(new QuestionsIO)

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  get("/foobar") {
    contentType="text/html"

    logger.info("getting questions")
    ssp("index", "questions" -> questionsDb.getQuestions)
  }
}
