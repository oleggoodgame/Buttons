package com.example.all

import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.all.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindingClass: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingClass.root)
        val listRadioButton = listOf(bindingClass.radioButton2, bindingClass.radioButton3,bindingClass.radioButton4,bindingClass.radioButton5)
        val listChipButton = listOf(bindingClass.chip2, bindingClass.chip3,bindingClass.chip4,bindingClass.chip5)
        val listRadioBool = MutableList(listRadioButton.size) { false }
        val listChipBool = MutableList(listChipButton.size) { false }
        val listImagesButton = listOf(bindingClass.imageButton2, bindingClass.imageButton2, bindingClass.imageButton2, bindingClass.imageButton2)
        val checkBoxes = listOf(bindingClass.checkBox4, bindingClass.checkBox3, bindingClass.checkBox2, bindingClass.checkBox  )
        val textView= bindingClass.TextViews
        listRadioButton.forEachIndexed { index, radioButton ->
            radioButton.setOnClickListener {
                listRadioBool[index] = !listRadioBool[index]
            }
        }
        listChipButton.forEachIndexed { index, chip ->
            chip.setOnClickListener {
                listChipBool[index] = !listChipBool[index]
            }
        }
        bindingClass.button.setOnClickListener {
            listImagesButton.forEachIndexed { index, imageButton ->
                if (listRadioBool[index]==true && listChipBool[index]==true) {
                    imageButton.visibility = ImageButton.GONE
                }
            }
        }
        checkBoxes.forEach { checkBox ->
            checkBox.setOnCheckedChangeListener { _, _ -> updateTextView(textView, checkBoxes) } //це метод CheckBox, який дозволяє вам встановити слухача (listener) для змінення стану цього CheckBox.
        // Перший параметр (зазначений як _ у вашому випадку) — це сам CheckBox, який викликав цей метод.
            //Другий параметр (також зазначений як _) — це новий стан CheckBox (включений або вимкнений), але у цьому випадку ми його не використовуємо.
        }
        bindingClass.toggleButton.setOnClickListener {
            Toast.makeText(this, "Toggle Button clicked", Toast.LENGTH_LONG).show()
        }

        // Switch Change Listener
        bindingClass.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Switch is ON, apply dark theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                // Switch is OFF, apply light theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
    // допоміг штучний інтелект
    private fun updateTextView(textView: TextView, checkBoxes: List<CheckBox>) {
        val selectedTexts = checkBoxes.filter { it.isChecked }.map { it.text.toString() }
        //filter — це функція, яка повертає новий список, що містить тільки ті елементи, для яких умова всередині filter є істинною.
        //В даному випадку, ми фільтруємо список checkBoxes, залишаючи лише ті, що вибрані (isChecked повертає true).
        textView.text = selectedTexts.joinToString(", ")
        //map — це функція, яка перетворює кожен елемент списку у новий вигляд, визначений у функції, переданій у map.
        //У цьому випадку, ми перетворюємо кожен вибраний CheckBox на рядок його тексту (text.toString()).
    }
}