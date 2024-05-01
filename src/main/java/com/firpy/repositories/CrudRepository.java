package com.firpy.repositories;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CrudRepository<EntityType extends Identifiable<IdType>, IdType>
{
	public void save(@NotNull EntityType entity)
	{
		entities.put(entity.getId(), entity);
	}

	public @NotNull List<EntityType> findAll()
	{
		return entities.values()
				       .stream()
				       .toList();
	}

	public @NotNull Optional<EntityType> findById(IdType id)
	{
		return Optional.ofNullable(entities.get(id));
	}

	public void delete(IdType id)
	{
		entities.remove(id);
	}

	public @NotNull List<EntityType> findManyByPredicate(Predicate<EntityType> predicate)
	{
		return entities.values()
				       .stream()
				       .filter(predicate)
				       .toList();
	}

	public @NotNull Optional<EntityType> findFirstByPredicate(Predicate<EntityType> predicate)
	{
		return entities.values()
				       .stream()
				       .filter(predicate)
				       .findFirst();
	}


	private final HashMap<IdType, EntityType> entities = new HashMap<>();
}
