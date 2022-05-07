package com.github.otsuka_kohei.rhinoroid.sampleapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.otsuka_kohei.rhinoroid.Rhinoroid

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result = Rhinoroid.open(this).use {
            it.import("a.js")
            it.import("b.js")
            it.import("c/c.js")
            it.evaluate("funcA() + funcB() + funcC();") as String
        }

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = result
    }
}