package com.example.sharedprefkotlin

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var input: String = ""
    private var result: String = ""
    private var KEY: String = "key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = this.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        btnSave.setOnClickListener(this)
        btnRetrive.setOnClickListener(this)
        btnClear.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            btnSave -> {
                input = etText.text.toString()
                if (null == input || input.equals("", true)) {
                    showToast("Enter some input to save")
                } else {
                    editor.putString(KEY, input)
                    editor.commit()
                    etText.setText("")
                    showToast("Input saved to pref")
                }
            }
            btnRetrive -> {
                result = sharedPreferences.getString(KEY, "").toString()
                if (null == result || result.equals("", true))
                    showToast("No result found")
                else tvResult.text = result
            }
            btnClear -> {
                editor.remove(KEY)
                editor.clear()
                editor.commit()
                tvResult.text = ""
                showToast("Cleared shared pref")
            }
            else -> {

            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(
            this, message,
            Toast.LENGTH_SHORT
        ).show()
    }
}
