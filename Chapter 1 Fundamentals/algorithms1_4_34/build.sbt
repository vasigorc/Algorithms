enablePlugins(ScalaJSPlugin)

scalaVersion := "2.12.1"

name := "algorithms1_4_34"
version := "1.0"

libraryDependencies ++= Seq("org.scalatest" % "scalatest_2.12" % "3.0.1" % "test",
			"org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
             "org.scala-js" % "scalajs-dom_sjs0.6_2.12" % "0.9.1",
             "be.doeraene" %%% "scalajs-jquery" % "0.9.1")

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

skip in packageJSDependencies := false
jsDependencies +=
  "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"
jsDependencies += RuntimeDOM