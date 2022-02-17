package entity;

import java.util.ArrayList;
import java.util.List;

public class Multi {
    /**
     * 鲜花标识符
     */

    private List<String> s = new ArrayList<String>();

    public List<String> getS() {
        return s;
    }

    public void setS(String ss) {
        s.add(ss);
    }

}