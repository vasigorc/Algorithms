addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.1.0")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.16")

resolvers += Resolver.url(
    "bintray-sbt-plugin-releases",
    url("https://dl.bintray.com/content/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
    