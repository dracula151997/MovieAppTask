package com.yelloco.movieapp.network

enum class Status {
    LOADING,
    SUCCESS,
    FAILED,
}

class NetworkingState(val status: Status, val message: String) {

    companion object {
        val LOADING: NetworkingState
        val LOADED: NetworkingState
        val ERROR: NetworkingState
        val END_OF_LIST: NetworkingState

        init {
            LOADING = NetworkingState(Status.LOADING, "Loading")
            LOADED = NetworkingState(Status.SUCCESS, "Success")
            ERROR = NetworkingState(Status.FAILED, "Something wen error")
            END_OF_LIST = NetworkingState(Status.FAILED, "You have reached to end of the list")
        }
    }


}