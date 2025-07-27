plugins {
    id("java")
    alias(libs.plugins.teavm)
}

group = "dev.dani"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://teavm.org/maven/repository")
}

dependencies {
    implementation(libs.asm)
    implementation(libs.asm.commons)

    compileOnly(libs.teavm.core)
    compileOnly(libs.lombok)

    annotationProcessor(libs.lombok)
}

teavm.wasmGC {
    mainClass = "dev.dani.remapper.Main"
    modularRuntime = true
}