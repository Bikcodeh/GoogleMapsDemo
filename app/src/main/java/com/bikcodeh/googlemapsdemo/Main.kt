package com.bikcodeh.googlemapsdemo

fun main() {

}

sealed interface CommonErrors
object ServerError : CommonErrors, GetUserErrors, LoginErrors
object Forbidden : CommonErrors, GetUserErrors
object Unauthorized : CommonErrors, GetUserErrors

sealed interface GetUserErrors
data class UserNotFound(val userId: String) : GetUserErrors
data class InvalidUserId(val userId: String) : GetUserErrors

sealed interface LoginErrors
data class InvalidUsername(val username: String) : LoginErrors
object InvalidPasswordFormat : LoginErrors

fun handleGetUserError(error: GetUserErrors): String = when (error) {
    is InvalidUserId -> TODO()
    is UserNotFound -> TODO()
    Forbidden -> TODO()
    ServerError -> TODO()
    Unauthorized -> TODO()
}

fun handleLoginError(error: LoginErrors): String = when (error) {
    is InvalidUsername -> TODO()
    InvalidPasswordFormat -> TODO()
    ServerError -> TODO()
}

fun handleCommonError(error: CommonErrors): String = when (error) {
    ServerError -> TODO()
    Forbidden -> TODO()
    Unauthorized -> TODO()
}
