package com.compiler.dev.annotations


@Target(AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class Operator(val type : Types = Types.Key){
        enum class Types {
            Key,
            Object,
            Argument
        }
}


