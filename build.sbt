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
    "io.spray"            %%  "spray-json"    % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2" %% "specs2-core" % "2.3.11" % "test",
    "com.typesafe.slick" %% "slick" % "2.1.0",
    "org.slf4j" % "slf4j-nop" % "1.7.7",
    "org.slf4j" % "slf4j-api" % "1.7.7",
    "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
    "com.typesafe" % "config" % "1.2.1",
    "postgresql" % "postgresql" % "9.1-901.jdbc4",
    "com.h2database" % "h2" % "1.4.182",
    "com.gettyimages" %% "spray-swagger" % "0.5.0",
    "com.github.t3hnar" %% "scala-bcrypt" % "2.4",
    "org.mindrot" % "jbcrypt" % "0.3m"
  )
}

Revolver.settings

