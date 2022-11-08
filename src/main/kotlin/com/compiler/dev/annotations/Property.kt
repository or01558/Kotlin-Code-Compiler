package com.compiler.dev.annotations

import java.lang.annotation.Inherited

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@MustBeDocumented
annotation class Property()
