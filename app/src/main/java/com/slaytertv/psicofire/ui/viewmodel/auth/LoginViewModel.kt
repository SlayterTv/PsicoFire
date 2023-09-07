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
class LoginViewModel @Inject constructor(
    val repository: AuthRepository
): ViewModel() {
    //observer login
    private val _login = MutableLiveData<UiState<String>>()
    val login: LiveData<UiState<String>>
        get() = _login

    //fun login
    fun login(
        email: String,
        password: String
    ) {
        _login.value = UiState.Loading
        repository.loginUser(
            email,
            password
        ){
            _login.value = it
        }
    }

    //mandar la session para el get[preferences
    fun getSession(result: (UserItem?) -> Unit){
        repository.getSession(result)
    }

}