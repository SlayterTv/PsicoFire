package com.slaytertv.psicofire.ui.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slaytertv.psicofire.data.repository.AuthRepository
import com.slaytertv.psicofire.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
//injectamos en el constructor
class ForgotPassViewModel @Inject constructor(
    val repository: AuthRepository
): ViewModel() {
    //observer forgot ppassweorf
    private val _forgotPassword = MutableLiveData<UiState<String>>()
    val forgotPassword: LiveData<UiState<String>>
        get() = _forgotPassword

    //funcion olvido password
    fun forgotPassword(email: String) {
        _forgotPassword.value = UiState.Loading
        repository.forgotPassword(email){
            _forgotPassword.value = it
        }
    }
}