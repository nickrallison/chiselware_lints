ThisBuild / scalaVersion := "2.13.15"
ThisBuild / organization := "tech.rocksavage"
ThisBuild / version      := "0.1.0"

// Enable SemanticDB for all projects in this build
inThisBuild(List(
  semanticdbEnabled := true,                                    // generate .semanticdb files :contentReference[oaicite:0]{index=0}
  semanticdbVersion := scalafixSemanticdb.revision,              // match Scalafix’s expected version :contentReference[oaicite:1]{index=1}
  scalacOptions     += "-Yrangepos"                              // required by semanticdb-scalac :contentReference[oaicite:2]{index=2}
))

lazy val root = (project in file("."))
  .settings(
    name := "chiselware_lints",
    Compile / resourceDirectories += baseDirectory.value / "src/main/resources",
    scalafixDependencies ++= Seq(
      "ch.epfl.scala" %% "scalafix-core" % "0.14.2"
    ),
    // This allows downstream projects to use your rules
    libraryDependencies ++= Seq(
      "ch.epfl.scala" %% "scalafix-core" % "0.14.2" % Provided
    )
  )