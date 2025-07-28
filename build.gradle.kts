plugins {
    id("java")
    alias(libs.plugins.teavm)
    alias(libs.plugins.node)
}

group = "dev.dani"
version = "1.0.5"

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

node {
    version.set("20.11.1")
    download.set(true)
    workDir.set(layout.buildDirectory.file("node").get().asFile)
    npmWorkDir.set(layout.buildDirectory.file("npm").get().asFile)
    nodeProjectDir.set(file("src/package"))
}

tasks {
    register<Exec>("npmInstallTypescript") {
        dependsOn("npmInstall")
        workingDir = file("src/package")
        commandLine("npm", "install", "-g", "typescript")
    }

    register<Exec>("compileTypescript") {
        dependsOn("npmInstallTypescript")
        workingDir = file("src/package")
        commandLine("npx", "tsc", "-p", "tsconfig.json")
    }

    register<Copy>("copyDist") {
        group = "build"
        delete("dist")
        dependsOn(generateWasmGC)
        dependsOn("compileTypescript")

        from(
            "README.md", "LICENSE",
            layout.buildDirectory.file("generated/teavm/wasm-gc/java-remapper.wasm"),
            "wasm-runtime/runtime.js", "wasm-runtime/wasm-imports-parser.js"
        )
        into("dist")

        doLast {
            file("dist/package.json").writeText(
                """
                    {
                      "name": "java-remapper",
                      "version": "${project.version}",
                      "description": "A JavaScript tool for remapping Java classes.",
                      "main": "index.js",
                      "types": "index.d.ts",
                      "keywords": [
                        "remapper",
                        "java",
                        "bytecode"
                      ],
                      "author": "Dani-error",
                      "license": "MIT"
                    }
                """.trimIndent()
            )
        }
    }

    build {
        dependsOn("copyDist")
    }
}