package defpackage;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class dd {
    public static final ko b = new ko();
    public final /* synthetic */ fd a;

    public dd(fd fdVar) {
        this.a = fdVar;
    }

    public static Class b(ClassLoader classLoader, String str) throws ClassNotFoundException {
        ko koVar = b;
        ko koVar2 = (ko) koVar.getOrDefault(classLoader, null);
        if (koVar2 == null) {
            koVar2 = new ko();
            koVar.put(classLoader, koVar2);
        }
        Class cls = (Class) koVar2.getOrDefault(str, null);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        koVar2.put(str, cls2);
        return cls2;
    }

    public static Class c(ClassLoader classLoader, String str) {
        try {
            return b(classLoader, str);
        } catch (ClassCastException e) {
            z6.d(str, ": make sure class is a valid subclass of Fragment", e);
            return null;
        } catch (ClassNotFoundException e2) {
            z6.d(str, ": make sure class name exists", e2);
            return null;
        }
    }

    public final void a(String str) {
        try {
            if (c(this.a.r.a.getClassLoader(), str).getConstructor(null).newInstance(null) == null) {
            } else {
                throw new ClassCastException();
            }
        } catch (IllegalAccessException e) {
            z6.d(str, ": make sure class name exists, is public, and has an empty constructor that is public", e);
        } catch (InstantiationException e2) {
            z6.d(str, ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (NoSuchMethodException e3) {
            z6.d(str, ": could not find Fragment constructor", e3);
        } catch (InvocationTargetException e4) {
            z6.d(str, ": calling Fragment constructor caused an exception", e4);
        }
    }
}
