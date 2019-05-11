package com.gamjatwigim.reactive;

import java.util.function.Function;

class LambdaAndClosure{
    public static final void main(String[] args){
        final IntRef outerVar = new IntRef();
        IntRef.element = 3;
        Func func = new Func() {
            @Override
            public Object inovke() {
                this.invoke();
                return null;
            }

            @Override
            public void invoke() {
                IntRef.element = 4;
            }
        };
        System.out.println(IntRef.element);
    }
}