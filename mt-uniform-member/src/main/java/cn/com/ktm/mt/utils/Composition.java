package cn.com.ktm.mt.utils;

import java.util.ArrayList;
import java.util.Collections;

public class Composition extends ArrayList<Integer> {

    @Override
    public boolean equals(Object other) {
        Composition comp = (Composition) other;
        Collections.sort(this);
        Collections.sort(comp);
        if (this.isEmpty() || comp.isEmpty() || this.size() != comp.size())
            return false;
        for (int i = 0; i < this.size(); i++)
            if (this.get(i) != comp.get(i))
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
