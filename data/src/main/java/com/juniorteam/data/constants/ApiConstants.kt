package com.juniorteam.data.constants

object ApiConstants {
    const val BASE_URL = "https://api.spoonacular.com/"

    const val HEADER = "Content-Type: application/json"

    const val GET_RECIPES = "recipes/complexSearch"
    const val GET_INGREDIENTS = "food/ingredients/search"
    const val GET_PRODUCTS = "food/products/search"
    const val GET_RECIPE_BY_ID = "recipes/{id}/information"
    const val GET_PRODUCT_BY_ID = "food/products/{id}"
    const val GET_INGREDIENT_BY_ID = "food/ingredients/{id}/information"

    const val API_KEY_VALUE =  "525a39c85bb1487981d6e0c5048f16b3" // "88754a02515e4ea88c6fcb7d51e78504" //"13d48b6187404c3ea15e96578e7ed160"
    const val QUERY_API_KEY = "apiKey"
    const val QUERY_QUERY = "query"
    const val BASE_PATH_IMAGE = "https://spoonacular.com/cdn/ingredients_250x250/"

    const val DEFAULT_QUERY_RECIPE = "tomato"
    const val DEFAULT_QUERY_INGREDIENT = "carrot"
    const val DEFAULT_QUERY_PRODUCT = "pizza" //"apple"
}