package com.provider.citoCabs.ui.fragments.auth.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.provider.citoCabs.base.BaseViewModel
import com.provider.citoCabs.data.LoginParams
import com.provider.citoCabs.data.ProfileParams
import com.provider.citoCabs.data.responeClasses.BaseResponse
import com.provider.citoCabs.repo.RemoteRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val remoteRepository: RemoteRepository
) :
    BaseViewModel() {


    var loginParams = LoginParams()
    var profileParams = ProfileParams()
    var data = MutableLiveData<BaseResponse>()
    var upgradeToDriverResponse = MutableLiveData<BaseResponse>()


    fun socialLogin() {
        viewModelScope.launch {
            remoteRepository.login(loginParams)
            { isSuccess: Boolean, response: BaseResponse ->
                showLoading.postValue(false)
                if (isSuccess) {
                    data.postValue(response)

                } else {
                    showMessage.postValue(response.message)
                }
            }
        }
    }

    fun addProfile() {
        showLoading.postValue(true)
        viewModelScope.launch {
            remoteRepository.addProfile(profileParams)
            { isSuccess: Boolean, response: BaseResponse ->
                showLoading.postValue(false)
                if (isSuccess) {
                    data.postValue(response)
                } else {
                    showMessage.postValue(response.message)
                }
            }
        }
    }

    fun upgradeTodDriver() {
        showLoading.postValue(true)
        viewModelScope.launch {
            remoteRepository.upgradeToDriver(profileParams)
            { isSuccess: Boolean, response: BaseResponse ->
                showLoading.postValue(false)
                if (isSuccess) {
                    upgradeToDriverResponse.postValue(response)
                } else {
                    showMessage.postValue(response.message)
                }
            }
        }
    }


}