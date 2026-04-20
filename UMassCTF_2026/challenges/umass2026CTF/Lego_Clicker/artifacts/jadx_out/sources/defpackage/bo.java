package defpackage;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class bo {
    public static final List a;
    public static final List b;

    static {
        List listAsList = Arrays.asList(Application.class, qn.class);
        listAsList.getClass();
        a = listAsList;
        List listSingletonList = Collections.singletonList(qn.class);
        listSingletonList.getClass();
        b = listSingletonList;
    }

    public static final Constructor a(Class cls, List list) {
        List listSingletonList;
        list.getClass();
        Constructor<?>[] constructors = cls.getConstructors();
        constructors.getClass();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            parameterTypes.getClass();
            int length = parameterTypes.length;
            if (length == 0) {
                listSingletonList = ob.a;
            } else if (length != 1) {
                listSingletonList = new ArrayList(new p5(0, parameterTypes));
            } else {
                listSingletonList = Collections.singletonList(parameterTypes[0]);
                listSingletonList.getClass();
            }
            if (list.equals(listSingletonList)) {
                return constructor;
            }
            if (list.size() == listSingletonList.size() && listSingletonList.containsAll(list)) {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }

    public static final vs b(Class cls, Constructor constructor, Object... objArr) {
        try {
            return (vs) constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e) {
            z6.j("Failed to access ", cls, e);
            return null;
        } catch (InstantiationException e2) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e3.getCause());
        }
    }
}
