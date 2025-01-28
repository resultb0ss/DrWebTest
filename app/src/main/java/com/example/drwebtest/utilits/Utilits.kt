package com.example.drwebtest.utilits

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.getDefaultAlertDialog(function: () -> Unit) {

    val builder = AlertDialog.Builder(this)

    builder.apply {
        setTitle("Разрешение необходимо чтобы получить список установленных приложений")
        setCancelable(false)
        setNegativeButton("Отмена") { _, _ -> }
        setPositiveButton("Запросить повторно") { _, _ ->
            function()
        }
    }.create().show()
}