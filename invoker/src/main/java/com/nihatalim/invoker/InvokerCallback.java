package com.nihatalim.invoker;

/**
 * Created by thecower on 2/13/18.
 */

public interface InvokerCallback<T> {
    public void invoke(T response);
}
