package com.yzr.anidoroido.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: AnidoroidoDispatcher)

enum class AnidoroidoDispatcher {
    Default,
    IO,
}