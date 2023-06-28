package com.jnlee.parsing.presentation

import android.util.Log

object LLog {
    private val TAG = "JN_LOG"

    private fun buildLogMsg(): String {
        val ste = Thread.currentThread().stackTrace[4]
        val sb = StringBuilder()
        sb.append("[")
        sb.append(ste.fileName)
        sb.append(" > ")
        sb.append(ste.methodName)
        sb.append(" #")
        sb.append(ste.lineNumber)
        sb.append("]")
        return sb.toString()
    }

    fun debug() {
        Log.d(TAG, buildLogMsg())
    }

    fun debug(d: Any) {
        val trace = Throwable().fillInStackTrace().stackTrace
        Log.d(TAG, "${buildLogMsg()} :: ${d.toString()}")
    }
}