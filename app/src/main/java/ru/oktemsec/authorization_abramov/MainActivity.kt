package ru.oktemsec.authorization_abramov

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    // Объявляем об использовании следующих объектов:
    private var username: EditText? = null
    private var password: EditText? = null
    private var login: Button? = null
    private var loginLocked: TextView? = null

    // Число для подсчета попыток залогиниться:
    var numberOfRemainingLoginAttempts = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Связываемся с элементами нашего интерфейса:
        username = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        login = findViewById(R.id.btnSignin)
        loginLocked = findViewById(R.id.tvInfo)

        login?.setOnClickListener {
            login()
        }
    }

    // Обрабатываем нажатие кнопки "Войти":
    private fun login() {

        // Если введенные логин и пароль будут словом "admin",
        // показываем Toast сообщение об успешном входе:
        if (username?.text.toString() == "admin" && password?.text.toString() == "admin") {
            Toast.makeText(this, "Вход выполнен!", Toast.LENGTH_SHORT).show()

            // Выполняем переход на другой экран:
            val intent: Intent = Intent(this, SignInSuccess::class.java)
            startActivity(intent)
        }
        else {
            Toast.makeText(this, "Неправильные данные!",Toast.LENGTH_SHORT).show()
            numberOfRemainingLoginAttempts--
            Toast.makeText(this, "Осталось попыток: $numberOfRemainingLoginAttempts", Toast.LENGTH_SHORT).show()

            // Когда выполнено 3 безуспешных попытки залогиниться,
            // делаем видимым текстовое поле с надписью, что все пропало и выставляем
            // кнопке настройку невозможности нажатия setEnabled(false):
            if (numberOfRemainingLoginAttempts == 0) {
                login?.isEnabled = false
                loginLocked?.visibility = View.VISIBLE
                loginLocked?.setTextColor(Color.RED)
                loginLocked?.text = "Вход заблокирован!!!"
            }
        }
    }
}