package com.firpy.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CrudRepository<EntityType extends Identifiable<IdType>, IdType>
{
	public void save(EntityType entity)
	{
		entities.put(entity.getId(), entity);
	}

	public List<EntityType> findAll()
	{
		return entities.values()
				       .stream()
				       .toList();
	}

	public EntityType findById(IdType id)
	{
		return entities.get(id);
	}

	public void delete(IdType id)
	{
		entities.remove(id);
	}

	public List<EntityType> findManyByPredicate(Predicate<EntityType> predicate)
	{
		return entities.values()
				       .stream()
				       .filter(predicate)
				       .toList();
	}

	public Optional<EntityType> findFirstByPredicate(Predicate<EntityType> predicate)
	{
		return entities.values()
				       .stream()
				       .filter(predicate)
				       .findFirst();
	}


	private final HashMap<IdType, EntityType> entities = new HashMap<>();
}
