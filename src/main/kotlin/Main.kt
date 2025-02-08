import java.io.*
import java.util.*

// currently defined commands
val builtInCommands = listOf("echo", "exit", "type")

fun main() {
    while (true) {
        print("$ ")
        val input = readln() // Wait for user input command

        if (input == "exit 0") {
               break
        } else {
            val text = input.split(" ", limit = 2)
            val command = text[0]
            val commandArg = if (text.size > 1) text[1] else ""

            if (command == "echo") {
                println(echo(commandArg))
            } else if (command == "type") {
                println(type(commandArg))
            } else if (!builtInCommands.contains(command)) {
                println(runExtProgram(command, commandArg))
            } else {
                println("$command: not found")
            }
        }
    }
}

/**
 * Look for the given command inside the directories listed in PATH
 * Return the directory (Optional<String>) if found, otherwise Optional.notFound()
 * @param command: command to search for in PATH directories
 */
fun getPath(command: String): String? {
    val paths = System.getenv("PATH").split(":")
    for (path in paths) {
        val file = File(path, command)
        if (file.exists() && file.isFile) {
            return path
        }
    }
    return "$command: not found"
}

/**
 * Run the given external command, provided that it actually exists in the system
 * @param command to be run
 * @param arguments passed to the given command
 * @return the commands output, otherwise a 'command not found' text
 */
fun runExtProgram(command: String, vararg arguments: String): String {
    val cmdPath = getPath(command)

    if (cmdPath != "$command: not found") {
        val process = ProcessBuilder("$command", *arguments).redirectErrorStream(true).start()
        return process.inputStream.bufferedReader().readText().trim()
    } else {
        return "$command: not found"
    }
}

/**
 * Given a command, look for its location using the PATH variable and return
 * @param command name to look
 * @return either the command file path or location, indicates whether the command is built-in, or a 'command_name not found' text
 */
fun type(str: String): String {
    if ( builtInCommands.contains(str)) {
        return "$str is a shell builtin"
    } else if (getPath(str) != "$str: not found" && !builtInCommands.contains(str)) {
        return "$str is ${getPath(str)}/$str"
    } else {
        return "$str: not found"
    }
}

fun echo(str: String): String {
    return str
}

