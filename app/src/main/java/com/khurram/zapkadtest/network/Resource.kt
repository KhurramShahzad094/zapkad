package com.khurram.zapkadtest.network

sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}

//data class Resource<out T>(val status: Status, val data: T?, val message: String?,val exception: Exception?) {
//    companion object {
//        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null,exception = null)
//
//        fun <T> error(data: T?, message: String,exception: Exception?): Resource<T> =
//            Resource(status = Status.ERROR, data = data, message = message,exception = exception)
//
//        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null,exception = null)
//    }
//}