package data

data class ViewData<T>(var status: Status = Status.INVALID, var data: T? = null)

enum class Status {
    LOADING,
    SUCCESS,
    ERROR,
    INVALID,
    EMPTY,
}