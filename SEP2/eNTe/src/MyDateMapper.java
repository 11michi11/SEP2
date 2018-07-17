import model.MyDate;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;

//Class for custom type for hibernate mapping
//Maps MyDate to Timestamp in both ways

public class MyDateMapper implements UserType {

    private static final int[] SQL_TYPE = new int[]{Types.TIMESTAMP};

    @Override
    public int[] sqlTypes() {
        return SQL_TYPE;
    }

    @Override
    public Class returnedClass() {
        return MyDate.class;
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
            Timestamp timestamp = rs.getTimestamp(strings[0]);
            return MyDate.convertFromTimestampToMyDate(timestamp);
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value != null && st != null) {
            MyDate date = (MyDate) value;
            Timestamp timestamp = MyDate.convertFromMyDateToTimestamp(date);
            st.setTimestamp(index, timestamp);
        } else
            st.setNull(index, SQL_TYPE[0]);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value == null ? null : ((MyDate)value).copy();
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
