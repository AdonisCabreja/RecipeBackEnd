package com.revature.repositories;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.Recipe;
import com.revature.util.HibernateUtil;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository {
	
	@Override
	public List<Recipe> getRecipes() {
		
		try(Session s = HibernateUtil.getSession()) {
			
			String hql = "from Recipe";
			Query<Recipe> q = s.createQuery(hql, Recipe.class);
			List<Recipe> recipes = q.getResultList();
			return recipes;
		}
	}
	
	@Override
	public List<Recipe> getRecipesByUserId(int user_id) {
		
		try(Session s = HibernateUtil.getSession()) {
			
			String hql = "from Recipe where user_id = :idVar";
			Query<Recipe> q = s.createQuery(hql, Recipe.class);
			q.setParameter("idVar", user_id);
			List<Recipe> r = q.getResultList();
			return r;
		}
	}

	@Override
	public List<Recipe> getFavoriteRecipesByUserId(int user_id) {
		
		try(Session s = HibernateUtil.getSession()) {
			
			String hql = "from Recipe where user_id = :idVar and isFavorite = true";
			Query<Recipe> q = s.createQuery(hql, Recipe.class);
			q.setParameter("idVar", user_id);
			List<Recipe> r = q.getResultList();
			return r;
		}
	}

	@Override
	public int addRecipe(Recipe r) {
		
		int recipeCreated = 0;
		try(Session s = HibernateUtil.getSession()) {
			
			Transaction tx = s.beginTransaction();
			recipeCreated = (int) s.save(r);
			tx.commit();
			return recipeCreated;
			
		}
	}

	@Override
	public void deleteRecipe(int recipe_id) {
		
		try(Session s = HibernateUtil.getSession()) {
			
			Transaction tx = s.beginTransaction();
			s.delete(new Recipe(recipe_id));
			tx.commit();
		}
	}

}
