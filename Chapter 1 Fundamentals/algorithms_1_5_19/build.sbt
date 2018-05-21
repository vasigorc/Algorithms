import Dependencies._
import sbt.Keys.mainClass

lazy val seventeen = RootProject(file("../algorithms1_5_17/"))

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ca.vgorcinschi",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "algorithms_1_5_19",
    libraryDependencies += scalaTest % Test,
    mainClass in Compile := Some("ca.vgorcinschi.algorithms1_5_19.RandomGrid")
  ).dependsOn(seventeen)