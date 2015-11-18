package webapp.dao;

import webapp.domain.TextString;

import java.io.IOException;
import java.util.List;

/** This interface offers API (CRUD methods) for working with data storage
 * @author ioann
 * @version 1.0
 */
public interface TextStringFileDao {

    Long create(TextString textString);

    TextString read(Long id);

    boolean update(TextString textString);

    boolean delate(TextString textString);

    List<TextString> findAll();

    void save();

}
