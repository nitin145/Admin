package com.provider.citoCabs.dataSources.remote

import com.provider.citoCabs.data.*
import com.provider.citoCabs.data.responeClasses.BaseResponse

interface RemoteDataSource {


    suspend fun login(payload: LoginParams): BaseResponse
    suspend fun logout(): BaseResponse
    suspend fun deleteAccount(): BaseResponse
    suspend fun addProfile(payload: ProfileParams): BaseResponse
    suspend fun upgradeToDriver(payload: ProfileParams): BaseResponse
    suspend fun getProfile(): BaseResponse
    suspend fun getWalletInfo(): BaseResponse
    suspend fun getStates(): BaseResponse
    suspend fun getOrderId(payload: OrderParams): BaseResponse
    suspend fun raisePaymentIssue(payload: RaisePaymentParams): BaseResponse
    suspend fun suggestion(payload: RaisePaymentParams): BaseResponse
    suspend fun purchaseSubscription(payload: SubscriptionParams): BaseResponse
    suspend fun getSubscriptionDetails(): BaseResponse
    suspend fun getRides(payload: RideParams): BaseResponse
    suspend fun finishRide(id:String): BaseResponse
    suspend fun getAgentRides(payload: RideParams): BaseResponse
    suspend fun addRide(payload: AddRideParams): BaseResponse
    suspend fun createRideAlert(payload: RideAlertItem): BaseResponse
    suspend fun deleteRideAlert(payload: QueryParams): BaseResponse
    suspend fun getRideAlerts(): BaseResponse



}