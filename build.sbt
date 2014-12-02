organization  := "org.proyectofincarrera"

version       := "0.1"

scalaVersion  := "2.11.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-json"    % "1.3.1",
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2" %% "specs2-core" % "2.3.11" % "test",
    "com.typesafe.slick" %% "slick" % "2.1.0",
    "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
    "mysql" % "mysql-connector-java" % "5.1.12",
    "com.h2database" % "h2" % "1.4.182"
  )
}

Revolver.settings

scoverage.ScoverageSbtPlugin.instrumentSettings

org.scoverage.coveralls.CoverallsPlugin.coverallsSettings
