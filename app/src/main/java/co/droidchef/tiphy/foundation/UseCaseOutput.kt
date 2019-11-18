package co.droidchef.spyspanner.arch

sealed class UseCaseOutput<out Result, out Error : Throwable> {
    data class Success<out R>(val result: R) : UseCaseOutput<R, Nothing>()
    data class Failure<out E : Throwable>(val error: E) : UseCaseOutput<Nothing, E>()
}