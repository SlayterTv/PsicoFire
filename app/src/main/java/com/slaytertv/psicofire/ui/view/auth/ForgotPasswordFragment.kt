package com.slaytertv.psicofire.ui.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.slaytertv.psicofire.R
import com.slaytertv.psicofire.databinding.FragmentForgotPasswordBinding
import com.slaytertv.psicofire.ui.viewmodel.auth.ForgotPassViewModel
import com.slaytertv.psicofire.util.UiState
import com.slaytertv.psicofire.util.clickdisable
import com.slaytertv.psicofire.util.clickenable
import com.slaytertv.psicofire.util.hide
import com.slaytertv.psicofire.util.show
import com.slaytertv.psicofire.util.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
    val TAG :String ="ForgotPasswordFragment"
    //creamos binding
    lateinit var binding: FragmentForgotPasswordBinding
    //viewmodel para usar authviewmodel
    val viewModel: ForgotPassViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (this::binding.isInitialized){
            return binding.root
        }else {
            binding = FragmentForgotPasswordBinding.inflate(layoutInflater)
            return binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //llamar observer
        initListeners()
        observer()
    }
    private fun initListeners() {
        with(binding){
            forgotPassBtn.setOnClickListener {
                viewModel.forgotPassword(emailEt.text.toString())
            }
        }
    }

    private fun observer() {
        viewModel.forgotPassword.observe(viewLifecycleOwner){state ->
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
                    toast(state.data)
                }

                else -> {toast("error when")}
            }
        }
    }
    //cambia el esto del boton y el progressbar
    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                binding.forgotPassBtn.setText("")
                binding.forgotPassBtn.clickdisable()
                binding.forgotPassProgress.show()
            } else {
                binding.forgotPassBtn.setText("Send")
                binding.forgotPassBtn.clickenable()
                binding.forgotPassProgress.hide()
            }
        }
    }

}