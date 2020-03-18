package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;
	public String date[][];

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " = ?");
		//System.out.println(sb.toString());
		return sb.toString();
	}

	private String createInsertQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT ");
		sb.append("INTO ");
		sb.append(type.getSimpleName());
		sb.append(" VALUES (");
		for (Field f : type.getDeclaredFields()) {
			sb.append("?,");
		}
		sb.setLength(sb.length() - 1);
		sb.append(")");
		// System.out.println(sb.toString());
		return sb.toString();
	}

	private String createUpdateQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		for (Field f : type.getDeclaredFields()) {
			sb.append(f.getName() + "=? , ");
		}
		sb.setLength(sb.length() - 2); // ca sa renunt la ultima virgula
		sb.append("WHERE " + field + "=?");
		// System.out.println(sb.toString());
		return sb.toString();
	}

	private String createDeleteQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		//System.out.println(sb.toString());
		return sb.toString();
	}

	private String createSelectAllQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		// System.out.println(sb.toString());
		return sb.toString();
	}

	public T insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		int resultSet = 0;
		String query = createInsertQuery();
		try {
			int index = 1;
			// System.out.println(query);
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			for (Field f : type.getDeclaredFields()) {
				// System.out.println("la1");
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), type);
				// System.out.println("la2");
				Method method = propertyDescriptor.getReadMethod();
				// System.out.println(method.getName());
				Object s = method.invoke(t);
				statement.setObject(index, s);
				index++;
			}
			// System.out.println(query);
			// System.out.println(query);
			resultSet = statement.executeUpdate();
			// statement.executeUpdate();
			return t;
		} catch (SQLException | IntrospectionException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			LOGGER.log(Level.WARNING, "DAO:insert " + e.getClass() + e.getMessage());
		} finally {
			// ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	public T update(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		int resultSet = 0;
		String query = createUpdateQuery("id");
		try {
			int index = 1;
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			for (Field f : type.getDeclaredFields()) {
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), type);
				Method method = propertyDescriptor.getReadMethod();
				Object s = method.invoke(t);
				statement.setObject(index, s);
				// System.out.println(f.getName());
				index++;
			}
			for (Field f : type.getDeclaredFields()) {
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), type);
				Method method = propertyDescriptor.getReadMethod();
				Object s = method.invoke(t);
				statement.setObject(index, s);
				// System.out.println(f.getName());
				break;
			}
			resultSet = statement.executeUpdate();
			return t;
		} catch (SQLException | IntrospectionException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
		} finally {
			// ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	public int delete(int id) {

		Connection connection = null;
		PreparedStatement statement = null;
		// ResultSet resultSet = null;
		String query = createDeleteQuery("id");

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			return 1;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
		} finally {
			// ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return 0;
	}

	public List<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ResultSet r = null;
		String query = createSelectAllQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
            r = resultSet;
            date = new String[50][40];
			int j = 0;
			while (resultSet.next()) {
				for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
					//System.out.println(resultSet.getMetaData().getColumnCount());
					Object o = resultSet.getObject(i);
					//System.out.println(o.toString());
					if(o!= null) {
					date[j][i - 1] = o.toString();
					//System.out.println(date[j][i-1]);
					}
					else
						date[j][i-1]="0";
				}
				//System.out.println("la");
				j++;
			}
            date[j+1]=null;
			return createObjects(r);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO : findall " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
       //System.out.println(type.getName());
		try {
			while (resultSet.next()) {
				 //System.out.println("de ce nu merge");
				T instance = type.newInstance();
				//System.out.println("AICI" + type.getName());
				//for (Field field : type.getDeclaredFields()) {
					//System.out.println(field);
				//}
				for (Field field : type.getDeclaredFields()) {
					// System.out.println("1");
					 //System.out.println(field.getName());
					Object value = resultSet.getObject(field.getName());
					 //System.out.println("2");
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					 //System.out.println("3");
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (IllegalAccessException | SQLException | InstantiationException | IntrospectionException
				| InvocationTargetException e) {
			LOGGER.log(Level.WARNING, type.getName() + "AICI E PROBLEMA " + e.getMessage());
		}
		return list;
	}

}
