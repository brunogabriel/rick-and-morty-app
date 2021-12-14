buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath(kotlin("gradle-plugin", Versions.kotlin))
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    configureApplication()
}

fun Project.configureApplication() {
    when {
        name == "app" -> configureAppModule()
        Modules.getAll().map { it.removePrefix(":") }.contains(name) -> configureSingleModule()
        else -> return
    }

    with(plugins) {
        apply("kotlin-android")
        apply("kotlin-kapt")
        apply("kotlinx-serialization")
    }

    configure<com.android.build.gradle.BaseExtension> {
        compileSdkVersion(AppConfig.compileSdk)

        defaultConfig {
            minSdk = AppConfig.minSdk
            targetSdk = AppConfig.targetSdk
            versionCode = AppConfig.versionCode
            versionName = AppConfig.versionName
            testInstrumentationRunner = AppConfig.testInstrumentationRunner
        }

        lintOptions {
            isAbortOnError = false
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = "11"
        }

        testOptions {
            with(unitTests) {
                isIncludeAndroidResources = true
                isReturnDefaultValues = true
            }
        }

        sourceSets.getByName("main") {
            java.srcDir("src/main/kotlin")
            java.srcDirs("src/main/kotlin")
        }
        sourceSets.getByName("test") {
            java.srcDir("src/test/kotlin")
            java.srcDir("src/test/kotlin")
        }
    }
}

fun Project.configureAppModule() {
    with(plugins) {
        apply("com.android.application")
    }
    configure<com.android.build.gradle.BaseExtension> {
        defaultConfig {
            applicationId = AppConfig.applicationId
        }
    }
}

fun Project.configureSingleModule() {
    with(plugins) {
        apply("com.android.library")
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}