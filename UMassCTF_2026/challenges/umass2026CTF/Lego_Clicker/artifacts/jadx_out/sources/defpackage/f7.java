package defpackage;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class f7 {
    public static final f7 c = new f7();
    public final HashMap a = new HashMap();
    public final HashMap b = new HashMap();

    public static void b(HashMap map, e7 e7Var, dg dgVar, Class cls) {
        dg dgVar2 = (dg) map.get(e7Var);
        if (dgVar2 == null || dgVar == dgVar2) {
            if (dgVar2 == null) {
                map.put(e7Var, dgVar);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + e7Var.b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + dgVar2 + ", new value " + dgVar);
    }

    public final d7 a(Class cls, Method[] methodArr) {
        int i;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        HashMap map2 = this.a;
        if (superclass != null) {
            d7 d7VarA = (d7) map2.get(superclass);
            if (d7VarA == null) {
                d7VarA = a(superclass, null);
            }
            map.putAll(d7VarA.b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            d7 d7VarA2 = (d7) map2.get(cls2);
            if (d7VarA2 == null) {
                d7VarA2 = a(cls2, null);
            }
            for (Map.Entry entry : d7VarA2.b.entrySet()) {
                b(map, (e7) entry.getKey(), (dg) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
            }
        }
        boolean z = false;
        for (Method method : methodArr) {
            dk dkVar = (dk) method.getAnnotation(dk.class);
            if (dkVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else {
                    if (!kg.class.isAssignableFrom(parameterTypes[0])) {
                        z6.f("invalid parameter type. Must be one and instanceof LifecycleOwner");
                        return null;
                    }
                    i = 1;
                }
                dg dgVarValue = dkVar.value();
                if (parameterTypes.length > 1) {
                    if (!dg.class.isAssignableFrom(parameterTypes[1])) {
                        z6.f("invalid parameter type. second arg must be an event");
                        return null;
                    }
                    if (dgVarValue != dg.ON_ANY) {
                        z6.f("Second arg is supported only for ON_ANY value");
                        return null;
                    }
                    i = 2;
                }
                if (parameterTypes.length > 2) {
                    z6.f("cannot have more than 2 params");
                    return null;
                }
                b(map, new e7(i, method), dgVarValue, cls);
                z = true;
            }
        }
        d7 d7Var = new d7(map);
        map2.put(cls, d7Var);
        this.b.put(cls, Boolean.valueOf(z));
        return d7Var;
    }
}
