package com.compiler.dev.operators

import com.compiler.dev.annotations.Operator
import com.compiler.dev.annotations.Operator.Types
import com.compiler.dev.structures.DataObject
import com.compiler.dev.structures.Empty


 @Operator
 fun delete(obj : DataObject, name : String): () -> Unit = {
    obj[name] = Empty()
}

@Operator(Types.Object)
fun print(obj : Any): () -> Unit = {
    print(obj)
}
