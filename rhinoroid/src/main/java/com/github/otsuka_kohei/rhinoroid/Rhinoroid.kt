package com.github.otsuka_kohei.rhinoroid

import android.content.Context
import android.util.Log
import org.mozilla.javascript.Scriptable


/**
 * Evaluate JavaScript on Android.
 * This class supports importing JavaScript (.js) file from asset folder.
 * Import method works like HTML tag <script src="hoge.js"></script>.
 *
 * @property context
 */
class Rhinoroid(private val context: Context) {

    private val rhinoContext: org.mozilla.javascript.Context =
        org.mozilla.javascript.Context.enter()
    private val scriptable: Scriptable = rhinoContext.initStandardObjects()
    private val jsList = ArrayList<String>()

    init {
        rhinoContext.optimizationLevel = -1
    }


    /**
     * Import JavaScript (.js) file from asset folder.
     * This method works like HTML tag <script src="hoge.js"></script>.
     * If import JavaScript (.js) file from .../assets/hoge/fuga.js, pass a string "hoge/fuga.js".
     *
     * @param jsFilePathInAssetFolder Path of JavaScript (.js) file in asset folder.
     */
    fun import(jsFilePathInAssetFolder: String) {
        val stringBuilder = StringBuilder()
        context.assets.open(jsFilePathInAssetFolder).bufferedReader().useLines { stringSequence ->
            stringSequence.forEach {
                stringBuilder.append(it)
                stringBuilder.append("\n")
            }
        }
        jsList.add(stringBuilder.toString())
    }


    /**
     * Evaluate given JavaScript code.
     * If you call import() method before, evaluation can refer imported JavaScript (.js) file.
     *
     * @param js JavaScript code which you want to evaluate.
     * @return Result of evaluation.
     */
    fun evaluate(js: String): Any {
        jsList.add(js)
        val stringBuilder = StringBuilder()
        jsList.forEach {
            stringBuilder.append(it)
            stringBuilder.append("\n")
        }
        val concatenatedJs = stringBuilder.toString()
        return rhinoContext.evaluateString(scriptable, concatenatedJs, "js", 0, null)
    }
}