package androidx.savedstate;

import android.os.Bundle;
import defpackage.at;
import defpackage.dg;
import defpackage.hb;
import defpackage.ig;
import defpackage.kg;
import defpackage.vn;
import defpackage.vs;
import defpackage.xn;
import defpackage.z6;
import defpackage.zn;
import defpackage.zs;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class Recreator implements ig {
    public final zn a;

    public Recreator(zn znVar) {
        this.a = znVar;
    }

    @Override // defpackage.ig
    public final void d(kg kgVar, dg dgVar) {
        if (dgVar != dg.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        kgVar.getLifecycle().b(this);
        zn znVar = this.a;
        Bundle bundleA = znVar.getSavedStateRegistry().a("androidx.savedstate.Restarter");
        if (bundleA == null) {
            return;
        }
        ArrayList<String> stringArrayList = bundleA.getStringArrayList("classes_to_restore");
        if (stringArrayList == null) {
            z6.o("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
            return;
        }
        int size = stringArrayList.size();
        int i = 0;
        while (i < size) {
            String str = stringArrayList.get(i);
            i++;
            String str2 = str;
            try {
                Class<? extends U> clsAsSubclass = Class.forName(str2, false, Recreator.class.getClassLoader()).asSubclass(vn.class);
                clsAsSubclass.getClass();
                try {
                    Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(null);
                    declaredConstructor.setAccessible(true);
                    try {
                        Object objNewInstance = declaredConstructor.newInstance(null);
                        objNewInstance.getClass();
                        if (!(znVar instanceof at)) {
                            z6.o("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner");
                            return;
                        }
                        zs viewModelStore = ((at) znVar).getViewModelStore();
                        xn savedStateRegistry = znVar.getSavedStateRegistry();
                        viewModelStore.getClass();
                        LinkedHashMap linkedHashMap = viewModelStore.a;
                        for (String str3 : new HashSet(linkedHashMap.keySet())) {
                            str3.getClass();
                            vs vsVar = (vs) linkedHashMap.get(str3);
                            vsVar.getClass();
                            hb.g(vsVar, savedStateRegistry, znVar.getLifecycle());
                        }
                        if (!new HashSet(linkedHashMap.keySet()).isEmpty()) {
                            savedStateRegistry.d();
                        }
                    } catch (Exception e) {
                        z6.j("Failed to instantiate ", str2, e);
                        return;
                    }
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException("Class " + clsAsSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
                }
            } catch (ClassNotFoundException e3) {
                throw new RuntimeException("Class " + str2 + " wasn't found", e3);
            }
        }
    }
}
