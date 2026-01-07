//snippet OuterClass
package com.javax0.blog.hiddenclasses.otherpackage;

import java.lang.invoke.MethodHandles;

public class OuterClass {

    // skip lines
    private int z = 55;

    public int getZ() {
        return z;
    }
    // end skip
    public static MethodHandles.Lookup lookup() {
        return MethodHandles.lookup();
    }
}
//end snippet
