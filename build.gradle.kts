val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val postgresql_version: String by project
val exposed_version: String by project
val jackson_version: String by project


plugins {
    application
    kotlin("jvm") version "1.6.21"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("commons-codec:commons-codec:1.14")
    //orm
    implementation("org.jetbrains.exposed:exposed-core:0.37.3")
    implementation("org.jetbrains.exposed:exposed-dao:0.37.3")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.37.3")
    implementation("org.jetbrains.exposed:exposed-java-time:0.37.3")
    //db
    implementation("org.postgresql:postgresql:42.3.3")
    implementation("com.zaxxer:HikariCP:5.0.1")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    //jackson
    //implementation ( "com.fasterxml.jackson.module:jackson-module-kotlin:$jackson_version")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktor_version")
}