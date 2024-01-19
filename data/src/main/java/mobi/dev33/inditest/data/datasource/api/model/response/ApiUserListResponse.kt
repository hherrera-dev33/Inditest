package mobi.dev33.inditest.data.datasource.api.model.response

import mobi.dev33.inditest.data.datasource.api.model.ApiUser

data class ApiUserListResponse(override val results: List<ApiUser>, override val info: ApiResponseInfo) : BaseApiResponse<List<ApiUser>>()
