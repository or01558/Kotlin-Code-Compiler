package com.compiler.dev.structures

open class Map<K> : HashMap<K, Any?>() {
   operator fun set(name : K, value: Any?): Any? {
        return if(value is Empty) {
            this.remove(name)
            value
        } else {
            this.put(name, value)
            value
        }
    }
}