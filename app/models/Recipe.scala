package models

import java.sql.Date

case class DisplayRecipe(
                          id: Option[Long] = null,
                          title: String,
                          making_time: String,
                          serves: String,
                          ingredients: String,
                          cost: String
                        )

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
  def toDisplay: DisplayRecipe = DisplayRecipe(id, title, making_time, serves, ingredients, cost)
}

