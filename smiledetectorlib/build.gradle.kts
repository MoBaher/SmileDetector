plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")

}

android {
    namespace = "com.bahercoding.smiledetectorlib"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.camera.core)
    implementation(libs.play.services.mlkit.face.detection)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.play.services.vision.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    val kotlin_version = "1.8.0"
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")

}
//publishing {
//    publications {
//        register<MavenPublication>("release") {
//            groupId = "com.github.MoBaher"
//            artifactId = project.archivesName.get()
//            version = project.version.toString()
//            pom.packaging = "aar"
//            artifact("$buildDir/outputs/aar/smiledetectorlib-release.aar")
//            afterEvaluate {
//                from(components["release"])
//            }
//        }
//    }
//
//    repositories {
//        maven {
//            url = uri("https://jitpack.io")
//        }
//    }
//}