import sbt._

object Dependencies {
  val kantanGeneric = "com.nrinaudo"      %% "kantan.csv"      % "0.6.1"
  val catsEffect    = "org.typelevel"     %% "cats-effect"     % "3.2.9"
  val scalatest     = "org.scalatestplus" %% "scalacheck-1-14" % "3.1.2.0" % Test
  val kindProjector = "org.typelevel"     %% "kind-projector"  % "0.10.3" cross CrossVersion.binary
}
