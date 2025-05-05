ThisBuild / scalaVersion := "2.13.15"
ThisBuild / organization := "tech.rocksavage"
ThisBuild / version      := "0.1.0"

lazy val root = (project in file("."))
  .settings(
      name := "chiselware_lints",
      scalafixDependencies ++= Seq(
          "ch.epfl.scala" %% "scalafix-core" % "0.14.2"
      ),
      // This allows downstream projects to use your rules
      libraryDependencies ++= Seq(
          "ch.epfl.scala" %% "scalafix-core" % "0.14.2" % Provided
      )
  )