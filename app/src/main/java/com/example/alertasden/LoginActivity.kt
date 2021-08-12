package com.example.alertasden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        txtUser=findViewById(R.id.txtUser)
        txtPassword=findViewById(R.id.txtPassword)
        progressBar=findViewById(R.id.progressBar)
        auth= FirebaseAuth.getInstance()
        setup()
    }
    private fun setup(){
        title= "LOGIN"
    }


    fun register(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
    fun acceder(view: View) {
        loginuser()
        progressBar.visibility=View.GONE
    }

    fun forgot(view: View) {
        startActivity(Intent(this, ForgotActivity::class.java))

    }

    private fun loginuser(){
        val user:String= txtUser.text.toString()
        val password:String=txtPassword.text.toString()

        if (!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(password)){
            progressBar.visibility=View.VISIBLE

                auth.signInWithEmailAndPassword(user,password)
                    .addOnCompleteListener(this, ){
                            task ->
                        if (task.isSuccessful){
                            action()
                        }else{
                            Toast.makeText(this, "Error al Autenticar", Toast.LENGTH_LONG).show()
                            progressBar.visibility=View.GONE
                        }
            }
        }
        else{
            Toast.makeText(this, "Rellena todos los campos o verifica que los datos esten correctos", Toast.LENGTH_LONG).show()

        }
    }
    private fun action(){
        startActivity(Intent(this, MainActivity::class.java))

    }
}