package models

import java.sql.Date

/**
 * Datatype to Show
 * @param id id
 * @param title title
 * @param making_time making time
 * @param serves servers
 * @param ingredients ingredients
 * @param cost cost
 */
case class DisplayRecipe(
                          id: Option[Long] = null,
                          title: String,
                          making_time: String,
                          serves: String,
                          ingredients: String,
                          cost: String
                        )

/**
 * Original type to db
 * @param id id
 * @param title title
 * @param making_time making time
 * @param serves servers
 * @param ingredients ingredients
 * @param cost cost
 * @param created_at created at
 * @param updated_at updated at
 */
case class Recipe(
                   id: Option[Long] = null,
                   title: String,
                   making_time: String,
                   serves: String,
                   ingredients: String,
                   cost: String,
                   created_at: Option[Date],
                   updated_at: Option[Date]
                 ) {
  /**
   * Convert to show type
   * @return
   */
  def toDisplay: DisplayRecipe = DisplayRecipe(id, title, making_time, serves, ingredients, cost)
}

