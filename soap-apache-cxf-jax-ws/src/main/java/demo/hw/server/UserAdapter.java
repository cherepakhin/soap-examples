package demo.hw.server;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class UserAdapter extends XmlAdapter<UserImpl, User> {

    public UserImpl marshal(User v) {
        System.out.println("UserAdapter.marshal():" + v.toString());
        if (v instanceof UserImpl) {
            return (UserImpl)v;
        }
        return new UserImpl(v.getName());
    }

    public User unmarshal(UserImpl v) {
        System.out.println("UserAdapter.unmarshal():" + v.toString());
        return v;
    }
}
