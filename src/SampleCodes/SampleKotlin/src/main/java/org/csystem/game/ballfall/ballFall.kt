package org.csystem.game.ballfall

private fun fillSpace(begin: Int, end: Int) : String
{
    val sb = StringBuilder()
    for (i in begin until end)
        sb.append(' ')

    return sb.toString()
}

private fun updateRightFlag(isRight: Boolean, ballIndex: Int, width: Int)
        = if (ballIndex == 0) true else if (ballIndex == width - 1) false else isRight

private fun updateBallIndex(isRight: Boolean, ballIndex: Int) = if (isRight) ballIndex + 1 else ballIndex - 1

private fun fillBall(ballIndex: Int,  end: Int) = fillSpace(0, ballIndex) + '*' + fillSpace(ballIndex + 1, end)

fun runBallFall(width: Int, height: Int) : String
{
    var ballIndex = 0
    var isRight = false
    val sb = StringBuilder()

    for (i in 1..height) {
        sb.append('|').append(fillBall(ballIndex, width))
        if (width != 1) {
            isRight = updateRightFlag(isRight, ballIndex, width)
            ballIndex = updateBallIndex(isRight, ballIndex)
        }
        sb.append("|\r\n")
    }

    return sb.toString()
}