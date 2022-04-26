package com.hanyeop.domain.usecase

import com.hanyeop.domain.repository.MainRepository
import javax.inject.Inject

class GetStatisticsUseCase @Inject constructor(
    private val mainRepository: MainRepository
){
    fun execute() = mainRepository.getStatistics()
}