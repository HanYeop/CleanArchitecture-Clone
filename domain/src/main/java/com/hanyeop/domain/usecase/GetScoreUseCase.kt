package com.hanyeop.domain.usecase

import com.hanyeop.domain.repository.MainRepository
import javax.inject.Inject

class GetScoreUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun execute() = mainRepository.getScore()
}