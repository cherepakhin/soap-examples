package demo.hw.server;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "User")
public class UserImpl implements User {

    private String name;

    // To avoid Exception in thread "main" javax.xml.ws.soap.SOAPFaultException: Unmarshalling Error: null
    public UserImpl() {
    }

    public UserImpl(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        demo.hw.server.UserImpl user = (demo.hw.server.UserImpl) o;
        return java.util.Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "UserImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}
