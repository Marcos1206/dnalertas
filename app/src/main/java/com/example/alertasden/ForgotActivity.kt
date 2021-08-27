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

class ForgotActivity : AppCompatActivity() {
    private lateinit var txtEmail: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        txtEmail=findViewById(R.id.txtEmail)
        auth= FirebaseAuth.getInstance()
        progressBar=findViewById(R.id.progressBar)

    }

    private fun setup(){
        title= "RECUPERARA CONTRASEÑA"
    }

    fun send(view: View) {
        val email= txtEmail.text.toString()
        if(!TextUtils.isEmpty(email)){
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this){
                        task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))

                    }else{
                        Toast.makeText(this, "Error al Enviar el Email", Toast.LENGTH_SHORT).show()
                        progressBar.visibility= View.GONE
                    }
                }
        }
    }

}
