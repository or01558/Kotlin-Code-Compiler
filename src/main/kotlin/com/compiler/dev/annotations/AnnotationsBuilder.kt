package com.compiler.dev.annotations

import java.io.DataInputStream
import java.io.File
import java.io.IOException
import java.lang.reflect.*
import kotlin.collections.HashMap

data class AnnotationData(val annotation : Annotation, val target : Any)
class AnnotationsBuilder(parent: ClassLoader?, private val classes: HashMap<Class<*>, HashMap<String, AnnotationData>> = HashMap()) : ClassLoader(parent){

    operator fun invoke(name : String){
        println(name)
    }

   /*  operator fun invoke(vararg args : Any){
         for(arg in args) {
             println(arg)
         }
    }*/

    fun test(name : String){

    }

    fun test2(name : String, id: Int?){

    }

    /**
     * Loads the class from the file system. The class file should be located in
     * the file system. The name should be relative to get the file location
     *
     * @param name
     * Fully Classified name of the class, for example, com.journaldev.Foo
     */
    @Throws(ClassNotFoundException::class)
    private fun getClass(name: String): Class<*>? {
        val file = name.replace('.', File.separatorChar) + ".class"
        var b: ByteArray? = null
        return try {
            // This loads the byte code data from the file
            b = loadClassFileData(file)
            // defineClass is inherited from the ClassLoader class
            // that converts byte array into a Class. defineClass is Final
            // so we cannot override it
            val c: Class<*> = defineClass(name, b, 0, b!!.size)
            resolveClass(c)
            c
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Every request for a class passes through this method. If the class is in
     * com.journaldev package, we will use this classloader or else delegate the
     * request to parent classloader.
     *
     *
     * @param name
     * Full class name
     */
    @Throws(ClassNotFoundException::class)
    override fun loadClass(name: String): Class<*>? {
        println("Loading Class '$name'")
        if (name.startsWith("com.journaldev")) {
            println("Loading Class using CCLoader")
            return getClass(name)
        }
        return super.loadClass(name)
    }

    /**
     * Reads the file (.class) into a byte array. The file should be
     * accessible as a resource and make sure that it's not in Classpath to avoid
     * any confusion.
     *
     * @param name
     * Filename
     * @return Byte array read from the file
     * @throws IOException
     * if an exception comes in reading the file
     */
    @Throws(IOException::class)
    private fun loadClassFileData(name: String): ByteArray? {
        val stream = javaClass.classLoader.getResourceAsStream(
            name
        )
        val size = stream.available()
        val buff = ByteArray(size)
        val `in` = DataInputStream(stream)
        `in`.readFully(buff)
        `in`.close()
        return buff
    }

    private fun addAnnotation(Class : Class<*>, name : String, data: AnnotationData): HashMap<String, AnnotationData>? {
       return if(classes.containsKey(Class)) {
            val annotationsList = classes[Class]
            annotationsList?.set(name, data)
            annotationsList
        } else {
            val annotationsList = HashMap<String, AnnotationData>()
            annotationsList[name] = data
            classes[Class] = annotationsList
           annotationsList
        }
    }
    fun load() {

        //\dataobjects\User.kt
        val packageName = "com.wsaBuilder.backend.api.structures.dataobjects.properties"
        val root =  System.getProperty("user.dir")
        val directory = "src\\main\\kotlin\\${packageName.replace(".", "\\")}"
        val path = "$root\\$directory"
        val file = File(path)
        println(path)
        println(file.exists())
           if (file.isDirectory) {
               file.listFiles().forEach { file ->
                   val propertiesClass =  this.loadClass("$packageName.${file.name.replace(".kt","")}")
                   if (propertiesClass != null) {

                      propertiesClass.declaredConstructors.forEach { constructor ->
                          println("Constructor $constructor")

                          constructor.parameters.forEach { parameter ->
                              parameter.annotations.forEach { annotation ->
                                  println("Parameter " + parameter.name)
                                  addAnnotation(propertiesClass, parameter.name, AnnotationData(annotation, parameter))
                                  println("Annotation " + annotation.annotationClass.simpleName)
                              }
                          }

                          constructor.annotations.forEach { annotation ->
                              val specialName = "constructor${propertiesClass.declaredConstructors.indexOf(constructor) + 1}"
                              addAnnotation(propertiesClass, specialName, AnnotationData(annotation, constructor))
                          }
                      }

                     propertiesClass.declaredFields.forEach { field ->
                         println("Field " + field.name)

                         field.annotations.forEach { annotation ->
                             addAnnotation(propertiesClass, field.name, AnnotationData(annotation, field))
                             println("Annotation " + annotation.annotationClass.simpleName)
                         }
                     }

                     propertiesClass.declaredMethods.forEach { method ->
                         println("Method " + method.name)
                         method.parameters.forEach { parameter ->
                             println("Parameter " + parameter.name)
                             parameter.annotations.forEach { annotation ->
                                 addAnnotation(propertiesClass, parameter.name, AnnotationData(annotation, parameter))
                                 println("Annotation " + annotation.annotationClass.simpleName)
                             }
                         }

                         method.annotations.forEach { annotation ->
                             addAnnotation(propertiesClass, method.name, AnnotationData(annotation, method))
                             println("Annotation " + annotation.annotationClass.simpleName)
                         }
                     }
                 /*     val properties = propertiesClass.fields
                       properties.forEach { field ->
                           println("Ccc" + field.name)
                           println("Ccc" + field.annotations.size)
                       }*/
                   }
               }
        }
    }

    fun buildAnnotaions(proxy: Any?, method: Method?, args: Array<out Any>?) {

    }

}


@Throws(ClassNotFoundException::class)
fun main(args: Array<String>) {
    val path = "com.wsaBuilder.backend.api.structures.dataobjects.User"
    val ccl = AnnotationsBuilder(
        AnnotationsBuilder::class.java.classLoader
    )
    ccl.test("Or")
   // ccl.load()
   // val clas = ccl.loadClass(path)
   // println(clas)
}