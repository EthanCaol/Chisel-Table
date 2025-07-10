scalaVersion := "2.13.16"
libraryDependencies += "org.chipsalliance" %% "chisel" % "6.7.0"
addCompilerPlugin(
  "org.chipsalliance" % "chisel-plugin" % "6.7.0" cross CrossVersion.full
)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-language:reflectiveCalls"
)
