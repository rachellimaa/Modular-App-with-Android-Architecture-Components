buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath(Depends.Android.gradle)
        classpath(Depends.Android.kotlinGradle)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}