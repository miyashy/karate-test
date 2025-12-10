import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.24"
}

java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

sourceSets {
    create("integrationTest") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
    create("endToEndTest")
}
val integrationTestImplementation by configurations.getting {
    extendsFrom(configurations.implementation.get())
    extendsFrom(configurations.testImplementation.get())
}
val endToEndTestImplementation by configurations

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    integrationTestImplementation ("com.intuit.karate:karate-junit5:1.4.1")
    endToEndTestImplementation ("com.intuit.karate:karate-junit5:1.4.1")
}

tasks.test {
    useJUnitPlatform {
        excludeTags("all")
    }
    testLogging {
        events(PASSED,SKIPPED,FAILED,STANDARD_OUT)
    }
}

task<Test>("integrationTest") {
    description = "Runs end to end test."
    group = "verification"

    useJUnitPlatform {
        excludeTags("all")
    }
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    testLogging {
        events(PASSED,SKIPPED,FAILED,STANDARD_OUT)
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
    testLogging {
        events(PASSED,SKIPPED,FAILED,STANDARD_OUT)
    }
}

task<Test>("parallelEndToEndTest") {
    description = "Runs parallel end to end test."
    group = "verification"
    useJUnitPlatform {
        includeTags("all")
    }
    testClassesDirs = sourceSets["endToEndTest"].output.classesDirs
    classpath = sourceSets["endToEndTest"].runtimeClasspath
    testLogging {
        events(PASSED,SKIPPED,FAILED,STANDARD_OUT)
    }
}

tasks.wrapper {
    gradleVersion = "8.8"
}