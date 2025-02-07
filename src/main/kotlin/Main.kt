fun main() {
    // Uncomment this block to pass the first stage

    while (true) {
        print("$ ")
        val input = readln() // Wait for user input command
        if (input == "exit 0") {    // exit if user input command is <--
               break
        } else {
            val text = input.split(" ", limit = 2)
            val command = text[0]
            val commandArgs = text[1]

            if (command == "echo") { // implement echo functionality
                println(commandArgs)
            } else {
                println("$input: command not found")
            }
        }
    }
}

