import sbt.Keys._
import sbt._

name := "jnc-library"

scalaVersion  := "2.11.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

organization := "net.juniper"

version := "0.8.0-SNAPSHOT"

isSnapshot := true

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

publishTo <<= version { v => {
    val nexus = "http://10.155.87.253:8081/nexus/content/repositories/"
    if (v.endsWith("SNAPSHOT"))
      Some("snapshots" at nexus + "scp-snapshots")
    else
      Some("releases" at nexus + "scp-releases")
  }
}


resolvers += "Local Maven Repository" at Path.userHome.asFile.toURI.toURL + "/mavenrepo/release"

javaSource in Compile := baseDirectory.value / "jnc" / "src"

resourceDirectory in Compile := baseDirectory.value / "jnc" / "resources"

managedResources in Compile += baseDirectory.value / "jnc.py"

managedResources in Compile += baseDirectory.value / "jrc.py"

managedResources in Compile += baseDirectory.value / "jcc.py"

libraryDependencies ++= Seq(
  "ch.ethz.ganymed"                   %  "ganymed-ssh2"           % "262",
  "commons-lang"                      %  "commons-lang"           % "2.4",
  "org.hamcrest"                      %  "hamcrest-core"          % "1.3",
  "com.fasterxml.jackson.core"        %  "jackson-databind"       % "2.4.4"
)

crossPaths := false