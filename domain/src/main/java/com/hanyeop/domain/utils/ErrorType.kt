package com.hanyeop.domain.utils

// api 통신 실패 시 에러 타입
enum class ErrorType {
    NETWORK,
    TIMEOUT,
    SESSION_EXPIRED,
    UNKNOWN
}