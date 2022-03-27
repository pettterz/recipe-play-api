package services

import dao.RecipeDao
import models.Recipe

import javax.inject.Inject
import scala.concurrent.Future

/**
 * Service to handle request
 * @param recipeDao Recipe DAO
 */
class RecipeService @Inject()(recipeDao: RecipeDao) {
  /**
   * Insert an object
   * @param recipe recipe
   * @return
   */
  def insert(recipe: Recipe): Future[Long] = recipeDao.insert(recipe)

  /**
   * Delete by id
   * @param id id
   * @return
   */
  def delete(id: Long): Future[Int] = recipeDao.delete(id)

  /**
   * Get by id
   * @param id id
   * @return
   */
  def get(id: Long): Future[Option[Recipe]] = recipeDao.get(id)

  /**
   * List all
   * @return
   */
  def listAll(): Future[Seq[Recipe]] = recipeDao.all()

  /**
   * Update record
   * @param newRecipe new record
   * @return
   */
  def update(newRecipe: Recipe): Future[Int] = recipeDao.update(newRecipe)
}
