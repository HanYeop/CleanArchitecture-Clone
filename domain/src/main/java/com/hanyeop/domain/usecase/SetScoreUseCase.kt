package com.hanyeop.domain.usecase

import com.hanyeop.domain.model.DomainScore
import com.hanyeop.domain.repository.MainRepository
import javax.inject.Inject

class SetScoreUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun execute(score: DomainScore) = mainRepository.setScore(score)
}