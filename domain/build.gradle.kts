plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}
dependencies {
    implementation(libs.inject)
    implementation(libs.kotlinx.coroutines.core)
//    implementation(libs.okhttp)
//    implementation(libs.logging.interceptor)
//    implementation(libs.okhttp.urlconnection)
    implementation(libs.retrofit)
//    implementation(libs.converter.scalars)
    implementation(libs.converter.gson)
//    implementation(libs.kotlinx.serialization.json)
//    implementation(libs.gson)
}