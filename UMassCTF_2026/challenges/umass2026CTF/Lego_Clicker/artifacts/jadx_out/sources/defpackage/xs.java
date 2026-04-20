package defpackage;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class xs extends rn {
    public static xs d;
    public final Application c;

    public xs(Application application) {
        this.c = application;
    }

    @Override // defpackage.ys
    public final vs b(Class cls, lj ljVar) {
        if (this.c != null) {
            return d(cls);
        }
        Application application = (Application) ljVar.a.get(fr.d);
        if (application != null) {
            return c(cls, application);
        }
        if (!i2.class.isAssignableFrom(cls)) {
            return super.d(cls);
        }
        z6.f("CreationExtras must have an application by `APPLICATION_KEY`");
        return null;
    }

    public final vs c(Class cls, Application application) {
        if (!i2.class.isAssignableFrom(cls)) {
            return super.d(cls);
        }
        try {
            vs vsVar = (vs) cls.getConstructor(Application.class).newInstance(application);
            vsVar.getClass();
            return vsVar;
        } catch (IllegalAccessException e) {
            z6.j("Cannot create an instance of ", cls, e);
            return null;
        } catch (InstantiationException e2) {
            z6.j("Cannot create an instance of ", cls, e2);
            return null;
        } catch (NoSuchMethodException e3) {
            z6.j("Cannot create an instance of ", cls, e3);
            return null;
        } catch (InvocationTargetException e4) {
            z6.j("Cannot create an instance of ", cls, e4);
            return null;
        }
    }

    @Override // defpackage.rn, defpackage.ys
    public final vs d(Class cls) {
        Application application = this.c;
        if (application != null) {
            return c(cls, application);
        }
        throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
    }
}
