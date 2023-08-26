plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.mockito:mockito-junit-jupiter:5.5.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(project(":testFixtures"))

    implementation("com.google.guava:guava:31.1-jre")
    implementation("org.springframework.boot:spring-boot-starter:3.1.3")
    implementation(project(":model"))

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("app.game.carmensandiego.App")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
