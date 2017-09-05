name := "play-eureka"
organization  := "com.jiqid.eureka"
version := "1.0"

scalaVersion := "2.11.11"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies += "com.netflix.eureka" % "eureka-client" % "1.7.0"
