// The simplest possible sbt build file is just one line:

scalaVersion := "2.12.1"

name := "algorithms1_5_12"
organization := "ch.epfl.scala"
version := "1.0"

libraryDependencies += "org.typelevel" %% "cats" % "0.9.0"

// Here's a quick glimpse of what a multi-project build looks like for this
// build, with only one "subproject" defined, called `root`:

// lazy val root = (project in file(".")).
//   settings(
//     inThisBuild(List(
//       organization := "ch.epfl.scala",
//       scalaVersion := "2.12.1"
//     )),
//     name := "hello-world"
//   )

// To learn more about multi-project builds, head over to the official sbt
// documentation at http://www.scala-sbt.org/documentation.html

