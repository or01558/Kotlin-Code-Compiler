package com.compiler.dev.runtime

import com.compiler.dev.api.Feature
import com.compiler.dev.classes.Code
import com.compiler.dev.classes.IResource



/**
 * Compiler for kotlin project with high level features.
 *
 * Customize everything the way You want.
 *
 * Includes Customizable life cycle for every process at the compiler runtime.
 * Includes Customizable code's resources and code's features.
 *
 * @param root
 * The root folder path of the kotlin project must be inside the src/main/kotlin folder, for example, com/dev/myproject
 * Use . for src/main/kotlin folder
 */
class Compiler(val root : String){


  lateinit var rootFolder : String
  val features : MutableList<Feature> = mutableListOf()
  val resources : MutableList<IResource> = mutableListOf()



   /**
    * Adds new code resource
    * to the compiler's resources. The resource should be an implement the IResource interface.
    * Please note that in order for this to work you must have an Custom Resource class too.
    *
    * @param resource
    * your custom code's resource for the compiler runtime process, for example, class MyResource() : IResource
   */
  open fun addResource(resource: IResource) {
        this.resources.add(resource)
    }


    /**
     * Removes specific code resource
     * from the compiler's resources. The resource should be within the compiler's resources list.
     *
     * @param resource
     * The specific resource that you want to remove from the compiler runtime process, for example, Variable.Resource
     */
    open fun removeResource(resource: IResource) {
        this.resources.remove(resource)
    }

  /**
   * Part of the compiler's life cycle.
   * Loads a folder from the root folder. The folder should be located in
   * the root folder. The path should be relative to get the folder location
   *
   * @param path
   *The path of the folder without the rootFolder part, for example, tests
  */
 protected open fun loadFolder(path : String) {
      TODO("Implement the loadFolder method")
  }


  /**
   * Part of the compiler's life cycle.
   * Loads a file from the root folder. The file should be located in
   * the root folder. The path should be relative to get the file location
   *
   * @param path
   *The path of the file without the rootFolder part, for example, functions/MyFunctions.kt
  */
  protected open fun loadFile(path : String) {
      TODO("Implement the loadFile method")
  }


  /**
   * Part of the compiler life cycle.
   * Loads a kotlin's file code. The code should be  within
   * the a file on the root folder. The code should be relative to a kotlin's file code
   *
   * @param originalCode
   *The original code of a file , for example, var i = 1
   */
  protected fun load(originalCode : String) {

      this.validate(originalCode)
      val code = this.build(originalCode)
      code?.resource?.let { this.compile(it, code) }
  }


  /**
   * Part of the compiler's life cycle.
   * Validates a kotlin's file code. The code should be  within
   * the a file on the root folder. The code should be relative to a kotlin's file code
   * Please note that you must implement the method by yourself, does nothing for now.
   *
   * @param originalCode
   *The original code of a file , for example, var i 1
  */
  protected open fun validate(originalCode : String) {
      // empty method for custom code validation.
  }

  /**
   * Part of the compiler's life cycle.
   * build a user friendly code from kotlin's file original code. The original code should be  within
   * the a file on the root folder. The code should be relative to a kotlin's file code
   *
   * @param originalCode
   *The original code of a file , for example, var i = 1
  */
    protected open fun build(originalCode : String) : Code? {
     TODO("Implement the build method")
  }

   /**
    * Part of the compiler's life cycle.
    * compile a user friendly code to a resource object. The code should come
    * from the build function result. The code should be user friendly from kotlin's file code
    *
    * @param resource
    * The resource de of this code
    *
    * @param code
    *The user friendly code of a file , for example, Code(Line(start = 0, resource = Variable(name = "i", type = PrimitiveTypes.Int, value = 0), end = 0))
   */
  protected open fun compile(resource : IResource, code : Code?) {
      TODO("Implement the compile method")
  }
}