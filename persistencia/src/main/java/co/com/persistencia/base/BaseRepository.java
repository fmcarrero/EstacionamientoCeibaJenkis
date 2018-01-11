package co.com.persistencia.base;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

public abstract class BaseRepository<F, T> {
	
	private Class<T> toClazz;
	private Class<F> fromClazz;
	private Mapper mapper;
	
	public BaseRepository(Class<T> toClazz) {
		this.toClazz = toClazz;
		this.mapper  = DozerBeanMapperSingletonWrapper.getInstance(); 
	}
	
	public BaseRepository(Class<T> toClazz, Class<F> fromClazz) {
		this.toClazz = toClazz;
		this.fromClazz = fromClazz;
		this.mapper  = DozerBeanMapperSingletonWrapper.getInstance(); 
	}
	
	public T to(F from){
		T mapped = null;
		if(from != null) {
			mapped = mapper.map(from, toClazz);
		}
		return mapped;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List to(List listToConvert){
		List listConverted = new ArrayList<>();
		for (Object object : listToConvert) {
			listConverted.add(to((F) object));
		}
		return listConverted;
	}
	
	public F from(T to) {
		F mapped = null;
		if(to != null) {
			mapped = mapper.map(to, fromClazz);
		}
		return mapped;
	}
}
