import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ca.vgorcinschi",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "algorithms2_1",
    libraryDependencies ++= {
      val scalaTestVersion = "3.0.5"
      val scalaCheckVersion = "1.14.0"
      Seq(scalaTest % Test,
        "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
        "org.scalacheck" %% "scalacheck" % scalaCheckVersion % "test",
        "com.storm-enroute" %% "scalameter" % "0.9"
      )
    }
  )
