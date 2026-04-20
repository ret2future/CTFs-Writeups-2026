package defpackage;

import android.os.Bundle;
import androidx.lifecycle.a;
import androidx.savedstate.Recreator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class yn {
    public final zn a;
    public final xn b = new xn();
    public boolean c;

    public yn(zn znVar) {
        this.a = znVar;
    }

    public final void a() {
        zn znVar = this.a;
        fg lifecycle = znVar.getLifecycle();
        if (((a) lifecycle).c != eg.b) {
            z6.o("Restarter must be created only during owner's initialization stage");
            return;
        }
        lifecycle.a(new Recreator(znVar));
        final xn xnVar = this.b;
        xnVar.getClass();
        if (xnVar.b) {
            z6.o("SavedStateRegistry was already attached.");
            return;
        }
        lifecycle.a(new ig() { // from class: un
            @Override // defpackage.ig
            public final void d(kg kgVar, dg dgVar) {
                xn xnVar2 = xnVar;
                xnVar2.getClass();
                if (dgVar == dg.ON_START) {
                    xnVar2.f = true;
                } else if (dgVar == dg.ON_STOP) {
                    xnVar2.f = false;
                }
            }
        });
        xnVar.b = true;
        this.c = true;
    }

    public final void b(Bundle bundle) {
        if (!this.c) {
            a();
        }
        fg lifecycle = this.a.getLifecycle();
        if (((a) lifecycle).c.compareTo(eg.d) >= 0) {
            throw new IllegalStateException(("performRestore cannot be called when owner is " + ((a) lifecycle).c).toString());
        }
        xn xnVar = this.b;
        if (!xnVar.b) {
            z6.o("You must call performAttach() before calling performRestore(Bundle).");
        } else if (xnVar.d) {
            z6.o("SavedStateRegistry was already restored.");
        } else {
            xnVar.c = bundle != null ? bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key") : null;
            xnVar.d = true;
        }
    }

    public final void c(Bundle bundle) {
        xn xnVar = this.b;
        xnVar.getClass();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = xnVar.c;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        nn nnVar = xnVar.a;
        nnVar.getClass();
        ln lnVar = new ln(nnVar);
        nnVar.c.put(lnVar, Boolean.FALSE);
        while (lnVar.hasNext()) {
            Map.Entry entry = (Map.Entry) lnVar.next();
            bundle2.putBundle((String) entry.getKey(), ((wn) entry.getValue()).a());
        }
        if (bundle2.isEmpty()) {
            return;
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}
