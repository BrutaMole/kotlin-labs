package com.example.todo
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_create_object.*
class CreateObject : AppCompatActivity() {
    private lateinit var database: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_object)
        database = Room.databaseBuilder(
            applicationContext, Database::class.java, "To_Do").build()
        add_button.setOnClickListener {
            if (!add_title.text.toString().trim { it <= ' ' }.isNotEmpty() || add_title.text.toString().length < 3
            ) {
                var text="Введите название! Длина названия должна быть не меньше 3 символов!"
                val toast = Toast.makeText(applicationContext,text, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
                toast.show()
            } else {
                var title = add_title.getText().toString().trim()
                var desc = add_desc.getText().toString()
                DatabaseObject.AddData(title, desc)
                val transition = Intent(this, MainActivity::class.java)
                startActivity(transition)
            }
        }
        close_button.setOnClickListener {
                val transition = Intent(this, MainActivity::class.java)
                startActivity(transition)
        }
    }
}

