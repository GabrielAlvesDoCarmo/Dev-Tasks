package com.gdsdevtec.tasksdeveloper.data.remote.repository.login

import android.accounts.NetworkErrorException
import com.gdsdevtec.tasksdeveloper.di.FirebaseInjection
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginRepository private constructor(
    private val auth: FirebaseAuth,
) {
    val isUserLogged: Boolean get() = auth.currentUser != null
    suspend fun login(user: LoginUserRequest): LoginRepositoryState {
        return try {
            return withContext(Dispatchers.IO) {
                return@withContext try {
                    auth.signInWithEmailAndPassword(user.email, user.password).await()
                    LoginRepositoryState.Success
                } catch (e: Exception) {
                    val msg = when (e) {
                        is FirebaseAuthInvalidUserException -> "Usuario invalido ou inexistente"
                        is FirebaseAuthInvalidCredentialsException -> "credenciais invalida"
                        is FirebaseAuthEmailException -> "Email invalido"
                        is FirebaseAuthUserCollisionException -> "falha de cadastro"
                        is FirebaseAuthException -> "falha generica de autenticacao "
                        is FirebaseException -> "Falha firebase ${e.message}"
                        else -> String.format("Falha generica withContext", e.message)
                    }
                    LoginRepositoryState.Error(msg)
                }
            }
        } catch (e: Exception) {
            val msg = when (e) {
                is FirebaseNetworkException, is NetworkErrorException -> "Falha de conecxao"
                else -> String.format("falha generica", e.message)
            }
            LoginRepositoryState.Error(msg)
        }
    }


    companion object {
        @Volatile
        private var instance: LoginRepository? = null
        fun getInstance(): LoginRepository {
            return instance ?: synchronized(this) {
                instance ?: LoginRepository(auth = FirebaseInjection.getFirebaseAuth()).also {
                    instance = it
                }
            }
        }
    }
}