package com.compiler.dev.annotations

import java.lang.annotation.Inherited

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@MustBeDocumented
annotation class PropertySetter()
