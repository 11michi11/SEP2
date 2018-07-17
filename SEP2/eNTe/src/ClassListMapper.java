import model.ClassNo;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//Class for custom type for hibernate mapping
//Maps List<ClassNo> to String[] in both ways
//Use as example for future custom types

public class ClassListMapper implements UserType {

    private static final int[] SQL_TYPE = new int[]{Types.ARRAY};

    @Override
    public int[] sqlTypes() {
        return SQL_TYPE;
    }

    @Override
    public Class returnedClass() {
        return List.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return o == null ? o1 == null : o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o == null ? 0 : o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        if (strings != null && strings.length > 0 && rs != null && rs.getArray(strings[0]) != null) {
            String[] results = (String[]) rs.getArray(strings[0]).getArray();
            List<ClassNo> classes = new LinkedList<>();

            for (String result : results)
                classes.add(ClassNo.valueOf(result));

            return classes;
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value != null && st != null) {
            List<ClassNo> classes = (List<ClassNo>) value;
            List<String> strings = classes.stream().map(ClassNo::getValue).collect(Collectors.toList());

            String[] castObject = new String[strings.size()];
            strings.toArray(castObject);

            Array array = session.connection().createArrayOf("text", castObject);
            st.setArray(index, array);
        } else
            st.setNull(index, SQL_TYPE[0]);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value == null ? null : new LinkedList<>((List<ClassNo>) value);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return serializable;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
