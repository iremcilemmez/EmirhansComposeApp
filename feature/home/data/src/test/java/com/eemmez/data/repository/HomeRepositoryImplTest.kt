package com.eemmez.data.repository

import com.eemmez.common.domain.entity.Result
import com.eemmez.data.fake.FakeHomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeRepositoryImplTest {
    private val homeRepository = FakeHomeRepository()

    @Before
    fun setup() {
        homeRepository.setCurrentPage(0)
    }

    @Test
    fun `get success response from getList`() {
        runTest {
            val result = homeRepository.getList(0, null)
            assert(result is Result.Success)
        }
    }

    @Test
    fun `get 10 element for each getList`() {
        runTest {
            val result = homeRepository.getList(0, "")
            assert(result.data?.list?.size == 10)
        }
    }

    @Test
    fun `get error if pageNumber parameter is null for getList`() {
        runTest {
            val result = homeRepository.getList(null, "")
            assert(result is Result.Error)
        }
    }

    @Test
    fun `search specific element`() {
        runTest {
            val searchString = "kedi12"
            val result = homeRepository.getList(0, searchString)
            assert(result.data?.list?.firstOrNull { it.name == searchString } != null)
        }
    }
}