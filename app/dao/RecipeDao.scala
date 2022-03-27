package dao

import scala.concurrent.Future
import javax.inject.Inject
import models.{DisplayRecipe, Recipe}
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.lifted.ProvenShape

import java.sql.Date
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.MySQLProfile.api._

/**
 * Request form
 */
object DisplayRecipeForm {
  val form = Form(
    mapping(
          "id" -> optional(longNumber),
      "title" -> nonEmptyText,
      "making_time" -> nonEmptyText,
      "serves" -> nonEmptyText,
      "ingredients" -> nonEmptyText,
      "cost" -> nonEmptyText

    )(DisplayRecipe.apply)(DisplayRecipe.unapply)
  )
}

/**
 * Mapping to db
 * @param tag
 */
class RecipesTable(tag: Tag) extends Table[Recipe](tag, "recipes") {

  def id: Rep[Option[Long]] = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def title: Rep[String] = column[String]("title")
  def making_time: Rep[String] = column[String]("making_time")
  def serves: Rep[String] = column[String]("serves")
  def ingredients: Rep[String] = column[String]("ingredients")
  def cost: Rep[String] = column[String]("cost")
  def created_at = column[Option[Date]]("created_at")
  def updated_at = column[Option[Date]]("updated_at")

  /**
   * Binding
   * @return
   */
  override def * : ProvenShape[Recipe] =
    (id, title, making_time, serves, ingredients, cost, created_at, updated_at) <> (Recipe.tupled, Recipe.unapply)
}

/**
 * Recipe DAO
 * @param dbConfigProvider provider
 * @param executionContext context
 */
class RecipeDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  private val Recipes = TableQuery[RecipesTable]

  /**
   * Get all from DB
   * @return
   */
  def all(): Future[Seq[Recipe]] = db.run(Recipes.result)

  /**
   * Get a specific
   * @param id id
   * @return
   */
  def get(id: Long): Future[Option[Recipe]] = db.run(Recipes.filter(r => r.id === id).result.headOption)

  /**
   * Insert into DB
   * @param recipe recipe
   * @return
   */
  def insert(recipe: Recipe): Future[Long] = db.run(Recipes returning Recipes.map(_.id) += recipe)
    .map(r => r.getOrElse(-1L))
    .recover {
      case ex: Exception => {
        printf(ex.getMessage)
        ex.getMessage
        -1L
      }
    }

  /**
   * Delete a record from db
   * @param id id
   * @return
   */
  def delete(id: Long): Future[Int] = db.run(Recipes.filter(r => r.id === id).delete)

  /**
   * Update the record
   * @param recipe recipe
   * @return
   */
  def update(recipe: Recipe): Future[Int] = db.run(
    Recipes.filter(_.id === recipe.id)
      .map(r => (r.title, r.making_time, r.serves, r.ingredients, r.cost))
      .update(recipe.title, recipe.making_time, recipe.serves, recipe.ingredients, recipe.cost)
  )
}