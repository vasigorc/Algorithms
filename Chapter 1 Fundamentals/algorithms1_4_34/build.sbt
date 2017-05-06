// The simplest possible sbt build file is just one line:

scalaVersion := "2.12.1"

// It's possible to define many kinds of settings, such as:

name := "algorithms1_4_34"
version := "1.0"

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.1" % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"