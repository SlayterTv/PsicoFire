package com.slaytertv.psicofire.ui.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.slaytertv.psicofire.R
import com.slaytertv.psicofire.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import com.slaytertv.psicofire.ui.viewmodel.auth.LoginViewModel
import com.slaytertv.psicofire.util.UiState
import com.slaytertv.psicofire.util.anallogin
import com.slaytertv.psicofire.util.clickdisable
import com.slaytertv.psicofire.util.clickenable
import com.slaytertv.psicofire.util.dialogx
import com.slaytertv.psicofire.util.hide
import com.slaytertv.psicofire.util.isValidEmail
import com.slaytertv.psicofire.util.show
import com.slaytertv.psicofire.util.toast


@AndroidEntryPoint
class LoginFragment : Fragment() {
    //creamos val tag
    val TAG: String = "RegisterFragment"
    //binding
    lateinit var binding: FragmentLoginBinding
    //viewmodel de auth para cver los cambios
    val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //llamar observer
        observer()
        initListeners()
    }

    private fun initListeners() {
        with(binding){
            //botn login
            loginBtn.setOnClickListener {
                //findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                handleLogin()
            }
            //btn si se olvida el passwr
            forgotPassLabel.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
            }
            //btn si se necesita registrar
            registerLabel.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    fun observer(){
        //ciclo de vida del login con el live data
        viewModel.login.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    handleLoading(isLoading = true)
                }
                is UiState.Failure -> {
                    handleLoading(isLoading = false)
                    //toast(state.error)
                    dialogx(state.error)
                }
                is UiState.Sucess -> {
                    handleLoading(isLoading = false)
                    anallogin(binding.passEt.text.toString())
                    //toast(state.data)
                    //findNavController().navigate(R.id.action_loginFragment_to_home_navigation)
                    findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                }

                else -> {toast("erro when")}
            }
        }
    }
    //preguntar al inicio si existe akguna sesion para ingresar directamente
    override fun onStart() {
        super.onStart()
        //manda los cambios a getsession si el usuario es distinto de null
        viewModel.getSession { user ->
            if (user != null){
                if(user.statedate == "open"){
                    //findNavController().navigate(R.id.action_loginFragment_to_home_navigation)
                    findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                }else{toast("renovar cuenta")}
            }
        }
    }
    //cambia el esto del boton y el progressbar
    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                binding.loginBtn.setText("")
                binding.loginBtn.clickdisable()
                binding.loginProgress.show()
            } else {
                binding.loginBtn.setText("Login")
                binding.loginBtn.clickenable()
                binding.loginProgress.hide()
            }
        }
    }
    //cuando se apreta boton login
    private fun handleLogin() {
        //pregubnta si los campos estan vacios
        if (validation()) {
            //se manda al viewmodel con los datos para loguear
            viewModel.login(
                email = binding.emailEt.text.toString(),
                password = binding.passEt.text.toString()
            )
        }
    }
    //validar camopse vacios
    fun validation(): Boolean {
        var isValid = true

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