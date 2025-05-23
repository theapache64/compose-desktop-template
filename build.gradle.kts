import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
    id("com.google.devtools.ksp")
}

group = "com.myapp"
version = "1.0.0"

repositories {
    mavenCentral()
    google()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

val daggerVersion by extra("2.56.1")

dependencies {
    implementation(compose.desktop.macos_arm64  )

    // Module dependencies
    implementation(project(":data"))

    // Dagger : A fast dependency injector for Android and Java.
    implementation("com.google.dagger:dagger:$daggerVersion")
    ksp("com.google.dagger:dagger-compiler:$daggerVersion")
    kspTest("com.google.dagger:dagger-compiler:$daggerVersion")

    // Cyclone : https://github.com/theapache64/cyclone
    implementation("com.github.theapache64:cyclone:1.0.0-alpha02")

    // Decompose : Decompose
    val voyagerVersion = "1.1.0-beta03"
    // Navigator
    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")

    /**
     * Testing Dependencies
     */
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    // DaggerMock
    testImplementation("com.github.fabioCollini.daggermock:daggermock:0.8.5")
    testImplementation("com.github.fabioCollini.daggermock:daggermock-kotlin:0.8.5")

    // Mockito Core : Mockito mock objects library core API and implementation
    testImplementation("org.mockito:mockito-core:5.17.0")

    // Expekt : An assertion library for Kotlin
    testImplementation("com.github.theapache64:expekt:1.0.0")

    // JUnit

    // Kotlinx Coroutines Test : Coroutines support libraries for Kotlin
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.8.0")
    testImplementation(compose("org.jetbrains.compose.ui:ui-test-junit4"))

    // JUnit : JUnit is a unit testing framework for Java, created by Erich Gamma and Kent Beck.
    testImplementation(kotlin("test-junit5"))
}

compose.desktop {
    application {
        mainClass = "com.myapp.AppKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "myapp"
            packageVersion = "1.0.0"

            val iconsRoot = project.file("src/main/resources/drawables")

            linux {
                iconFile.set(iconsRoot.resolve("launcher_icons/linux.png"))
            }

            windows {
                iconFile.set(iconsRoot.resolve("launcher_icons/windows.ico"))
            }

            macOS {
                iconFile.set(iconsRoot.resolve("launcher_icons/macos.icns"))
            }

        }
    }
}
