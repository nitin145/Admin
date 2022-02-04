package com.provider.citoCabs.dataSources.remote

import com.provider.citoCabs.R
import com.provider.citoCabs.base.MainApplication
import com.provider.citoCabs.base.Prefs
import com.provider.citoCabs.data.*
import com.provider.citoCabs.data.responeClasses.BaseResponse
import com.provider.citoCabs.network.core.APIService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class RemoteDataSourceImp(
    private val apiService: APIService
) : RemoteDataSource {


    override suspend fun login(payload: LoginParams): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.login(payload)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun logout(): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.logout()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun deleteAccount(): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.deleteAccount()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }


    override suspend fun addProfile(payload: ProfileParams): BaseResponse {
        var response = BaseResponse()

        try {
            var params = HashMap<String, RequestBody>()
            params["id"] =
                RequestBody.create("text/plain".toMediaTypeOrNull(), Prefs.init().userId!!)
            params["deviceType"] =
                RequestBody.create("text/plain".toMediaTypeOrNull(), payload.deviceType!!)

            params["deviceToken"] =
                RequestBody.create("text/plain".toMediaTypeOrNull(), payload.deviceToken!!)
            params["userType"] =
                RequestBody.create("text/plain".toMediaTypeOrNull(), payload.userType!!)
            params["name"] =
                RequestBody.create("text/plain".toMediaTypeOrNull(), payload.name!!)
            params["email"] =
                RequestBody.create("text/plain".toMediaTypeOrNull(), payload.email!!)
            params["aadharNumber"] =
                RequestBody.create("text/plain".toMediaTypeOrNull(), payload.aadharNumber!!)


            if (payload.userType == MainApplication.instance.getString(R.string.driver)) {
                params["rcNo"] =
                    RequestBody.create("text/plain".toMediaTypeOrNull(), payload.rcNo!!)
                params["licenseNo"] =
                    RequestBody.create("text/plain".toMediaTypeOrNull(), payload.licenseNo!!)
                response = apiService.addProfile(
                    params,
                    payload.profileImage,
                    payload.aadharFrontImage,
                    payload.aadharBackImage,
                    payload.licenseFrontImage,
                    payload.licenseBackImage,
                    payload.rcImage,
                    payload.carImage
                )

            } else {
                response = apiService.addProfile(
                    params,
                    payload.profileImage,
                    payload.aadharFrontImage,
                    payload.aadharBackImage,
                    null,
                    null,
                    null,
                    null
                )

            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun upgradeToDriver(payload: ProfileParams): BaseResponse {
        var response = BaseResponse()

        try {
            var params = HashMap<String, RequestBody>()

            params["rcNo"] =
                RequestBody.create("text/plain".toMediaTypeOrNull(), payload.rcNo!!)
            params["licenseNo"] =
                RequestBody.create("text/plain".toMediaTypeOrNull(), payload.licenseNo!!)
            response = apiService.upgradeToDriver(
                params,
                payload.licenseFrontImage,
                payload.licenseBackImage,
                payload.rcImage,
                payload.carImage
            )

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun getProfile(): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.getProfile()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun getWalletInfo(): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.getWalletInfo()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun getStates(): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.getStatesList()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun getOrderId(payload: OrderParams): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.getOrderId(payload)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun raisePaymentIssue(payload: RaisePaymentParams): BaseResponse {
        var response = BaseResponse()
        try {
            var params = HashMap<String, RequestBody>()
            params["description"] =
                RequestBody.create("text/plain".toMediaTypeOrNull(), payload.description!!)
            response = apiService.raisePaymentIssue(params, payload.image)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun suggestion(payload: RaisePaymentParams): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.suggestion(payload)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun purchaseSubscription(payload: SubscriptionParams): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.subscribe(payload)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun getSubscriptionDetails(): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.getSubscriptionPlans()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun getRides(payload: RideParams): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.getRides(payload)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun finishRide(id: String): BaseResponse {
        var response = BaseResponse()
        try {
            var rideParams = FinishRideParams()
            rideParams.rideId = id
            response = apiService.finishRide(rideParams)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun getAgentRides(payload: RideParams): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.getAgentRides(payload)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun addRide(payload: AddRideParams): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.addRide(payload)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun createRideAlert(payload: RideAlertItem): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.createRideAlert(payload)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun deleteRideAlert(payload: QueryParams): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.deleteRideAlert(payload)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }

    override suspend fun getRideAlerts(): BaseResponse {
        var response = BaseResponse()
        try {
            response = apiService.getCreateRideAlerts()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            response.message = APIService.getErrorMessageFromGenericResponse(e).toString()
            response.error = 1
        }
        return response
    }


}