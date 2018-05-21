package ua.danit.dao;

import java.sql.Connection;

public abstract class AbstractDAO<T>
{
	abstract public void save(T obj);

	abstract public void update(T obj);

	abstract public T get(Object pk);

	abstract public void delete(Object pk);
}
