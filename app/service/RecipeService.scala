package service

import dao.RecipeDao
import models.Recipe

import javax.inject.Inject
import scala.concurrent.Future


class RecipeService @Inject()(recipeDao: RecipeDao) {
  def insert(recipe: Recipe): Future[Long] = recipeDao.insert(recipe)

  def delete(id: Long): Future[Int] = recipeDao.delete(id)

  def get(id: Long): Future[Option[Recipe]] = recipeDao.get(id)

  def listAll(): Future[Seq[Recipe]] = recipeDao.all()

  def update(newRecipe: Recipe): Future[Int] = recipeDao.update(newRecipe)
}
