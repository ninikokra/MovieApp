package com.space.movieapp.data.repository
import MoviesPagingSource
import androidx.paging.PagingConfig
import androidx.paging.Pager
import androidx.paging.PagingData
import com.space.movieapp.data.remote.service.ServiceApi
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(
    private val serviceApi: ServiceApi
) : MoviesRepository {
    override fun getMovies(category: String, page: Int): Flow<PagingData<MoviesDomainModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(serviceApi, category) }
        ).flow
    }
}
