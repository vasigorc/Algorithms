import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ca.vgorcinschi",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "algorithms",
    libraryDependencies ++= {
      val scalaTestVersion = "3.0.5"
      val scalaCheckVersion = "1.14.0"
      val catsVersion = "2.0.0"
      Seq(scalaTest % Test,
        "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
        "org.scalacheck" %% "scalacheck" % scalaCheckVersion % "test",
        "com.storm-enroute" %% "scalameter" % "0.9",
        "com.google.guava" % "guava" % "23.0",
        "org.typelevel" %% "cats-core" % catsVersion
      )
    }
  )
