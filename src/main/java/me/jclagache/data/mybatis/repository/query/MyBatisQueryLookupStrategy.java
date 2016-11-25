package me.jclagache.data.mybatis.repository.query;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.data.repository.query.RepositoryQuery;

import java.lang.reflect.Method;

/**
 *
 * TODO: add support @Query annotation
 */
public class MyBatisQueryLookupStrategy {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private MyBatisQueryLookupStrategy() {
	}

	/**
	 * Creates a {@link QueryLookupStrategy} for the given
	 * {@link SqlSessionTemplate} and {@link Key}.
	 * 
	 * @param sessionTemplate
	 * @param key
	 * @return
	 */
	public static QueryLookupStrategy create(SqlSessionTemplate sessionTemplate, Key key) {
		if (key == null) {
			return new DeclaredQueryLookupStrategy(sessionTemplate);
		}
		if (Key.USE_DECLARED_QUERY.equals(key)) {
			return new DeclaredQueryLookupStrategy(sessionTemplate);
		} else {
			throw new IllegalArgumentException(String.format("Unsupported query lookup strategy %s!", key));
		}
	}

}
