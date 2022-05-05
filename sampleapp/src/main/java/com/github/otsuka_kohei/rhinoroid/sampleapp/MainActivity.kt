package com.github.otsuka_kohei.rhinoroid.sampleapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.otsuka_kohei.rhinoroid.Rhinoroid

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rhinoroid = Rhinoroid(this)
        rhinoroid.import("a.js")
        rhinoroid.import("b.js")
        rhinoroid.import("c/c.js")
        val result = rhinoroid.evaluate("funcA() + funcB() + funcC();") as String

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = result
    }
}