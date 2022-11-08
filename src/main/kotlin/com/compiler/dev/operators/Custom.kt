package com.compiler.dev.operators

import com.compiler.dev.annotations.Feature
import com.compiler.dev.annotations.Operator
import com.compiler.dev.annotations.Operator.Types
import com.compiler.dev.structures.Empty
import com.compiler.dev.structures.Map


@Feature
@Operator
 fun delete(obj : Map<Any>, name : String): () -> Unit = {
    obj[name] = Empty()
}

@Feature
@Operator(Types.Argument)
fun print(obj : Any): () -> Unit = {
    print(obj)
}
