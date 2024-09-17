plugins {
    id("java")
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.2.3"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    kotlin("plugin.serialization") version "2.0.20"
}

group = "dev.nikomaru"
version = "1.0-SNAPSHOT"

fun captureVersion(dependency: Dependency): String {
    return dependency.version ?: throw IllegalArgumentException("Version not found for $dependency")
}


repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://jitpack.io")
    maven("https://plugins.gradle.org/m2/")
    maven("https://repo.incendo.org/content/repositories/snapshots")
    maven("https://repo.codemc.io/repository/maven-public/")
}


dependencies {
    val paperVersion = "1.21-R0.1-SNAPSHOT"
    val mccoroutineVersion = "2.14.0"
    val lampVersion = "3.1.9"
    val koinVersion = "3.5.3"
    val coroutineVersion = "1.7.3"
    val serializationVersion = "1.6.2"


    compileOnly("io.papermc.paper:paper-api:$paperVersion")

    library(kotlin("stdlib"))

    implementation("com.github.Revxrsal.Lamp:common:$lampVersion")
    implementation("com.github.Revxrsal.Lamp:bukkit:$lampVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

    library("com.github.shynixn.mccoroutine:mccoroutine-bukkit-api:$mccoroutineVersion")
    library("com.github.shynixn.mccoroutine:mccoroutine-bukkit-core:$mccoroutineVersion")

    implementation("io.insert-koin:koin-core:$koinVersion")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "21"
        kotlinOptions.javaParameters = true
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "21"
    }
    build {
        dependsOn(shadowJar)
    }
    runServer {
        minecraftVersion("1.21")
    }
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }
}



bukkit {
    name = "MobBallRemake"
    version = "miencraft_plugin_version"
    website = "https://github.com/morinoparty/Mobball-remake"

    main = "$group.mobballremake.MobBallRemake"

    apiVersion = "1.20"
}