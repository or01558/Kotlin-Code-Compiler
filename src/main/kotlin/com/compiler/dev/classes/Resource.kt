package com.compiler.dev.classes

open class Part(){

}

open class Declaration(): Part(){

}

open class Body(): Part(){

}

interface IResource {
    val declaration : ResourceDeclaration
    fun createNew(startLine : String, originalCode: String, lines : List<String>) : Resource?
}


open class ResourceDeclaration(){

}

open abstract class Resource(val name : String, val declaration : Declaration? = null, val body :  Body? = null, val originalCode : String = "", val code : Code? = null) {

    companion object  : IResource {
        override val declaration: ResourceDeclaration
            get() = TODO("Not yet implemented")

        override fun createNew(startLine: String, originalCode: String, lines: List<String>): Resource? {
            TODO("Not yet implemented")
        }
    }

     var lines : List<String> = ArrayList()

    init {
        lines = originalCode.split("\r\n")
    }

    abstract fun load(originalCode : String = this.originalCode)

    abstract fun build(originalCode : String) : Code?

    abstract fun compile(code : Code) : Resource?
}