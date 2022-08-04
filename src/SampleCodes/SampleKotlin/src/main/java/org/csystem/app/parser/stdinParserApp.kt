package org.csystem.app.parser

import org.csystem.kotlin.util.console.readInt

fun runStdIntParserApp()
{
    val count = readInt("Kaç tane karakter okuyacaksınız:")

    val stdInSource = StdInSource(count)

    val parser = FindAlphabeticCountParser(stdInSource)

    parser.parse()
}