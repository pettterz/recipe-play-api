// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseRecipeController RecipeController = new controllers.ReverseRecipeController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseRecipeController RecipeController = new controllers.javascript.ReverseRecipeController(RoutesPrefix.byNamePrefix());
  }

}
