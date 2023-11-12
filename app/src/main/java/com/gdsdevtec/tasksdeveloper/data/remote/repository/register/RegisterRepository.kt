package com.gdsdevtec.tasksdeveloper.data.remote.repository.register

import android.accounts.NetworkErrorException
import com.gdsdevtec.tasksdeveloper.di.FirebaseInjection
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RegisterRepository private constructor(
    private val auth: FirebaseAuth,
) {
    suspend fun registerUser(user: RegisterUserRequest): RegisterRepositoryState {
        return try {
            return withContext(Dispatchers.IO) {
                return@withContext try {
                    auth.createUserWithEmailAndPassword(user.email, user.password).await()
                    RegisterRepositoryState.SuccessRegister
                } catch (e: Exception) {
                    val msg = when (e) {
                        is FirebaseAuthWeakPasswordException -> WEAK_PASSWORD
                        is FirebaseAuthUserCollisionException -> USER_REGISTERED
                        is FirebaseAuthEmailException, is FirebaseAuthInvalidCredentialsException -> INVALID_EMAIL
                        is FirebaseException -> FIREBASE_ERROR
                        else -> String.format(GENERIC_FAILURE_WITH_CONTEXT, e.message)
                    }
                    RegisterRepositoryState.ErrorRegister(msg)
                }
            }
        } catch (e: Exception) {
            val msg = when (e) {
                is FirebaseNetworkException, is NetworkErrorException -> CONNECTION_ERROR
                else -> String.format(GENERIC_FAILURE,e.message)
            }
            RegisterRepositoryState.ErrorRegister(msg)
        }
    }

    companion object {

        const val WEAK_PASSWORD = "senha fraca"
        const val USER_REGISTERED = "Email ja cadastrado"
        const val INVALID_EMAIL = "email invalido"
        const val FIREBASE_ERROR = "Erro firebase"
        const val GENERIC_FAILURE_WITH_CONTEXT = "falha generica %s withContext"
        const val GENERIC_FAILURE = "falha generica %s"
        const val CONNECTION_ERROR = "Falha na conecxao"

        @Volatile
        private var instance: RegisterRepository? = null
        fun getInstance(): RegisterRepository {
            return instance ?: synchronized(this) {
                instance ?: RegisterRepository(auth = FirebaseInjection.getFirebaseAuth()).also {
                    instance = it
                }
            }
        }
    }
}