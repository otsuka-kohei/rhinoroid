package com.github.otsuka_kohei.rhinoroid

import android.content.Context
import org.mozilla.javascript.Scriptable
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.io.SequenceInputStream
import java.util.*


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

    private val jsInputStreamList = ArrayList<InputStream>()

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
        val inputStream: InputStream = context.assets.open(jsFilePathInAssetFolder)
        jsInputStreamList.add(inputStream)
    }


    /**
     * Evaluate given JavaScript code.
     * If you call import() method before, evaluation can refer imported JavaScript (.js) file.
     *
     * @param js JavaScript code which you want to evaluate.
     * @return Result of evaluation.
     */
    fun evaluate(js: String): Any {
        val jsInputStream = ByteArrayInputStream(js.toByteArray())
        jsInputStreamList.add(jsInputStream)
        val concatenatedImportedJsInputStream =
            SequenceInputStream(Collections.enumeration(jsInputStreamList))
        val inputStreamReader = InputStreamReader(concatenatedImportedJsInputStream)
        return rhinoContext.evaluateReader(scriptable, inputStreamReader, "js", 0, null)
    }
}