package com.github.otsuka_kohei.rhinoroid

import android.content.Context
import org.mozilla.javascript.Scriptable


/**
 * Evaluate JavaScript on Android.
 * This class supports importing JavaScript (.js) file from asset folder.
 * Import method works like HTML tag <script src="hoge.js"></script>.
 *
 * @property context
 */
class Rhinoroid(
    private val context: Context,
    javaScriptVersion: Int = org.mozilla.javascript.Context.VERSION_ES6
) {

    private val rhinoContext: org.mozilla.javascript.Context =
        org.mozilla.javascript.Context.enter()
    private val scriptable: Scriptable = rhinoContext.initStandardObjects()
    private var importCounter = 0

    init {
        rhinoContext.optimizationLevel = -1
        rhinoContext.languageVersion = javaScriptVersion
    }


    /**
     * Import JavaScript (.js) file from asset folder.
     * This method works like HTML tag <script src="hoge.js"></script>.
     * If import JavaScript (.js) file from .../assets/hoge/fuga.js, pass a string "hoge/fuga.js".
     *
     * @param jsFilePathInAssetFolder Path of JavaScript (.js) file in asset folder.
     */
    fun import(jsFilePathInAssetFolder: String) {
        context.assets.open(jsFilePathInAssetFolder).bufferedReader().use {
            rhinoContext.evaluateReader(
                scriptable,
                it,
                "Import${importCounter}($jsFilePathInAssetFolder)",
                0,
                null
            )
        }
        importCounter++
    }


    /**
     * Evaluate given JavaScript code.
     * If you call import() method before, evaluation can refer imported JavaScript (.js) file.
     *
     * @param js JavaScript code which you want to evaluate.
     * @return Result of evaluation.
     */
    fun evaluate(js: String): Any {
        return rhinoContext.evaluateString(
            scriptable,
            js,
            "Evaluated JavaScript Code",
            0,
            null
        )
    }
}