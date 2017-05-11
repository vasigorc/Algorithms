enablePlugins(ScalaJSPlugin)

scalaVersion := "2.12.1"

name := "algorithms1_4_34"
version := "1.0"

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.1" % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"

// This is an application with a main method
scalaJSUseMainModuleInitializer := true