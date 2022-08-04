package org.csystem.app.parser

class CharArraySource(str: String) : ISourceReset {
    private val mChars = str.toCharArray()
    private var mIndex = 0
    override fun nextChar() = if (mIndex == mChars.size) -1 else mChars[mIndex++].code

    override fun reset()
    {
        mIndex = 0
    }
}