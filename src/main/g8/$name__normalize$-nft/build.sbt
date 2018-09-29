trapExit := false

fork := true
envVars in Test := Map("OTT_LOG_LEVEL" -> "WARN", "WS_CURL_LOG_LEVEL" -> "WARN", "REQUEST_BODY_LOG_LEVEL" -> "WARN", "UMV_LOG_LEVEL" -> "WARN")

libraryDependencies ++= Seq(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.5" % Test,
  "io.gatling" % "gatling-test-framework" % "2.2.5" % Test,
  "com.bskyb.internettv.umv" % "token-generator" % "7.0.0",
  "io.kubernetes" % "client-java" % "2.0.0-beta1",
  specs2 % Test excludeAll ExclusionRule("org.eclipse.jetty")
)

unmanagedResourceDirectories in Compile += baseDirectory.value / s"../$name;format="normalize"$-service/conf"