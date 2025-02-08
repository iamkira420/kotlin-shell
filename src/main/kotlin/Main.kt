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
fun getPath(command: String): Optional<String> {
    val paths = System.getenv("PATH").split(":")
    val foundPath = paths.firstOrNull() {
        val file = File("$it/$command")
        file.exists() && file.isFile()  // check if file exists AND is not a directory
    }
    return Optional.ofNullable(foundPath)
}

fun type(str: String): String {
    if ( builtInCommands.contains(str)) {
        return "$str is a shell builtin"
    } else if (getPath(str).isPresent && !builtInCommands.contains(str)) {
        return "$str is ${getPath(str).get()}/$str"
    } else {
        return "$str: not found"
    }
}

fun echo(str: String): String {
    return str
}

