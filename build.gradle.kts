plugins {
    id("java")
    // Лінтер: перевіряє стиль коду за правилами з config/checkstyle/checkstyle.xml
    id("checkstyle")
}

group = "org.vibeinc.aislop"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

checkstyle {
    toolVersion = "10.21.4"
    // Будь-яке порушення правил валить збірку
    isIgnoreFailures = false
    maxWarnings = 0
}

tasks.test {
    useJUnitPlatform()
    // Виводити в консоль Jenkins результат кожного тесту
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.vibeinc.aislop.Main"
    }
}
