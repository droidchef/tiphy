package co.droidchef.spyspanner.arch

interface UseCaseBuilder<T> {
    fun build(): UseCase<T>
}