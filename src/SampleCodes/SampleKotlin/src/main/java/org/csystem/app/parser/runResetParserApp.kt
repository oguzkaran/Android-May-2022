package org.csystem.app.parser

import org.csystem.kotlin.util.console.readInt

fun runResetParserApp()
{
    val count = readInt("Bir sayı giriniz:")

    val parser = FindAlphabeticCountResetParser(createRandomSourceResetFactory(10))

    parser.parse();

    for (i in 2..count) {
        parser.source = createRandomSourceResetFactory(10)

        parser.parse()
    }
}