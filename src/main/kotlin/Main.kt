fun main() {
    // list of currently defined commands
    val builtInCommands = listOf("echo", "exit 0", "type")

    while (true) {
        print("$ ")
        val input = readln() // Wait for user input command
        if (input == "exit 0") {    // exit if user input command is <--
               break
        } else {
            val text = input.split(" ", limit = 2)
            val command = text[0]
            val commandArg = if (text.size > 1) text[1] else ""

            if (command == "echo") { // implement echo functionality
                println(commandArg)
            } else {
                println("$input: command not found")
            }

            if (command == "type") {
                if (builtInCommands.contains(commandArg)) {
                    println("$commandArg is a shell builtin")
                } else {
                    println("$commandArg: not found")
                }
            }
        }
    }
}

