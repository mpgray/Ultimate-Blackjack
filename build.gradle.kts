plugins {
    application
}

group = "blackjack"
version = "0.4.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "blackjack.Main"
}

tasks.jar {
    archiveBaseName = "ultimate-blackjack"
    manifest {
        attributes(
            "Main-Class" to application.mainClass.get(),
            "Implementation-Title" to "Ultimate Blackjack",
            "Implementation-Version" to project.version,
        )
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
    // The rules tests never open a window; force headless so an accidental
    // Swing call fails loudly in CI instead of hanging or needing a display.
    systemProperty("java.awt.headless", "true")
}
