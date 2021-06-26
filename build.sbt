val jacksonDatabindVersion = "2.12.3"
val grpcVersion = "1.37.0"
lazy val root =
  project
    .in(file("."))
    .settings(
      organization := "io.github.mavenrain",
      name := "websocket-version",
      version := "0.1.0-SNAPSHOT",
      versionScheme := Some("early-semver"),
      scalaVersion := "3.0.0",
      // todo remove when fixed: https://github.com/lampepfl/dotty/issues/11943
      Compile / doc / sources := Seq(),
      libraryDependencies ++= Seq(
        "com.google.protobuf" % "protobuf-java-util" % "3.17.3",
        "com.fasterxml.jackson.core" % "jackson-databind" % jacksonDatabindVersion,
        ("com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonDatabindVersion)
          .cross(CrossVersion.for3Use2_13),
        "com.hubspot.jackson" % "jackson-datatype-protobuf" % "0.9.12",
        "dev.zio" %% "zio" % "1.0.9",
        "io.d11" %% "zhttp" % "1.0.0.0-RC17",
        "dev.zio" %% "zio-interop-reactivestreams" % "1.3.5",
        "io.grpc" % "grpc-netty" % grpcVersion,
        "io.grpc" % "grpc-protobuf" % grpcVersion,
        "io.grpc" % "grpc-stub" % grpcVersion,
        "io.r2dbc" % "r2dbc-h2" % "0.8.4.RELEASE",
        "org.scalatest" %% "scalatest" % "3.2.9"
      )
    )

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}
dockerExposedPorts += 9000
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)