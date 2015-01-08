package com.greatmancode.tools.world;

import com.greatmancode.tools.utils.Vector;
import lombok.Data;

@Data
public class Location {

    private final int x, y, z;
    private final String world;


    public Vector getVector() {
        return new Vector(x,y,z);
    }
}
