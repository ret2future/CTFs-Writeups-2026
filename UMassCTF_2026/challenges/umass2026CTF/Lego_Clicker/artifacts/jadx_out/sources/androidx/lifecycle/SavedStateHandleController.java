package androidx.lifecycle;

import defpackage.dg;
import defpackage.fg;
import defpackage.ig;
import defpackage.kg;
import defpackage.qn;
import defpackage.xn;
import defpackage.z6;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandleController implements ig {
    public final String a;
    public final qn b;
    public boolean c;

    public SavedStateHandleController(String str, qn qnVar) {
        this.a = str;
        this.b = qnVar;
    }

    public final void b(fg fgVar, xn xnVar) {
        xnVar.getClass();
        fgVar.getClass();
        if (this.c) {
            z6.o("Already attached to lifecycleOwner");
            return;
        }
        this.c = true;
        fgVar.a(this);
        xnVar.c(this.a, this.b.e);
    }

    @Override // defpackage.ig
    public final void d(kg kgVar, dg dgVar) {
        if (dgVar == dg.ON_DESTROY) {
            this.c = false;
            kgVar.getLifecycle().b(this);
        }
    }
}
