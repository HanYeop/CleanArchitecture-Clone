package com.hanyeop.domain.usecase

import com.hanyeop.domain.repository.SplashRepository
import javax.inject.Inject

class CheckAppVersionUseCase @Inject constructor(
    private val splashRepository: SplashRepository
) {
    fun execute() = splashRepository.checkAppVersion()
}