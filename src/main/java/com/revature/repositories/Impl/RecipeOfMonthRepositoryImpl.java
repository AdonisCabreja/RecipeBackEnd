package com.revature.repositories.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.RecipeOfMonth;
import com.revature.repositories.RecipeOfMonthRepository;
import com.revature.util.HibernateUtil;

@Repository
public class RecipeOfMonthRepositoryImpl implements RecipeOfMonthRepository {

	@Override
	public List<RecipeOfMonth> getRecipesOfMonth() {
		
		try(Session s = HibernateUtil.getSession()) {
			
			String hql = "from RecipeOfMonth";
			Query<RecipeOfMonth> q = s.createQuery(hql, RecipeOfMonth.class);
			List<RecipeOfMonth> recipes = q.getResultList();
			return recipes;
		}
	}

	@Override
	public List<RecipeOfMonth> getRecipesByMonth(int month) {
		
		try(Session s = HibernateUtil.getSession()) {
			
			String hql = "from RecipeOfMonth where month = :monthVar";
			Query<RecipeOfMonth> q = s.createQuery(hql, RecipeOfMonth.class);
			q.setParameter("monthVar", month);
			List<RecipeOfMonth> recipesByMonth = q.getResultList();			
			return recipesByMonth;
		}
	}
}
