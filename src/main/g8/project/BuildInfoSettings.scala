import com.typesafe.sbt.GitPlugin.autoImport._
import sbt.Keys.{name, sbtVersion, scalaVersion, version}
import sbtbuildinfo.BuildInfoKey.setting
import sbtbuildinfo.BuildInfoPlugin.autoImport._

object BuildInfoSettings {
  lazy val settings = Seq(
    buildInfoKeys := Seq(
      BuildInfoKey.map(name) { case (_, v) => ("appName", v) },
      BuildInfoKey.map(git.gitCurrentBranch) { case (_, v) => ("gitBranch", v) },
      BuildInfoKey.map(version) { case (_, v) => ("appVersion", v) },
      BuildInfoKey("javaVersion", System.getProperty("java.version")),
      scalaVersion,
      sbtVersion,
    ),
    buildInfoPackage := "$organization$.common.admin",
    buildInfoObject := "BuildInfo",
    buildInfoUsePackageAsPath := true,
    buildInfoOptions += BuildInfoOption.ToJson
  )
}
