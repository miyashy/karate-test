import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.18"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
}

java.sourceCompatibility = JavaVersion.VERSION_11

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
    implementation("org.springdoc:springdoc-openapi-ui:1.5.13")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    integrationTestImplementation ("com.intuit.karate:karate-junit5:1.1.0")
    endToEndTestImplementation ("com.intuit.karate:karate-junit5:1.1.0")
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

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.wrapper {
    gradleVersion = "7.2"
}