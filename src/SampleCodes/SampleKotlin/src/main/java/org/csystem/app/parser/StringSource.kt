package org.csystem.app.parser

class StringSource (str: String) : ISourceReset {
    private val mStr = str
    private var mIndex = 0

    override fun nextChar() = if (mIndex == mStr.length) -1 else mStr[mIndex++].code

    override fun reset()
    {
        mIndex = 0
    }
}