package me.jclagache.data.mybatis.repository.query;

import java.lang.reflect.Method;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;

public class DeclaredQueryLookupStrategy implements QueryLookupStrategy {

	private final SqlSessionTemplate sessionTemplate;

	public DeclaredQueryLookupStrategy(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	protected RepositoryQuery resolveQuery(MyBatisQueryMethod method, SqlSessionTemplate sessionTemplate,
			NamedQueries namedQueries) {
		return new MyBatisQuery(method, sessionTemplate);
	}

	@Override
	public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, ProjectionFactory projectionFactory,
			NamedQueries namedQueries) {
		return resolveQuery(new MyBatisQueryMethod(method, metadata, projectionFactory), sessionTemplate, namedQueries);
	}
}