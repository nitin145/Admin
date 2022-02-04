package com.provider.citoCabs.ui.fragments.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.provider.citoCabs.R
import com.provider.citoCabs.base.ScopedFragment
import com.provider.citoCabs.databinding.FragmentLoginBinding
import com.provider.citoCabs.ui.activities.MainActivity
import com.provider.citoCabs.ui.fragments.auth.viewModel.LoginViewModel
import com.provider.citoCabs.ui.fragments.auth.viewModel.LoginViewModelFactory
import kotlinx.coroutines.MainScope
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

/**
 * Created by Nitin SHarma on 9/4/2021.
 */
class LoginFragment : ScopedFragment(), KodeinAware {
    override val kodein by lazy { (activity?.applicationContext as KodeinAware).kodein }
    lateinit var mBinding: FragmentLoginBinding
    private val viewModelFactory: LoginViewModelFactory by instance()
    lateinit var mViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel =
            ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        mBinding = FragmentLoginBinding.inflate(inflater, container, false).apply {
            clickHandler = ClickHandler()
        }
        setupObserver()
        hideKeyboard(mBinding.root)
        return mBinding.root
    }

    inner class ClickHandler {
        fun getStarted() {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }

    private fun setupObserver() {

        mViewModel.apply {
            /*  addRideResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                  if (it != null) {
                      showToast("Ride Added Successfully")
                      setFragmentResult(getString(R.string.add_ride), Bundle())
                      findNavController().navigateUp()
                  }
              })*/

            showLoading.observe(viewLifecycleOwner) {
                if (it == true) {
                    showProgress()
                } else hideProgress()
            }

            showMessage.observe(viewLifecycleOwner, Observer {
                hideProgress()
                if (!it.isNullOrEmpty()) {
                    showToast(it)
                }

            })

        }

    }

    override fun stopBlur() {

    }
}