package com.dan.comidarf.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.dan.comidarf.R
import com.dan.comidarf.databinding.ActivityRegisterBinding
import com.dan.comidarf.utils.message
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var auth: FirebaseAuth

    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = ActivityRegisterBinding.inflate(layoutInflater)
         setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding.btnCreateAccount.setOnClickListener {
            if (!validateFields()) {
                return@setOnClickListener
            }
            binding.progressBar.visibility = View.VISIBLE

            createUser(email, password)
        }

        binding.tvGoToLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }


    }

    private fun validateFields(): Boolean{
        email = binding.emailInput.text.toString().trim()  //Elimina los espacios en blanco
        password = binding.passwordInput.text.toString().trim()

        //Verifica que el campo de correo no esté vacío
        if(email.isEmpty()){
            binding.emailInput.error = "Se requiere el correo"
            binding.emailInput.requestFocus()
            return false
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailInput.error = "El correo no tiene un formato válido"
            binding.emailInput.requestFocus()
            return false
        }

        //Verifica que el campo de la contraseña no esté vacía y tenga al menos 6 caracteres
        if(password.isEmpty()){
            binding.passwordInput.error = "Se requiere una contraseña"
            binding.passwordInput.requestFocus()
            return false
        }else if(password.length < 6){
            binding.passwordInput.error = "La contraseña debe tener al menos 6 caracteres"
            binding.passwordInput.requestFocus()
            return false
        }
        return true
    }
    private fun actionLoginSuccess(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun createUser(user: String, password: String){
        auth.createUserWithEmailAndPassword(user, password).addOnCompleteListener { authResult ->
            if (authResult.isSuccessful) {

                auth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        message("Se ha enviado un correo de verificación")
                    }
                }?.addOnFailureListener {
                    message("Error al enviar el correo de verificación")
                }

                actionLoginSuccess()
                message("Usuario creado con exito")


            } else {
                binding.progressBar.visibility = View.GONE
                handleErrors(authResult)
            }

        }
    }

    private fun handleErrors(task: Task<AuthResult>){
        var errorCode = ""

        try{
            errorCode = (task.exception as FirebaseAuthException).errorCode
        }catch(e: Exception){
            e.printStackTrace()
        }

        when(errorCode){
            "ERROR_INVALID_EMAIL" -> {
                message("Error: El correo electrónico no tiene un formato correcto")
                binding.emailInput.error = "Error: El correo electrónico no tiene un formato correcto"
                binding.emailInput.requestFocus()
            }
            "ERROR_WRONG_PASSWORD" -> {
                message("Error: La contraseña no es válida")
                binding.passwordInput.error = "La contraseña no es válida"
                binding.passwordInput.requestFocus()
                binding.passwordInput.setText("")

            }
            "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" -> {
                //An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address.
                message("Error: Una cuenta ya existe con el mismo correo, pero con diferentes datos de ingreso")
            }
            "ERROR_EMAIL_ALREADY_IN_USE" -> {
                message("Error: el correo electrónico ya está en uso con otra cuenta.")
                binding.emailInput.error = ("Error: el correo electrónico ya está en uso con otra cuenta.")
                binding.emailInput.requestFocus()
            }
            "ERROR_USER_TOKEN_EXPIRED" -> {
                message("Error: La sesión ha expirado. Favor de ingresar nuevamente.")
            }
            "ERROR_USER_NOT_FOUND" -> {
                message("Error: No existe el usuario con la información proporcionada.")
            }
            "ERROR_WEAK_PASSWORD" -> {
                message("La contraseña porporcionada es inválida")
                binding.passwordInput.error = "La contraseña debe de tener por lo menos 6 caracteres"
                binding.passwordInput.requestFocus()
            }
            "NO_NETWORK" -> {
                message("Red no disponible o se interrumpió la conexión")
            }
            else -> {
                message("Error. No se pudo autenticar exitosamente.")
            }
        }

    }


}