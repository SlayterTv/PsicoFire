package com.slaytertv.psicofire.ui.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.slaytertv.psicofire.R
import com.slaytertv.psicofire.data.model.UserItem
import com.slaytertv.psicofire.databinding.FragmentRegisterBinding
import com.slaytertv.psicofire.ui.viewmodel.auth.AuthViewModel
import com.slaytertv.psicofire.util.UiState
import com.slaytertv.psicofire.util.analregister
import com.slaytertv.psicofire.util.clickdisable
import com.slaytertv.psicofire.util.clickenable
import com.slaytertv.psicofire.util.hide
import com.slaytertv.psicofire.util.isValidEmail
import com.slaytertv.psicofire.util.show
import com.slaytertv.psicofire.util.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment() {
    //tag del fragment
    val TAG: String = "RegisterFragment"
    //creamos binding
    lateinit var binding: FragmentRegisterBinding
    //viewmodel para usar authviewmodel
    val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //llamamos al observador y a los botones
        observer()
        initListeners()
    }

    private fun initListeners() {
        with(binding){
            //cuando se apreta el boton de registro
            registerBtn.setOnClickListener {
                handleRegister()
            }
        }
    }

    //el observer se encargara de ver lose stados del registro
    fun observer() {
        viewModel.register.observe(viewLifecycleOwner) { state ->
            //se controlaran con el state
            when(state){
                is UiState.Loading -> {
                    handleLoading(isLoading = true)
                }
                is UiState.Failure -> {
                    handleLoading(isLoading = false)
                    toast(state.error)
                }
                is UiState.Sucess -> {
                    handleLoading(isLoading = false)
                    FirebaseMessaging.getInstance().subscribeToTopic("todos")
                    analregister(binding.emailEt.text.toString(),"${binding.firstNameEt.text.toString()} ${binding.lastNameEt.text.toString()}")
                    toast("${state.data}, verify your email")
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }

                else -> {toast("error when")}
            }
        }
    }
    //cambia el esto del boton y el progressbar
    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                binding.registerBtn.setText("")
                binding.registerBtn.clickdisable()
                binding.registerProgress.show()
            } else {
                binding.registerBtn.setText("Register")
                binding.registerBtn.clickenable()
                binding.registerProgress.hide()
            }
        }
    }
    //
    private fun handleRegister() {
        //verificamos que los campos esten llenos
        if (validation()){
            //si es asi pasamos tod al viewmodel para luego agregarlos
            viewModel.register(
                email = binding.emailEt.text.toString(),
                password = binding.passEt.text.toString(),
                user = getUserObj()
            )
        }
    }

    //funcion donde guardare los datos a agregar
    fun getUserObj(): UserItem {
        return UserItem(
            id = "",
            first_name = binding.firstNameEt.text.toString(),
            last_name = binding.lastNameEt.text.toString(),
            phone_name = binding.phoneNameEt.text.toString(),
            work_name = binding.workNameEt.text.toString(),
            work_phone = binding.workPhoneEt.text.toString(),
            work_direc = binding.workDirecEt.text.toString(),
            work_nit = binding.workNitEt.text.toString(),
            email = binding.emailEt.text.toString(),
            password = binding.passEt.text.toString()
        )
    }
    //verificar que los cmpos no esten vacios
    fun validation(): Boolean {
        var isValid = true

        if (binding.firstNameEt.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_first_name))
        }

        if (binding.lastNameEt.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_last_name))
        }

        if (binding.phoneNameEt.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_phone_name))
        }
        if (binding.workNameEt.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_name_work))
        }
        if (binding.workPhoneEt.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_phone_work))
        }
        if (binding.workDirecEt.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_direc_work))
        }
        if (binding.workNitEt.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_nit_work))
        }


        if (binding.emailEt.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_email))
        }else{
            if (!binding.emailEt.text.toString().isValidEmail()){
                isValid = false
                toast(getString(R.string.invalid_email))
            }
        }
        if (binding.passEt.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_password))
        }else{
            if (binding.passEt.text.toString().length < 8){
                isValid = false
                toast(getString(R.string.invalid_password))
            }
        }
        return isValid
    }


}