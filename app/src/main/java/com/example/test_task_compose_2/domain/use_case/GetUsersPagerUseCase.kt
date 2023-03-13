package com.example.test_task_compose_2.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.test_task_compose_2.data.retrofit.repository.GitUserRepository
import com.example.test_task_compose_2.domain.paging_source.UsersPagingSource
import com.example.test_task_compose_2.util.Constants
import javax.inject.Inject

class GetUsersPagerUseCase @Inject constructor(
    private val gitUserRepository: GitUserRepository
){
    operator fun invoke() = Pager(
        config = PagingConfig(
            pageSize = Constants.USER_PER_PAGE
        ),
        pagingSourceFactory = {
            UsersPagingSource(gitUserRepository)
        }
    ).flow
}