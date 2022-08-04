package org.csystem.app.parser

class FindWhitespaceCountParser(source: ISource) : Parser(source) {
    override fun parse()
    {
        var count = 0

        while (true) {
            val ch = source.nextChar()

            if (ch == -1)
                break;

            if (ch.toChar().isWhitespace())
                ++count
        }

        println("Whitespace Count:$count")
    }
}