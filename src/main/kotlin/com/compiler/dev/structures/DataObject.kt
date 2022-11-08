package com.compiler.dev.structures
import org.json.JSONObject



open class DataProperties : Map<String>(){


    fun getProperty(name : String): Any? {
        return this[name];
    }

    open fun setProperty(name : String, value : Any?): Any? {
        this[name] = value
        return value
    }

    fun removeProperty(name : String) : Any? {
        return this.set(name, Empty())
    }

}

open class DataObject(private val id : String? = null, private val properties : DataProperties = DataProperties()) : JSONObject(){

    open fun getId(): String? {
        return id;
    }


    override operator fun get(name: String): Any? {
        return this.properties.getProperty(name)
    }

    operator fun set(name: String, value: Any?): Any? {
        this.put(name, value)
        this.properties.setProperty(name, value)
        return value
    }

    operator fun set(name : String, value : Empty): Any? {
        this.remove(name)
        this.properties.removeProperty(name)
        return value
    }

    fun getProperties(): DataProperties {
        return properties;
    }

    fun getProperty(name : String): Any? {
        return this[name]
    }

    fun setProperty(name : String, value : Any? = Empty()): Any? {
        this[name] = value
        return value
    }

    fun deleteProperty(name : String){
        this[name] = Empty()
    }

    override fun toString() : String{
        return """
            #DataObject {
            #  id: $id
            #  properties: $properties
            #}""".trimMargin("#")
    }

    fun toJson(): String {
        return super.toString()
    }
}