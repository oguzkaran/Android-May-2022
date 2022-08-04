package org.csystem.app.parser

import org.csystem.kotlin.util.console.readString

class StdInSource(count: Int) : ISource {
    private var mCount = count
    override fun nextChar() = if (mCount-- == 0) -1 else readString()[0].code
}