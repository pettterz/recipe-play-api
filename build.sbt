lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-scala-starter-example""",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.6",
    herokuJdkVersion in Compile := "11",
    libraryDependencies ++= Seq(
      guice,
//      "com.h2database" % "h2" % "1.4.199",
//      "org.scalatestplus.play" %% "scalatestplus-play" % "8.0.11" % Test,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,

      //      jdbc,
      "mysql" % "mysql-connector-java" % "5.1.49",
      "com.typesafe.play" %% "play-slick" % "4.0.2",
      "com.typesafe.play" %% "play-slick-evolutions" % "4.0.2"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
