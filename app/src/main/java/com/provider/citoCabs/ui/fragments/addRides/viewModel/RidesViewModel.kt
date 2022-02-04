package com.provider.citoCabs.ui.fragments.addRides.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.provider.citoCabs.R
import com.provider.citoCabs.base.BaseViewModel
import com.provider.citoCabs.base.plusAssign
import com.provider.citoCabs.data.AddRideParams
import com.provider.citoCabs.data.QueryParams
import com.provider.citoCabs.data.RideAlertItem
import com.provider.citoCabs.data.RideParams
import com.provider.citoCabs.data.responeClasses.BaseResponse
import com.provider.citoCabs.data.responeClasses.ResponseRideAlertsItem
import com.provider.citoCabs.data.responeClasses.ResponseRideItem
import com.provider.citoCabs.repo.RemoteRepository
import kotlinx.coroutines.launch

class RidesViewModel(
    private val remoteRepository: RemoteRepository
) :
    BaseViewModel() {

    var getRideParams = RideParams()
    var getAgentRideParams = RideParams()
    var addRideParams = AddRideParams()
    var createRideAlertParams = RideAlertItem()
    var deleteRidePArams = QueryParams()
    var addRideResult = MutableLiveData<ResponseRideAlertsItem>()
    var deleteRideResponse = MutableLiveData<Boolean>()
    var showProgress = MutableLiveData<Boolean>()

    var ridesList = MutableLiveData<ArrayList<ResponseRideItem>>()
    var agentRidesList = MutableLiveData<ArrayList<ResponseRideItem>>()
    var finsihRideResponse = MutableLiveData<BaseResponse>()
    var addRideResponse = MutableLiveData<BaseResponse>()

    val ridesAlertList = MutableLiveData<ArrayList<ResponseRideAlertsItem>>()
    var statesList = MutableLiveData<BaseResponse>()


    fun getStatesList() {
        viewModelScope.launch {
            remoteRepository.getStates()
            { isSuccess: Boolean, response: BaseResponse ->
                if (isSuccess) {
                    statesList.postValue(response)

                } else {
                    showMessage.postValue(response.message)
                }
            }
        }
    }

    fun getCreateRideAlerts() {
        showLoading.postValue(true)
        viewModelScope.launch {
            remoteRepository.getRideAlerts()
            { isSuccess: Boolean, response: BaseResponse ->
                showLoading.postValue(false)
                if (isSuccess) {
                    ridesAlertList.postValue(response.data?.priorityList as ArrayList<ResponseRideAlertsItem>?)

                } else {
                    showMessage.postValue(response.message)
                }
            }
        }
    }

    fun getRides() {
        viewModelScope.launch {
            remoteRepository.getRides(getRideParams)
            { isSuccess: Boolean, response: BaseResponse ->
                if (isSuccess) {
                    ridesList += response.data?.rideData as ArrayList<ResponseRideItem>

                } else {
                    showMessage.postValue(response.message)
                }
            }
        }
    }

    fun finishRide(id: String) {
        viewModelScope.launch {
            showLoading.postValue(true)
            remoteRepository.finishRide(id)
            { isSuccess: Boolean, response: BaseResponse ->
                if (isSuccess) {
                    showLoading.postValue(false)
                    finsihRideResponse.postValue(response)


                } else {
                    showMessage.postValue(response.message)
                }
            }
        }
    }

    fun getAgentRides() {
        viewModelScope.launch {
            showLoading.postValue(true)
            remoteRepository.getAgentRides(getAgentRideParams)
            { isSuccess: Boolean, response: BaseResponse ->
                showLoading.postValue(false)
                if (isSuccess) {
                    agentRidesList += response.data?.rideData as ArrayList<ResponseRideItem>

                } else {
                    agentRidesList.postValue(ArrayList())
                }
            }
        }
    }


    fun addRide() {
        showLoading.postValue(true)
        viewModelScope.launch {
            remoteRepository.addRide(addRideParams)
            { isSuccess: Boolean, response: BaseResponse ->
                showLoading.postValue(false)
                if (isSuccess) {
                    addRideResponse.postValue(response)

                } else {
                    showMessage.postValue(response.message)
                }
            }
        }
    }

    fun addRideAlerts() {
        showLoading.postValue(true)
        viewModelScope.launch {
            remoteRepository.addRideAlerts(createRideAlertParams)
            { isSuccess: Boolean, response: BaseResponse ->
                showLoading.postValue(false)
                if (isSuccess) {
                    addRideResult.postValue(response.data?.priorityData!!)
                } else {
                    showMessage.postValue(response.message)
                }
            }
        }
    }

    fun deleteRideAlert() {
        showLoading.postValue(true)
        viewModelScope.launch {
            remoteRepository.deleteRideAlert(deleteRidePArams)
            { isSuccess: Boolean, response: BaseResponse ->
                showLoading.postValue(false)
                if (isSuccess) {
                    deleteRideResponse.postValue(true)
                } else {
                    showMessage.postValue(response.message)
                }
            }
        }
    }


}