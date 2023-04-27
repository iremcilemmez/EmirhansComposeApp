package com.eemmez.presentation.fake

import com.eemmez.common.domain.entity.Result
import com.eemmez.home.domain.entity.GetListResponseEntity
import com.eemmez.home.domain.entity.HomeError
import com.eemmez.home.domain.entity.ListItemEntity
import com.eemmez.home.domain.repository.HomeRepository

class FakeHomeRepository : HomeRepository {
    private var currentPage = 0

    val homeItemList = mutableListOf(
        ListItemEntity("kedi1", "image1"),
        ListItemEntity("kedi2", "image2"),
        ListItemEntity("kedi3", "image3"),
        ListItemEntity("kedi4", "image4"),
        ListItemEntity("kedi5", "image5"),
        ListItemEntity("kedi6", "image6"),
        ListItemEntity("kedi7", "image7"),
        ListItemEntity("kedi8", "image8"),
        ListItemEntity("kedi9", "image9"),
        ListItemEntity("kedi10", "image10")
    )

    override suspend fun getList(
        pageNumber: Int?,
        search: String?
    ): Result<GetListResponseEntity, HomeError> {
        return if (pageNumber != null) {
            if (search != null) {
                val searchData = homeItemList.filter { it.name.contains(search) }
                val nextPageAvailable = searchData.size > (pageNumber + 1) * 10

                val list =
                    if (nextPageAvailable)
                        searchData.subList(pageNumber * PAGE_SIZE, (pageNumber + 1) * 10)
                    else if (searchData.isNotEmpty())
                        searchData
                    else
                        listOf()
                Result.Success(
                    GetListResponseEntity(
                        currentPage = currentPage,
                        nextPageAvailable = nextPageAvailable,
                        list = list
                    )
                )
            } else {
                val nextPageAvailable = homeItemList.size > (pageNumber + 1) * 10
                val list = if (nextPageAvailable) homeItemList.subList(
                    pageNumber * PAGE_SIZE,
                    (pageNumber + 1) * 10
                ) else if (homeItemList.isNotEmpty())
                    homeItemList
                else
                    listOf()
                Result.Success(
                    GetListResponseEntity(
                        currentPage = currentPage,
                        nextPageAvailable = nextPageAvailable,
                        list = list
                    )
                )
            }
        } else {
            Result.Error(HomeError.UnknownError)
        }
    }

    override suspend fun addToFavourites(listItemEntity: ListItemEntity): Result<Unit, HomeError> {
        return Result.Success(Unit)
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}