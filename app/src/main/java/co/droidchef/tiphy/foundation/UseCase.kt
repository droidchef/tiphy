package co.droidchef.spyspanner.arch

import io.reactivex.Single

interface UseCase<T> {
    fun invoke(): Single<UseCaseOutput<T, Throwable>>
}