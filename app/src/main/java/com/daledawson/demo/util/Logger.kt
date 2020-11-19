package com.daledawson.demo.util

import android.util.Log
import com.daledawson.demo.Config

/**
 * 封装原有的日志信息，通过配置控制不同类型的日志输出。
 */
object Logger {
    private val IS_VERBOSE: Boolean = Config.OPEN_LOGGER
    private val IS_DEBUG: Boolean = Config.OPEN_LOGGER
    private val IS_INFO: Boolean = Config.OPEN_LOGGER
    private val IS_WARN: Boolean = Config.OPEN_LOGGER
    private val IS_ERROR: Boolean = Config.OPEN_LOGGER
    private const val PREFIX = ">>>>>"
    fun v(tag: String?, msg: String) {
        if (IS_VERBOSE) {
            Log.v(tag, PREFIX + msg)
        }
    }

    fun d(tag: String?, msg: String) {
        if (IS_DEBUG) {
            Log.d(tag, PREFIX + msg)
        }
    }

    fun i(tag: String?, msg: String) {
        if (IS_INFO) {
            Log.i(tag, PREFIX + msg)
        }
    }

    fun w(tag: String?, msg: String) {
        if (IS_WARN) {
            Log.w(tag, PREFIX + msg)
        }
    }

    fun e(tag: String?, msg: String) {
        if (IS_ERROR) {
            Log.e(tag, PREFIX + msg)
        }
    }

    /**
     * Print log but print 120 characters at most in every line.
     *
     * @param tag
     * @param msg
     */
    fun printLog(tag: String?, msg: String?) {
        if (IS_DEBUG && msg != null) {
            val count = msg.length
            val lineCount = 120
            var i = 0
            while (i < count) {
                if (i + lineCount <= count) {
                    Log.d(tag, msg.substring(i, i + lineCount))
                } else {
                    Log.d(tag, msg.substring(i, count))
                }
                i = i + lineCount
            }
        }
    }

    /**
     * Print log but print 4000 characters at most in every line.
     *
     * @param tag
     * @param msg
     */
    fun printLog2(tag: String?, msg: String?) {
        if (IS_DEBUG && msg != null) {
            val count = msg.length
            val lineCount = 3000
            var i = 0
            while (i < count) {
                if (i + lineCount <= count) {
                    Log.d(tag, msg.substring(i, i + lineCount))
                } else {
                    Log.d(tag, msg.substring(i, count))
                }
                i = i + lineCount
            }
        }
    }
}