package mobi.dev33.inditest.data.datasource.api.model.response

abstract class BaseApiResponse<T> {
    abstract val results: T
    abstract val info: ApiResponseInfo
}
