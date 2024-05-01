package com.firpy.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CrudRepository<EntityType, IdType>
{
	public void save(IdType id, EntityType entity)
	{
		entities.put(id, entity);
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
