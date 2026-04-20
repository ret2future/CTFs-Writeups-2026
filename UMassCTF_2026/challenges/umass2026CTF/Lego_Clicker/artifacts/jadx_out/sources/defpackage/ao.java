package defpackage;

import android.app.Application;
import android.os.Bundle;
import androidx.activity.b;
import androidx.lifecycle.LegacySavedStateHandleController$tryToAddRecreator$1;
import androidx.lifecycle.SavedStateHandleController;
import androidx.lifecycle.a;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ao implements ys {
    public final Application a;
    public final xs b;
    public final Bundle c;
    public final fg d;
    public final xn e;

    public ao(Application application, b bVar, Bundle bundle) {
        xs xsVar;
        this.e = bVar.getSavedStateRegistry();
        this.d = bVar.getLifecycle();
        this.c = bundle;
        this.a = application;
        if (application != null) {
            if (xs.d == null) {
                xs.d = new xs(application);
            }
            xsVar = xs.d;
            xsVar.getClass();
        } else {
            xsVar = new xs(null);
        }
        this.b = xsVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final vs a(String str, Class cls) {
        Object obj;
        Application application;
        fg fgVar = this.d;
        if (fgVar == null) {
            throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        boolean zIsAssignableFrom = i2.class.isAssignableFrom(cls);
        Constructor constructorA = (!zIsAssignableFrom || this.a == null) ? bo.a(cls, bo.b) : bo.a(cls, bo.a);
        if (constructorA == null) {
            if (this.a != null) {
                return this.b.d(cls);
            }
            if (rn.b == null) {
                rn.b = new rn();
            }
            rn rnVar = rn.b;
            rnVar.getClass();
            return rnVar.d(cls);
        }
        xn xnVar = this.e;
        xnVar.getClass();
        Bundle bundle = this.c;
        Bundle bundleA = xnVar.a(str);
        Class[] clsArr = qn.f;
        qn qnVarT = hb.t(bundleA, bundle);
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, qnVarT);
        savedStateHandleController.b(fgVar, xnVar);
        eg egVar = ((a) fgVar).c;
        if (egVar == eg.b || egVar.compareTo(eg.d) >= 0) {
            xnVar.d();
        } else {
            fgVar.a(new LegacySavedStateHandleController$tryToAddRecreator$1(fgVar, xnVar));
        }
        vs vsVarB = (!zIsAssignableFrom || (application = this.a) == null) ? bo.b(cls, constructorA, qnVarT) : bo.b(cls, constructorA, application, qnVarT);
        synchronized (vsVarB.a) {
            try {
                obj = vsVarB.a.get("androidx.lifecycle.savedstate.vm.tag");
                if (obj == null) {
                    vsVarB.a.put("androidx.lifecycle.savedstate.vm.tag", savedStateHandleController);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (obj != null) {
            savedStateHandleController = obj;
        }
        if (vsVarB.c) {
            vs.a(savedStateHandleController);
        }
        return vsVarB;
    }

    @Override // defpackage.ys
    public final vs b(Class cls, lj ljVar) {
        fr frVar = fr.e;
        LinkedHashMap linkedHashMap = ljVar.a;
        String str = (String) linkedHashMap.get(frVar);
        if (str == null) {
            z6.o("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
            return null;
        }
        if (linkedHashMap.get(d.E) == null || linkedHashMap.get(d.F) == null) {
            if (this.d != null) {
                return a(str, cls);
            }
            z6.o("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
            return null;
        }
        Application application = (Application) linkedHashMap.get(fr.d);
        boolean zIsAssignableFrom = i2.class.isAssignableFrom(cls);
        Constructor constructorA = (!zIsAssignableFrom || application == null) ? bo.a(cls, bo.b) : bo.a(cls, bo.a);
        return constructorA == null ? this.b.b(cls, ljVar) : (!zIsAssignableFrom || application == null) ? bo.b(cls, constructorA, d.o(ljVar)) : bo.b(cls, constructorA, application, d.o(ljVar));
    }

    @Override // defpackage.ys
    public final vs d(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return a(canonicalName, cls);
        }
        z6.f("Local and anonymous classes can not be ViewModels");
        return null;
    }
}
