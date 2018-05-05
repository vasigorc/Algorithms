import Dependencies._

lazy val core = RootProject(file("../algorithms1_5_12/"))

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ca.vgorcinschi",
      scalaVersion := "2.12.6",
      crossScalaVersions := Seq("2.12.6", "2.12.1"),
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "erdosrenyi",
    libraryDependencies ++= {
      val scalaTestVersion = "3.0.5"
      val scalaCheckVersion = "1.14.0"
      Seq(
        scalaTest % Test,
        "org.scalactic" %% "scalactic" % scalaTestVersion,
        "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
        "org.scalacheck" %% "scalacheck" % scalaCheckVersion % "test"
      )
    }
  ).dependsOn(core)
