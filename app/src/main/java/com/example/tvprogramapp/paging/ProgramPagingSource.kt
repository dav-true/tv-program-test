package com.example.tvprogramapp.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tvprogramapp.api.ApiService
import com.example.tvprogramapp.dto.Program
import com.example.tvprogramapp.helpers.SharedPreferencesStore
import retrofit2.HttpException
import java.io.IOException

class ProgramPagingSource(
    private val service: ApiService,
    private val sharedPreferencesStore: SharedPreferencesStore
) : PagingSource<Int, Program>() {

    private var direction = 0
    private var previousPageIndex = 0

    override fun getRefreshKey(state: PagingState<Int, Program>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Program> {
        val pageIndex = params.key?.apply {
            direction = if (previousPageIndex >= this) -1 else 1
        } ?: 0
        previousPageIndex = pageIndex
        return try {
            val response = service.getProgram(
                direction = direction,
                borderId = if (direction == -1) sharedPreferencesStore.firstBorderId else sharedPreferencesStore.lastBorderId
            )
            val movies = response.items.apply {
                if (direction == -1)
                    sharedPreferencesStore.firstBorderId =
                        first().id
                else if (direction == 1)
                    sharedPreferencesStore.lastBorderId =
                        last().id
            }

            val nextPageNumber = pageIndex + 1

            LoadResult.Page(
                data = movies,
                prevKey = pageIndex - 1,
                nextKey = nextPageNumber
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true


}