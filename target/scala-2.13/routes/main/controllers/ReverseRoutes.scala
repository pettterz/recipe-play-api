// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:4
package controllers {

  // @LINE:4
  class ReverseRecipeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def delete(id:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "recipes/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:5
    def show(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipes/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:6
    def create: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "recipes")
    }
  
    // @LINE:7
    def update(id:Long): Call = {
      
      Call("PATCH", _prefix + { _defaultPrefix } + "recipes/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:4
    def index: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipes")
    }
  
  }


}
