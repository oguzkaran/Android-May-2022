package org.csystem.app.parser

class FindAlphabeticCountResetParser(source: ISourceReset) : ResetParser(source) {
    override fun parse()
    {
        source.reset()
        var count = 0

        while (true) {
            val ch = source.nextChar()

            if (ch == -1)
                break;

            if (ch.toChar().isLetter())
                ++count
        }

        println("Alphabetic Count:$count")
    }
}