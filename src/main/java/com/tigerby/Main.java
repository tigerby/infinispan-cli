package com.tigerby;

import com.tigerby.utils.PropertiesManager;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p> title here </p>
 *
 * @author <a href="mailto:bongyeonkim@gmail.com">Kim Bryan</a>
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new PropertiesConfiguration(args[0]);
        String serverList = configuration.getString("infinispan.client.hotrod.server_list");

        RemoteCacheManager manager = new RemoteCacheManager(serverList);
        RemoteCache cache = manager.getCache(args[1]);
        Method[] methods = RemoteCache.class.getMethods();
        String[] methodArgs = Arrays.copyOfRange(args, 3, args.length);

        Method method = null;
        for(Method m: methods) {
            if(args[2].equals(m.getName()) && m.getParameterTypes().length == methodArgs.length) {
                method = m;
            }
        }

        Object result = method.invoke(cache, methodArgs);

        if(result instanceof Collection) {
            Collection collection = (Collection)result;
            for(Object o: collection) {
                System.out.println(o);
            }
        } else if(result instanceof Map) {
            Map map = (Map)result;
            Set<Map.Entry> entrySet = map.entrySet();
            for(Map.Entry entry: entrySet) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println(result);
        }

    }


}
