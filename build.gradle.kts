plugins {
    id("java")
    alias(libs.plugins.teavm)
}

group = "dev.dani"
version = "1.0.1"

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

tasks {
    register<Copy>("copyDist") {
        group = "build"
        dependsOn(generateWasmGC)

        from(
            "README.md", "LICENSE", "remapper.js", "remapper.d.ts",
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
                      "main": "remapper.js",
                      "types": "remapper.d.ts",
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