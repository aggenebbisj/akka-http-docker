import sbt._

name := "akka-http-docker"

version := "1.0"

scalaVersion := "2.11.8"

// Versions
lazy val akkaVersion = "2.4.4"

lazy val main = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(DockerPlugin)

// Libraries
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaVersion
)

val gitHeadCommitSha = settingKey[String]("Determines the current git commit SHA")
gitHeadCommitSha := Process("git rev-parse HEAD").lines.head

version in Docker := gitHeadCommitSha.value
dockerRepository := Some("aggenebbisj")
