import javax0.jamal.api.Position
import javax0.jamal.engine.Processor
import javax0.jamal.tools.Input

buildscript {
    repositories {
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
    dependencies {
        classpath("com.javax0.jamal:jamal-all:2.4.0")
    }
}

tasks.register("jamal") {
    doLast {
        for (year in 2013..2100) {
            layout.projectDirectory
                .dir("_posts/$year/")
                .asFile
                .walkTopDown()
                .filter { ".*\\.adoc\\.jam".toRegex().containsMatchIn(it.name) }
                .forEach {
                    val output = layout.projectDirectory.file("_posts/${it.name.replace(".jam", "")}").asFile
                    if (output.exists() && (output.lastModified() <= it.lastModified())) {
                        val processor = Processor("{%", "%}")
                        val input = Input.makeInput(it.readText(Charsets.UTF_8), Position(it.absolutePath, 0, 0))
                        println("converting ${it.absolutePath} to ${output.absolutePath}")
                        val result = processor.process(input)
                        output.writeText(result)
                    }
                }
        }
        println("Jamal conversion done")
    }
}

tasks.register("build") {
    dependsOn("jamal")
    doLast {
        exec {
            workingDir = layout.projectDirectory.asFile
            commandLine = listOf("bundle", "exec", "jekyll", "build", "--incremental")
        }

    }
}

tasks.register("add") {
    dependsOn("build")
    doLast {
        exec {
            workingDir = layout.projectDirectory.asFile
            commandLine = listOf("git", "add", "--all", "docs")
        }
    }
}

tasks.register("commit") {
    dependsOn("add")
    doLast {
        exec {
            workingDir = layout.projectDirectory.asFile
            commandLine = listOf("git", "commit", "-m", "update")
        }
    }
}

tasks.register("push") {
    dependsOn("commit")
    doLast {
        exec {
            workingDir = layout.projectDirectory.asFile
            commandLine = listOf("git", "push")
        }
    }
}

tasks.register("run") {
    dependsOn("build")
    doLast {
        exec {
            commandLine = listOf("open", "http://localhost:4000")
        }
        exec {
            workingDir = layout.projectDirectory.asFile
            commandLine = listOf("bundle", "exec", "jekyll", "serve", "--incremental")
        }
    }
}

tasks.register("publish") {
    dependsOn("push")
    doLast {
        exec {
            workingDir = layout.projectDirectory.asFile
            commandLine = listOf("open", "https://javax0.github.io")
        }
        exec {
            workingDir = layout.projectDirectory.asFile
            commandLine = listOf("open", "https://github.com/javax0/javax0.github.io/actions")
        }
        println("All done")
    }
}
