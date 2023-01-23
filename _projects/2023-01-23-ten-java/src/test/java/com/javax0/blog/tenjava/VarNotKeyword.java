package com.javax0.blog.tenjava;

//snippet var
public class VarNotKeyword {
    final int var = 13;

    void myMethod() {
        int var = var().var;
    }

    VarNotKeyword var() {
        return new VarNotKeyword();
    }

}
//end snippet
