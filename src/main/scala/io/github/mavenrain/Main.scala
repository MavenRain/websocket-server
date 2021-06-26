package io.github.mavenrain

import io.github.mavenrain.removeElem
import io.r2dbc.spi.ConnectionFactory
import scala.util.chaining.scalaUtilChainingOps
import zhttp.service.Server.start
import zio.Runtime.default.unsafeRun
import zio.ZIO.{effect, foreach, never}
import zio.console.putStrLn

@main
def run =
  given numbersDb: ConnectionFactory = "notes".toDatabase.connectionFactory
  (for
    rowsUpdated <- Seq(
      "create table notes (id text, title text, content text);",
      "insert into notes values('fref', 'first', 'tgtgd');",
      "insert into notes values('ufirbef', 'second', 'nbyfuiuref');",
      "insert into notes values('nnnfn', 'third', 'bfywebfjfrh');"
    ).toCommands.execute
    numbers <- "select title from notes".toQuery.results
    _ <- foreach(numbers.map(_.get("title", classOf[String])))(putStrLn(_))
    _ <- putStrLn((("Hi", true, 2).removeElem[Int]._2).toString)
    _ <- effect(start(8080, app))
    _ <- never
  yield ()).pipe(unsafeRun(_))