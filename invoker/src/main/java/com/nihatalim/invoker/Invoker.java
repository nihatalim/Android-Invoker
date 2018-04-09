package com.nihatalim.invoker;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by nihat on 23.03.2018.
 */

public class Invoker {
    // New Invoker concept is like a channel. A fragment, activity and other views can listen channel,
    // and when channel has a message, channels will be invoke. (multiple listen)
    // TODO New update Map < String ( channelID ), Map < ( String ( connectionID ), InvokerCallback > >)
    public static Map<String, Map<String, InvokerCallback>> invoker = new HashMap<>();

    public static void invoke(String channelID, Object object){
        synchronized (invoker){
            if(!invoker.containsKey(channelID)) return;
            Map<String, InvokerCallback> invokerCallbacks = invoker.get(channelID);
            for (InvokerCallback invokerCallback : invokerCallbacks.values()) {
                invokerCallback.invoke(object);
            }
        }
    }

    public static void invoke(String uniqueID, String channelID, Object object){
        synchronized (invoker){
            if(!invoker.containsKey(channelID)) return;
            Map<String, InvokerCallback> invokerCallbacks = invoker.get(channelID);
            if(!invokerCallbacks.containsKey(uniqueID)) return;

            invokerCallbacks.get(uniqueID).invoke(object);
        }
    }


    public static void put(String uniqueID, String channelID, InvokerCallback invokerCallback){
        synchronized (invoker){
            if(!invoker.containsKey(channelID)){
                invoker.put(channelID, new HashMap<String, InvokerCallback>());
            }
            invoker.get(channelID).put(uniqueID, invokerCallback);
        }
    }

    public static Set<String> getChannels(){
        return invoker.keySet();
    }

    public static void remove(String uniqueID, String channelID){
        synchronized (invoker){
            if(!invoker.containsKey(channelID)) return;
            Map<String, InvokerCallback> invokerCallbacks = invoker.get(channelID);
            if(invokerCallbacks.containsKey(uniqueID)) invokerCallbacks.remove(uniqueID);
        }
    }

    public static void removeAll(String channelID){
        if(invoker.containsKey(channelID)) invoker.remove(channelID);
    }
}
