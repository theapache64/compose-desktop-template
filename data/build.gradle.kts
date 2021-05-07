plugins {
    kotlin("jvm")
    kotlin("kapt")
}

group = "com.myapp"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    // Dagger
    val daggerVersion: String by rootProject.extra
    api("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    // Arbor : Like Timber, just different.
    api("com.ToxicBakery.logging:arbor-jvm:1.34.109")
}
