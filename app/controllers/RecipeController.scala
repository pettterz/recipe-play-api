package controllers

import java.sql.Date
import dao.DisplayRecipeForm
import models.{DisplayRecipe, Recipe}

import javax.inject.Inject
import play.api.Logger
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.OFormat.oFormatFromReadsAndOWrites
import play.api.libs.json._
import play.api.mvc.{Action, _}
import services.RecipeService
import play.api.data.format.Formats.dateFormat

import java.util.Calendar
import scala.concurrent.{ExecutionContext, Future}

/**
 * Takes HTTP requests and produces JSON.
 */
class RecipeController @Inject()(cc: ControllerComponents, recipeService: RecipeService)(
  implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  private val logger = Logger(getClass)

  implicit val recipeFormat = Json.format[Recipe]
//  implicit val recipeFormat4 = Json.writes[Recipe]
  implicit val recipeFormat2 = Json.format[DisplayRecipe]
  implicit val recipeFormat3 = Json.writes[DisplayRecipe]
  implicit val format = dateFormat("yyyy-MM-dd H:mm:ss")


  /**
   * List all
   * @return
   */
  def index: Action[AnyContent] = Action.async { implicit request =>
    logger.trace("index: ")

    recipeService.listAll().map { recipes =>
      val allResults = recipes.map(_.toDisplay)
      val res = Json.obj("recipes" -> allResults)
      Ok(Json.toJson(res))
    }
  }

  /**
   * Show entry
   * @param id
   * @return
   */
  def show(id: Long): Action[AnyContent] = Action.async {
    implicit request =>
      logger.trace(s"show: id = $id")

      recipeService.get(id).map(recipe => {
        if (recipe.isEmpty) NotFound("not exist")
        else {
          val result = Json.obj(
            "message" -> "Recipe details by id",
            "recipe" -> Seq(recipe.get.toDisplay)
          )
          Ok(Json.toJson(result))
        }
      })
  }

  /**
   * Post to create
   * @return
   */
  def create: Action[AnyContent] = Action.async { implicit request =>
    DisplayRecipeForm.form.bindFromRequest().fold(
      errorFrom => {
        errorFrom.errors.foreach(e => logger.trace(e.message))
        val errorMessage = Json.obj(
          "message" -> "Recipe creation failed!",
          "required" -> "title, making_time, serves, ingredients, cost"
        )
        Future.successful(Ok(Json.toJson(errorMessage)))
      },
      data => {
        val now = Calendar.getInstance().getTime
        val nowDate = new Date(now.getTime)
        val newRecipe = Recipe(null, data.title, data.making_time, data.serves, data.ingredients, data.cost, Option(nowDate), Option(nowDate))
        recipeService.insert(newRecipe).flatMap(
          id => {
            if (id < 0) {
              {
                val errorMessage = Json.obj(
                  "message" -> "Recipe creation failed!",
                  "required" -> "title, making_time, serves, ingredients, cost"
                )
                Future.successful(Ok(Json.toJson(errorMessage)))
              }
            } else {
              // success
              recipeService.get(id).map(recipe => {
//                val json = Json.obj("recipe" -> Seq[Recipe](recipe.get))
                val result = Json.obj(
                  "message" -> "Recipe successfully created!",
                  "recipe" -> Seq(recipe.get)
                )
                Ok(Json.toJson(result))
              })
            }
          })
      }
    )
  }

  /**
   * Update entry
   * @param id
   * @return
   */
  def update(id: Long): Action[AnyContent] = Action.async { implicit request =>
    DisplayRecipeForm.form.bindFromRequest().fold(
      errorFrom => {
        errorFrom.errors.foreach(e => logger.trace(e.message))
        val errorMessage = Json.obj(
          "message" -> "Recipe creation failed!",
          "required" -> "title, making_time, serves, ingredients, cost"
        )
        Future.successful(Ok(Json.toJson(errorMessage)))
      },
      data => {
        recipeService.get(id).flatMap(
          recipe =>
            if (recipe.isEmpty) Future.successful(NotFound("No recipe found"))
            else {
              // update this receipt
              val now = Calendar.getInstance().getTime
              val nowDate = new Date(now.getTime)
              val oldRecipe = recipe.get
              val newRecipe = oldRecipe.copy(
                title = data.title,
                making_time = data.making_time,
                serves = data.serves,
                ingredients = data.ingredients,
                cost = data.cost,
                updated_at = Option(nowDate)
              )
              recipeService.update(newRecipe).map(
                n => {
                  val result = Json.obj(
                    "message" -> "Recipe successfully updated!",
                    "recipe" -> Seq(newRecipe.toDisplay)
                  )
                  Ok(Json.toJson(result))
                })
            }
        )
      }
    )
  }

  /**
   * Delete entry
   * @param id
   * @return
   */
  def delete(id: Long): Action[AnyContent] = Action.async { implicit request =>
    logger.trace("delete: ")
    recipeService.delete(id).map(res => {
      if (res > 0) {
        val result = Json.obj(
          "message" -> "Recipe successfully removed!",
        )
        Ok(Json.toJson(result))
      } else NotFound("No recipe found")
    })
  }
}
