package org.akshev.health_score

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform