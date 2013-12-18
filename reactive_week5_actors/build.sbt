name := "My Project"

version := "1.0"

scalaVersion := "2.10.2"


resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq("com.typesafe.akka" % "akka-actor" % "2.0.1",
							   "com.ning" % "async-http-client" % "1.7.19")