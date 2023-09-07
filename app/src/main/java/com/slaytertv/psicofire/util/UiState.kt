package com.slaytertv.psicofire.util

//para los estados de la app
//cuando el viewmodel nos devuelve un resultado a la view, nos da uno de estos casos
sealed class UiState<out T> {
    //cuando finaliza no devuelve nada
    object Finished: UiState<Nothing>()
    //cuando carga no devuelve nada
    object Loading: UiState<Nothing>()
    //cuando es satisfactorio nos devuelvte un valor T (q significa cualqueir tipo de dato)
    data class Sucess<out T>(val data:T): UiState<T>()
    //si falla nos devuelve un string con el error
    data class Failure(val error:String?): UiState<Nothing>()
}