package com.compiler.dev.classes.resources


import com.compiler.dev.classes.Code
import com.compiler.dev.classes.IResource
import com.compiler.dev.classes.Resource
import com.compiler.dev.classes.ResourceDeclaration

open class Expression(name: String) : Resource(name) {

    companion object  : IResource {
        override val declaration: ResourceDeclaration
            get() = TODO("Not yet implemented")

        override fun createNew(startLine: String, originalCode: String, lines: List<String>) : Expression?{
            TODO("Not yet implemented")
        }
    }

    override fun load(originalCode: String) {
        TODO("Not yet implemented")
    }

    override fun build(originalCode: String): Code? {
        TODO("Not yet implemented")
    }

    override fun compile(code: Code): Resource? {
        TODO("Not yet implemented")
    }

}