lazy val repositories = Seq(
  "Sky Releases Mirror" at "https://maven-proxy.tools.cosmic.sky/artifactory/sky-releases/",
  "OVP-Proxy" at "https://nexus.nowtv.bskyb.com/repository/ovp-nexus-release-proxy/",
  JCenterRepository
)

lazy val commonSettings = Seq(
  organization := "com.sky.ott",
  scalaVersion := "2.11.12",
  resolvers ++= repositories,
  version := git.gitDescribedVersion.value.getOrElse("0.1.0-SNAPSHOT").replace(s"\${name.value}-", "")
)

lazy val `$name;format="normalize"$` = (project in file("."))
  .settings(commonSettings)
  .aggregate(
    `$name;format="normalize"$-service`,
    `$name;format="normalize"$-test`,
    `$name;format="normalize"$-nft`,
    `$name;format="normalize"$-stubs`
  )

lazy val `$name;format="normalize"$-service` = (project in file("./$name;format="normalize"$-service"))
  .enablePlugins(PlayScala, DockerPlugin, BuildInfoPlugin)
  .settings(commonSettings)

lazy val `$name;format="normalize"$-test` = (project in file("./$name;format="normalize"$-test"))
  .settings(commonSettings)
  .dependsOn(`$name;format="normalize"$-service`, `$name;format="normalize"$-stubs`)

lazy val `$name;format="normalize"$-nft` = (project in file("./$name;format="normalize"$-nft"))
  .enablePlugins(GatlingPlugin)
  .settings(commonSettings)
  .dependsOn(`$name;format="normalize"$-service`, `$name;format="normalize"$-stubs`, `$name;format="normalize"$-test` % "test->test")

lazy val `$name;format="normalize"$-stubs` = (project in file("./$name;format="normalize"$-stubs"))
  .enablePlugins(AshScriptPlugin, DockerPlugin)
  .settings(commonSettings)
