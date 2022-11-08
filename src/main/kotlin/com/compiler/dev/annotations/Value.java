package com.compiler.dev.annotations;


import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Value {
}
