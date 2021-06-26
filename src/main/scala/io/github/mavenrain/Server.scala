package io.github.mavenrain

import zhttp.http.{/, ->, HttpApp, Response, Root}
import zhttp.http.Method.GET
import zhttp.socket.{Socket, WebSocketFrame}
import zio.stream.ZStream

val socket = Socket.collect[WebSocketFrame] {
  case WebSocketFrame.Text(text) => ZStream.succeed(WebSocketFrame.Text(text))
}

val app =
  HttpApp.collect {
    case GET -> Root / "hey" => Response.text("you guys!")
    case GET -> Root / "subscriptions" => Response.socket(socket)
  }