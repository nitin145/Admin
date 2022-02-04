package com.provider.citoCabs.repo

import com.provider.citoCabs.data.*
import com.provider.citoCabs.data.responeClasses.BaseResponse


interface RemoteRepository {

    fun login(
        registerRequest: LoginParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun logout(
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )
    fun deleteAccount(
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun addProfile(
        registerRequest: ProfileParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun upgradeToDriver(
        registerRequest: ProfileParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun getProfile(
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun getWalletInfo(
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun getStates(
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun getOrderId(
        orderParams: OrderParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun raisePaymentParams(
        orderParams: RaisePaymentParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun suggestion(
        orderParams: RaisePaymentParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun purchaseSubscription(
        orderParams: SubscriptionParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun getSubscriptionDetails(
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun getRides(
        orderParams: RideParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )
    fun finishRide(
        id:String,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )
    fun getAgentRides(
        orderParams: RideParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )


    fun addRide(
        orderParams: AddRideParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun addRideAlerts(
        orderParams: RideAlertItem,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )
    fun deleteRideAlert(
        params: QueryParams,
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )

    fun getRideAlerts(
        onResult: (isSuccess: Boolean, baseResponse: BaseResponse) -> Unit
    )




}