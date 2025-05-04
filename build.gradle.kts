plugins {
    id("java-library")
}

group = "io.github.xfnt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.17")

    testImplementation("org.apache.logging.log4j:log4j-core:2.24.3")
    testImplementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.3")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}