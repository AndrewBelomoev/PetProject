package com.bel.petproject.model

sealed class LceState<out T> {
    data object Loading : LceState<Nothing>()
    data class Content<out T>(val data: T) : LceState<T>()
    data class Error(val throwable: Throwable) : LceState<Nothing>()
}