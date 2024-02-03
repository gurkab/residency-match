plugins {
    id("java")
    id("com.diffplug.spotless") version "6.24.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    implementation("org.projectlombok:lombok:1.18.28")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    java {
        target("src/main/java/**/*.java")
        prettier(mapOf("prettier" to "2.8.1", "prettier-plugin-java" to "2.0.0")).config(mapOf("parser" to "java", "tabWidth" to 4, "printWidth" to 140))
        removeUnusedImports()
    }
}
