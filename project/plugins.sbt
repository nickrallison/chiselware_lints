// Soteria plugin
addSbtPlugin("com.leobenkel" % "soteria" % "0.5.1")
// Scalafix plugin
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.14.2")

ThisBuild / evictionErrorLevel := Level.Warn
ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % "always"