val builtInCommands = listOf("echo", "exit", "type")

fun main() {
    // list of currently defined commands

    while (true) {
        print("$ ")
        val input = readln() // Wait for user input command

        if (input == "exit 0") {    // exit if user input command is <--
               break
        } else {
            val text = input.split(" ", limit = 2)
            val command = text[0]
            val commandArg = if (text.size > 1) text[1] else ""

            // debugging: println("The $command command is a shell builtin and is defined: ${builtInCommands.contains(commandArg)}")

            if (command == "echo") { // implement echo functionality
                println(echo(commandArg))
            } else if (command == "type") {
                /*if (builtInCommands.contains(commandArg)) {
                    println("$commandArg is a shell builtin")
                } else {
                    println("$commandArg: not found")
                }*/
                println(type(commandArg))
            } else {
                println("$command: not found")
            }
        }
    }
}

fun type(str: String): String {
    return if ( builtInCommands.contains(str)) "$str is a shell builtin" else "$str: not found"
}

fun echo(str: String): String {
    return str
}

