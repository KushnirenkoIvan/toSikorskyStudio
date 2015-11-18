package webapp.domain;

import org.springframework.stereotype.Component;

/**
 * This class represents a info from any source
 * @author ioann
 * @value <b>id</b> is a unique identifier of string
 * @value <b>info</b> - is a string contains information
 * @version 1.0
 */

@Component
public class TextString {

    private Long id;

    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public TextString() {
        System.out.println("New String");
    }

    public TextString(Long id, String string) {
        this.id = id;
        this.info = string;
    }

    @Override
    public String toString() {
        return new String("String id: " + id + "; info: " + info + ".");
    }

    @Override
    public boolean equals(Object obj) {
        TextString textString = (TextString) obj;
        return this.getId().equals(textString.getId());
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }

}
