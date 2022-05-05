package com.github.otsuka_kohei.rhinoroid

import android.content.Context
import org.mozilla.javascript.Scriptable
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.io.SequenceInputStream
import java.util.*


class Rhinoroid(private val context: Context) {

    private val rhinoContext: org.mozilla.javascript.Context =
        org.mozilla.javascript.Context.enter()
    private val scriptable: Scriptable = rhinoContext.initStandardObjects()

    private val jsInputStreamList = ArrayList<InputStream>()

    init {
        rhinoContext.optimizationLevel = -1
    }

    fun import(jsFilePathInAssetFolder: String) {
        val inputStream: InputStream = context.assets.open(jsFilePathInAssetFolder)
        jsInputStreamList.add(inputStream)
    }

    fun evaluate(js: String): Any {
        val jsInputStream = ByteArrayInputStream(js.toByteArray())
        jsInputStreamList.add(jsInputStream)
        val concatenatedImportedJsInputStream =
            SequenceInputStream(Collections.enumeration(jsInputStreamList))
        val inputStreamReader = InputStreamReader(concatenatedImportedJsInputStream)
        return rhinoContext.evaluateReader(scriptable, inputStreamReader, "js", 0, null)
    }
}