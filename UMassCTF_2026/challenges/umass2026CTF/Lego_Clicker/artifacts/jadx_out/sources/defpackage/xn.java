package defpackage;

import android.os.Bundle;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class xn {
    public boolean b;
    public Bundle c;
    public boolean d;
    public j2 e;
    public final nn a = new nn();
    public boolean f = true;

    public final Bundle a(String str) {
        if (!this.d) {
            z6.o("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
            return null;
        }
        Bundle bundle = this.c;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        Bundle bundle3 = this.c;
        if (bundle3 != null) {
            bundle3.remove(str);
        }
        Bundle bundle4 = this.c;
        if (bundle4 != null && !bundle4.isEmpty()) {
            return bundle2;
        }
        this.c = null;
        return bundle2;
    }

    public final wn b() {
        String str;
        wn wnVar;
        Iterator it = this.a.iterator();
        do {
            jn jnVar = (jn) it;
            if (!jnVar.hasNext()) {
                return null;
            }
            Map.Entry entry = (Map.Entry) jnVar.next();
            entry.getClass();
            str = (String) entry.getKey();
            wnVar = (wn) entry.getValue();
        } while (!hb.f(str, "androidx.lifecycle.internal.SavedStateHandlesProvider"));
        return wnVar;
    }

    public final void c(String str, wn wnVar) {
        Object obj;
        wnVar.getClass();
        nn nnVar = this.a;
        kn knVar = nnVar.a;
        while (knVar != null && !knVar.a.equals(str)) {
            knVar = knVar.c;
        }
        if (knVar != null) {
            obj = knVar.b;
        } else {
            kn knVar2 = new kn(str, wnVar);
            nnVar.d++;
            kn knVar3 = nnVar.b;
            if (knVar3 == null) {
                nnVar.a = knVar2;
                nnVar.b = knVar2;
            } else {
                knVar3.c = knVar2;
                knVar2.d = knVar3;
                nnVar.b = knVar2;
            }
            obj = null;
        }
        if (((wn) obj) == null) {
            return;
        }
        z6.f("SavedStateProvider with the given key is already registered");
    }

    public final void d() {
        if (!this.f) {
            z6.o("Can not perform this action after onSaveInstanceState");
            return;
        }
        j2 j2Var = this.e;
        if (j2Var == null) {
            j2Var = new j2(this);
        }
        this.e = j2Var;
        try {
            ag.class.getDeclaredConstructor(null);
            j2 j2Var2 = this.e;
            if (j2Var2 != null) {
                ((LinkedHashSet) j2Var2.b).add(ag.class.getName());
            }
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class " + ag.class.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
        }
    }
}
