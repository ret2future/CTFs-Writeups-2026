package defpackage;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class rn implements ys {
    public static rn a;
    public static rn b;

    public boolean a(CharSequence charSequence) {
        return false;
    }

    @Override // defpackage.ys
    public vs d(Class cls) throws InvocationTargetException {
        try {
            Object objNewInstance = cls.getDeclaredConstructor(null).newInstance(null);
            objNewInstance.getClass();
            return (vs) objNewInstance;
        } catch (IllegalAccessException e) {
            z6.j("Cannot create an instance of ", cls, e);
            return null;
        } catch (InstantiationException e2) {
            z6.j("Cannot create an instance of ", cls, e2);
            return null;
        } catch (NoSuchMethodException e3) {
            z6.j("Cannot create an instance of ", cls, e3);
            return null;
        }
    }
}
