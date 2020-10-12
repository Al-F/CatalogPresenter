package com.example.imageloader.ui.error

sealed class Failure(val error: Throwable) {
    class ServerError(error: Throwable) : Failure(error)
}