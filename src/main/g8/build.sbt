lazy val repositories = Seq(
  "Sky Releases Mirror" at "https://maven-proxy.tools.cosmic.sky/artifactory/sky-releases/",
  "OVP-Proxy" at "https://nexus.nowtv.bskyb.com/repository/ovp-nexus-release-proxy/",
  JCenterRepository
)

lazy val commonSettings = Seq(
  organization := "com.sky.ott",
  scalaVersion := "2.11.12",
  resolvers ++= repositories,
  version := git.gitDescribedVersion.value.getOrElse("0.1.0-SNAPSHOT").replace(s"${name.value}-", "")
)

lazy val `$name__normalize$` = (project in file("."))
  .settings(commonSettings)
  .aggregate(
    `$name__normalize$-service`,
    `$name__normalize$-test`,
    `$name__normalize$-nft`,
    `$name__normalize$-stubs`
  )

lazy val `$name__normalize$-service` = (project in file("./$name__normalize$-service"))
  .enablePlugins(PlayScala, DockerPlugin, BuildInfoPlugin)
  .settings(commonSettings)

lazy val `$name__normalize$-test` = (project in file("./$name__normalize$-test"))
  .settings(commonSettings)
  .dependsOn(`$name__normalize$-service`, `$name__normalize$-stubs`)

lazy val `$name__normalize$-nft` = (project in file("./$name__normalize$-nft"))
  .enablePlugins(GatlingPlugin)
  .settings(commonSettings)
  .dependsOn(`$name__normalize$-service`, `$name__normalize$-stubs`, `$name__normalize$-test` % "test->test")

lazy val `$name__normalize$-stubs` = (project in file("./$name__normalize$-stubs"))
  .enablePlugins(AshScriptPlugin, DockerPlugin)
  .settings(commonSettings)
