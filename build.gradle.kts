plugins {
    kotlin("multiplatform") version "1.6.0"
    id("com.android.application")
    id("kotlin-android-extensions")
}

group = "me.andrewreed"
version = "1.0-SNAPSHOT"

val ktor_version = "1.6.6"
val coroutines_version = "1.5.2-native-mt"
val serialization_version = "1.3.0"

repositories {
    google()
    mavenCentral()
}

kotlin {
    iosSimulatorArm64 {
        binaries.framework("library")
    }
    iosArm64("ios") {
        binaries.framework("library")
    }
    android()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-json:$ktor_version")
                implementation("io.ktor:ktor-client-logging:$ktor_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.ktor:ktor-client-mock:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktor_version")
            }
        }
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
        val iosTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
            }
        }
        val iosSimulatorArm64Test by getting
        iosSimulatorArm64Test.dependsOn(iosTest)
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktor_version")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
    }
}

android {
    compileSdk = 30
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 16
        targetSdk = 30
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}