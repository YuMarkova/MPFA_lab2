package com.example.yulia.lab2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.support.design.widget.Snackbar
import android.text.TextWatcher
import android.text.Editable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button_plus = findViewById<Button>(R.id.button_plus) as Button
        val button_minus = findViewById<Button>(R.id.button_minus) as Button
        val button_mult = findViewById<Button>(R.id.button_multiply) as Button
        val button_dir = findViewById<Button>(R.id.button_divide) as Button
        val edit_text_1 = findViewById<EditText>(R.id.edit_text_var1) as EditText
        val edit_text_2 = findViewById<EditText>(R.id.edit_text_var2) as EditText
        val text_view = findViewById<TextView>(R.id.text_view_result) as TextView

        fun calculate (oper: String) {

            var ok = true

            var a = 0.0
            try {
                a = edit_text_1.text.toString().toDouble()
            }
            catch (e : RuntimeException){
                Snackbar.make(text_view, "Недопустимое число в первом поле", Snackbar.LENGTH_LONG).show()
                ok = false
            }

            var b = 0.0
            try {
                b = edit_text_2.text.toString().toDouble()
            }
            catch (e : RuntimeException){
                Snackbar.make(text_view, "Недопустимое число во втором поле", Snackbar.LENGTH_LONG).show()
                ok = false
            }

            var c = 0.0

            if (ok)
                when (oper) {
                    "button_plus" -> c = a + b
                    "button_minus" -> c = a - b
                    "button_multiply" -> c = a * b
                    "button_divide" -> {
                        if (b == 0.toDouble()) {
                            Snackbar.make(text_view, "Деление на ноль", Snackbar.LENGTH_LONG).show()
                            ok = false
                        } else
                            c = a / b
                    }
                }

            if (ok)
                text_view.text = c.toString()
            else
                text_view.text = "error"
        }

        var last_oper = "none"

        button_plus.setOnClickListener {
            last_oper = "button_plus"
            calculate(last_oper)
        }

        button_minus.setOnClickListener {
            last_oper = "button_minus"
            calculate(last_oper)
        }

        button_mult.setOnClickListener {
            last_oper = "button_multiply"
            calculate(last_oper)
        }

        button_dir.setOnClickListener {
            last_oper = "button_divide"
            calculate(last_oper)
        }

        edit_text_1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (last_oper!= "none")
                    calculate(last_oper)
            }

            override fun afterTextChanged(s: Editable) {
            }
        })

        edit_text_2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (last_oper!= "none")
                    calculate(last_oper)
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
    }
}
