// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:4
  RecipeController_0: controllers.RecipeController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:4
    RecipeController_0: controllers.RecipeController
  ) = this(errorHandler, RecipeController_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, RecipeController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes""", """controllers.RecipeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes/""" + "$" + """id<[^/]+>""", """controllers.RecipeController.show(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes""", """controllers.RecipeController.create"""),
    ("""PATCH""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes/""" + "$" + """id<[^/]+>""", """controllers.RecipeController.update(id:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes/""" + "$" + """id<[^/]+>""", """controllers.RecipeController.delete(id:Long)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:4
  private[this] lazy val controllers_RecipeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes")))
  )
  private[this] lazy val controllers_RecipeController_index0_invoker = createInvoker(
    RecipeController_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "index",
      Nil,
      "GET",
      this.prefix + """recipes""",
      """ Routes
 This file defines all application routes (Higher priority routes first)
 ~~~~""",
      Seq()
    )
  )

  // @LINE:5
  private[this] lazy val controllers_RecipeController_show1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_show1_invoker = createInvoker(
    RecipeController_0.show(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "show",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """recipes/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:6
  private[this] lazy val controllers_RecipeController_create2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes")))
  )
  private[this] lazy val controllers_RecipeController_create2_invoker = createInvoker(
    RecipeController_0.create,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "create",
      Nil,
      "POST",
      this.prefix + """recipes""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_RecipeController_update3_route = Route("PATCH",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_update3_invoker = createInvoker(
    RecipeController_0.update(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "update",
      Seq(classOf[Long]),
      "PATCH",
      this.prefix + """recipes/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_RecipeController_delete4_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_delete4_invoker = createInvoker(
    RecipeController_0.delete(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "delete",
      Seq(classOf[Long]),
      "DELETE",
      this.prefix + """recipes/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:4
    case controllers_RecipeController_index0_route(params@_) =>
      call { 
        controllers_RecipeController_index0_invoker.call(RecipeController_0.index)
      }
  
    // @LINE:5
    case controllers_RecipeController_show1_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_RecipeController_show1_invoker.call(RecipeController_0.show(id))
      }
  
    // @LINE:6
    case controllers_RecipeController_create2_route(params@_) =>
      call { 
        controllers_RecipeController_create2_invoker.call(RecipeController_0.create)
      }
  
    // @LINE:7
    case controllers_RecipeController_update3_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_RecipeController_update3_invoker.call(RecipeController_0.update(id))
      }
  
    // @LINE:8
    case controllers_RecipeController_delete4_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_RecipeController_delete4_invoker.call(RecipeController_0.delete(id))
      }
  }
}
