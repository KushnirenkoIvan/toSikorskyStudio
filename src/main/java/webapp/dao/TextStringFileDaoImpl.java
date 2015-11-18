package webapp.dao;

import org.springframework.stereotype.Repository;
import webapp.domain.TextString;
import webapp.util.FileSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents Dao for working with source file
 * That would be better to use channels and byteBuffers from java.nio,
 * but i have no time, sorry
 *
 * @author ioann
 */
@Repository
public class TextStringFileDaoImpl implements TextStringFileDao {


    private FileSession session = FileSession.buildSession();

    public FileSession getSession() {
        return session;
    }

    public void setSession(FileSession session) {
        this.session = session;
    }

    private void checkInputParam(Object obj) {
        if (obj == null) throw new IllegalArgumentException("Input parameters must be not null!");
    }

    private void checkSession() {
        if (!session.isOpen()){
            session.openSession();
        }
    }

    @Override
    public Long create(TextString textString) {
        checkInputParam(textString);
        checkSession();
        session.appendInfo(textString.getId() + " : " + textString.getInfo());
        return textString.getId();
    }

    @Override
    public TextString read(Long id) {
        checkInputParam(id);
        checkSession();
        TextString textString = null;
        String file = session.getInfo().toString();
        int idx = file.indexOf(id.toString());
        if (idx == -1) {
            System.out.println("Cannot find textString");
            return textString;
        } else if (file.substring(idx + 1, idx + 4).equals(" : ")) {
            textString = new TextString(id, file.substring(idx));
        }
        return textString;
    }

    @Override
    public boolean update(TextString textString) {
        return false;
    }

    @Override
    public boolean delate(TextString textString) {
        return false;
    }

    @Override
    public List<TextString> findAll() {
        checkSession();
        List<TextString> list = new ArrayList<>();
        String file = session.getInfo().toString();
        String[] tfiles = file.split("\\n");
        for (String str : tfiles) {
            int i = str.indexOf(":");
            list.add(new TextString(Long.parseLong(str.substring(0, i - 1)), str.substring(i + 1)));
        }
        return list;
    }

    @Override
    public void save() {
        session.writeInfoToFile();
    }

}
