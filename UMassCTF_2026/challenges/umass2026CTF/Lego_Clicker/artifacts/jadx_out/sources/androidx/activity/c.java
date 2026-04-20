package androidx.activity;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import defpackage.ak;
import defpackage.bd;
import defpackage.d6;
import defpackage.eg;
import defpackage.fd;
import defpackage.fg;
import defpackage.kg;
import defpackage.q5;
import defpackage.zj;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public final Runnable a;
    public final q5 b = new q5();
    public final zj c;
    public final OnBackInvokedCallback d;
    public OnBackInvokedDispatcher e;
    public boolean f;

    public c(Runnable runnable) {
        this.a = runnable;
        if (Build.VERSION.SDK_INT >= 33) {
            this.c = new zj(0, this);
            this.d = ak.a.a(new zj(1, this));
        }
    }

    public final void a(kg kgVar, bd bdVar) {
        bdVar.getClass();
        fg lifecycle = kgVar.getLifecycle();
        if (((androidx.lifecycle.a) lifecycle).c == eg.a) {
            return;
        }
        bdVar.b.add(new OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(this, lifecycle, bdVar));
        if (Build.VERSION.SDK_INT >= 33) {
            c();
            bdVar.c = this.c;
        }
    }

    public final void b() {
        Object objPrevious;
        q5 q5Var = this.b;
        ListIterator listIterator = q5Var.listIterator(q5Var.c);
        while (true) {
            if (listIterator.hasPrevious()) {
                objPrevious = listIterator.previous();
                if (((bd) objPrevious).a) {
                    break;
                }
            } else {
                objPrevious = null;
                break;
            }
        }
        bd bdVar = (bd) objPrevious;
        if (bdVar == null) {
            this.a.run();
            return;
        }
        fd fdVar = bdVar.d;
        fdVar.e(true);
        if (!fdVar.g.a) {
            fdVar.f.b();
            return;
        }
        fdVar.e(false);
        fdVar.d(true);
        ArrayList arrayList = fdVar.B;
        ArrayList arrayList2 = fdVar.C;
        ArrayList arrayList3 = fdVar.d;
        int size = (arrayList3 == null || arrayList3.isEmpty()) ? -1 : fdVar.d.size() - 1;
        if (size >= 0) {
            for (int size2 = fdVar.d.size() - 1; size2 >= size; size2--) {
                arrayList.add((d6) fdVar.d.remove(size2));
                arrayList2.add(Boolean.TRUE);
            }
            fdVar.b = true;
            try {
                fdVar.j(fdVar.B, fdVar.C);
            } finally {
                fdVar.a();
            }
        }
        fdVar.k();
        ((HashMap) fdVar.c.b).values().removeAll(Collections.singleton(null));
    }

    public final void c() {
        boolean z;
        q5 q5Var = this.b;
        if (q5Var == null || !q5Var.isEmpty()) {
            Iterator it = q5Var.iterator();
            while (it.hasNext()) {
                if (((bd) it.next()).a) {
                    z = true;
                    break;
                }
            }
            z = false;
        } else {
            z = false;
        }
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.e;
        OnBackInvokedCallback onBackInvokedCallback = this.d;
        if (onBackInvokedDispatcher == null || onBackInvokedCallback == null) {
            return;
        }
        ak akVar = ak.a;
        if (z && !this.f) {
            akVar.b(onBackInvokedDispatcher, 0, onBackInvokedCallback);
            this.f = true;
        } else {
            if (z || !this.f) {
                return;
            }
            akVar.c(onBackInvokedDispatcher, onBackInvokedCallback);
            this.f = false;
        }
    }
}
