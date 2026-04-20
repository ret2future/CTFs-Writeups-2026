package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class tr {
    public final u5 a;
    public final u5 b;
    public final u5 c;

    public tr(u5 u5Var, u5 u5Var2, u5 u5Var3) {
        this.a = u5Var;
        this.b = u5Var2;
        this.c = u5Var3;
    }

    public abstract ur a();

    public final Class b(Class cls) throws ClassNotFoundException {
        String name = cls.getName();
        u5 u5Var = this.c;
        Class cls2 = (Class) u5Var.getOrDefault(name, null);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(cls.getPackage().getName() + "." + cls.getSimpleName() + "Parcelizer", false, cls.getClassLoader());
        u5Var.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method c(String str) throws NoSuchMethodException {
        u5 u5Var = this.a;
        Method method = (Method) u5Var.getOrDefault(str, null);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, tr.class.getClassLoader()).getDeclaredMethod("read", tr.class);
        u5Var.put(str, declaredMethod);
        return declaredMethod;
    }

    public final Method d(Class cls) throws NoSuchMethodException, ClassNotFoundException {
        String name = cls.getName();
        u5 u5Var = this.b;
        Method method = (Method) u5Var.getOrDefault(name, null);
        if (method != null) {
            return method;
        }
        Class clsB = b(cls);
        System.currentTimeMillis();
        Method declaredMethod = clsB.getDeclaredMethod("write", cls, tr.class);
        u5Var.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public abstract boolean e(int i);

    public final Parcelable f(Parcelable parcelable, int i) {
        if (!e(i)) {
            return parcelable;
        }
        return ((ur) this).e.readParcelable(ur.class.getClassLoader());
    }

    public final vr g() {
        String string = ((ur) this).e.readString();
        if (string == null) {
            return null;
        }
        try {
            return (vr) c(string).invoke(null, a());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    public abstract void h(int i);

    public final void i(vr vrVar) {
        if (vrVar == null) {
            ((ur) this).e.writeString(null);
            return;
        }
        try {
            ((ur) this).e.writeString(b(vrVar.getClass()).getName());
            ur urVarA = a();
            try {
                d(vrVar.getClass()).invoke(null, vrVar, urVarA);
                Parcel parcel = urVarA.e;
                int i = urVarA.i;
                if (i >= 0) {
                    int i2 = urVarA.d.get(i);
                    int iDataPosition = parcel.dataPosition();
                    parcel.setDataPosition(i2);
                    parcel.writeInt(iDataPosition - i2);
                    parcel.setDataPosition(iDataPosition);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
            } catch (InvocationTargetException e4) {
                if (!(e4.getCause() instanceof RuntimeException)) {
                    throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
                }
                throw ((RuntimeException) e4.getCause());
            }
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException(vrVar.getClass().getSimpleName().concat(" does not have a Parcelizer"), e5);
        }
    }
}
