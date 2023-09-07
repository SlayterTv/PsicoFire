package com.slaytertv.psicofire.ui.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slaytertv.psicofire.data.model.UserItem
import com.slaytertv.psicofire.data.repository.AuthRepository
import com.slaytertv.psicofire.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
//injectamos en el constructor
class AuthViewModel @Inject constructor(
    val repository: AuthRepository
): ViewModel() {
    //modelo de vista de auth
    //variable para guardar los datos del registro
    private val _register = MutableLiveData<UiState<String>>()
    //para ver los datos actuales
    val register: LiveData<UiState<String>>
        get() = _register

    //funcion cuando se registren
    fun register(
        //pedimos los datos
        email: String,
        password: String,
        user: UserItem
    ) {
        //colcamos el estado en cargando
        _register.value = UiState.Loading
        //registramos al usuario
        repository.registerUser(
            //mandamos los datos obtenidos
            email = email,
            password = password,
            user = user
        ) { _register.value = it }
        //deolvemos el resultado
    }
    //desloguear
    fun logout(result: () -> Unit){
        repository.logout(result)
    }
    //mandar la session para el get[preferences
    fun getSession(result: (UserItem?) -> Unit){
        repository.getSession(result)
    }
}