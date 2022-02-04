package com.provider.citoCabs.repo

import com.provider.citoCabs.data.*
import com.provider.citoCabs.data.responeClasses.BaseResponse
import com.provider.citoCabs.dataSources.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RemoteRepositoryImpl(
    private val authenticationDataSource: RemoteDataSource
) :
    RemoteRepository {


    override fun login(
        registerRequest: LoginParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.login(registerRequest)

            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun logout(onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.logout()

            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun deleteAccount(onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.deleteAccount()

            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }


    override fun addProfile(
        registerRequest: ProfileParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.addProfile(registerRequest)
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun upgradeToDriver(
        registerRequest: ProfileParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.upgradeToDriver(registerRequest)
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun getProfile(onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.getProfile()
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun getWalletInfo(onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.getWalletInfo()
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun getStates(

        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.getStates()
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun getOrderId(
        orderParams: OrderParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.getOrderId(orderParams)
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun raisePaymentParams(
        orderParams: RaisePaymentParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.raisePaymentIssue(orderParams)
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun suggestion(
        orderParams: RaisePaymentParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.suggestion(orderParams)
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun purchaseSubscription(
        orderParams: SubscriptionParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.purchaseSubscription(orderParams)
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun getSubscriptionDetails(onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.getSubscriptionDetails()
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun getRides(
        rideParams: RideParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.getRides(rideParams)
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun finishRide(
        id: String,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.finishRide(id)
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun getAgentRides(
        orderParams: RideParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.getAgentRides(orderParams)
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun addRide(
        addRideParams: AddRideParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.addRide(addRideParams)
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun addRideAlerts(
        orderParams: RideAlertItem,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.createRideAlert(orderParams)
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun deleteRideAlert(
        params: QueryParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.deleteRideAlert(params)
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }

    override fun getRideAlerts(onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = authenticationDataSource.getRideAlerts()
            if (response.error == 1) {
                onResult(false, response)
            } else {
                onResult(response.Success!!, response)
            }
        }
    }


}