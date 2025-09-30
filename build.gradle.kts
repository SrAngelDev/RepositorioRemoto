plugins {
    id("java")
}

group = "srangeldev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //Logger
    implementation("ch.qos.logback:logback-classic:1.5.13")
    implementation("org.slf4j:slf4j-api:2.0.12")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    // Cache
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.2")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.12.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.12.0") // Jackson con Retrofit
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.12.0") // RxJava3 con Retrofit

    // Para JSON usaremos Jackson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.19.2")

    // JDBI para la base de datos
    implementation("org.jdbi:jdbi3-core:3.49.5") // Core
    implementation("org.jdbi:jdbi3-sqlobject:3.49.5") // SQL Object para DAO

    // H2 Database
    implementation("com.h2database:h2:2.3.232")

    // Vavr para programación funcional
    implementation("io.vavr:vavr:0.10.4")

    // Mockito para nuestros test con JUnit 5
    testImplementation("org.mockito:mockito-junit-jupiter:5.19.0")
    testImplementation("org.mockito:mockito-core:5.19.0")


    // RxJava3 para programación reactiva
    implementation("io.reactivex.rxjava3:rxjava:3.1.11")

    // Vavr para programación funcional
    implementation("io.vavr:vavr:0.10.4")


    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Mockito para nuestros test con JUnit 5
    testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")
    testImplementation("org.mockito:mockito-core:5.12.0")
    testImplementation("io.projectreactor:reactor-test:3.6.7")

    // Lombok en test
    testCompileOnly("org.projectlombok:lombok:1.18.32")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.32")
}

tasks.test {
    useJUnitPlatform()
}
tasks.jar {
    manifest {
        // Clase principal
        attributes["Main-Class"] = "srangeldev.MainKt"
    }
    // Incluir dependencias
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    // Excluir duplicados
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
