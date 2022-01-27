package com.netcetera.ncau.java17.overthehood;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.junit.jupiter.api.Test;

class ProxyTests {
  
  @Test
  void testInvokeDefault() {
    ClassLoader classLoader = ProxyTests.class.getClassLoader();
    Class<?>[] interfaces = new Class[] {WithDefaultMethod.class};

    WithDefaultMethod dynamicallyGenerated = (WithDefaultMethod) Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
      if (method.isDefault()) {
        return InvocationHandler.invokeDefault(proxy, method, args);
      }
      if (method.getReturnType() == String.class && method.getParameterCount() == 0) {
        return "hello";
      }
      
      throw new IllegalStateException("unsupported method: " + method);
    });
    
    assertEquals("default-hello", dynamicallyGenerated.defaultMethod());
  }

  @FunctionalInterface
  interface WithDefaultMethod {

    String normalMethod();

    default String defaultMethod() {
      return "default-" + normalMethod();
    }

  }

}
