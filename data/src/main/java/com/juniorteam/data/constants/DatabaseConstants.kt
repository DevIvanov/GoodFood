package com.juniorteam.data.constants

object DatabaseConstants {

    const val DATABASE_NAME = "article_database"

    const val RECIPES_TABLE = "recipes_table"
    const val INGREDIENTS_TABLE = "ingredients_table"
    const val PRODUCTS_TABLE = "products_table"

    const val QUERY_READ_RECIPES = "SELECT * FROM $RECIPES_TABLE ORDER BY id ASC"
    const val QUERY_DELETE_RECIPES = "DELETE FROM $RECIPES_TABLE"

    const val QUERY_READ_INGREDIENTS = "SELECT * FROM $INGREDIENTS_TABLE ORDER BY id ASC"
    const val QUERY_DELETE_INGREDIENTS = "DELETE FROM $INGREDIENTS_TABLE"

    const val QUERY_READ_PRODUCTS = "SELECT * FROM $PRODUCTS_TABLE ORDER BY id ASC"
    const val QUERY_DELETE_PRODUCTS = "DELETE FROM $PRODUCTS_TABLE"

}