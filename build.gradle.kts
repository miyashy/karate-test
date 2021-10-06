import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
}

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

sourceSets {
    create("endToEndTest")
}
val endToEndTestImplementation by configurations

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springdoc:springdoc-openapi-ui:1.4.8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    endToEndTestImplementation ("com.intuit.karate:karate-junit5:1.1.0")
}

tasks.test {
    useJUnitPlatform {
        excludeTags("all")
    }
}

task<Test>("endToEndTest") {
    description = "Runs end to end test."
    group = "verification"

    useJUnitPlatform {
        excludeTags("all")
    }
    testClassesDirs = sourceSets["endToEndTest"].output.classesDirs
    classpath = sourceSets["endToEndTest"].runtimeClasspath
}

task<Test>("parallelEndToEndTest") {
    description = "Runs parallel end to end test."
    group = "verification"
    useJUnitPlatform {
        includeTags("all")
    }
    testClassesDirs = sourceSets["endToEndTest"].output.classesDirs
    classpath = sourceSets["endToEndTest"].runtimeClasspath
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.wrapper {
    gradleVersion = "7.2"
}