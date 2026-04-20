package androidx.lifecycle;

import defpackage.dg;
import defpackage.ig;
import defpackage.kg;
import defpackage.q9;
import defpackage.r9;
import defpackage.z6;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class DefaultLifecycleObserverAdapter implements ig {
    public final q9 a;
    public final ig b;

    public DefaultLifecycleObserverAdapter(q9 q9Var, ig igVar) {
        this.a = q9Var;
        this.b = igVar;
    }

    @Override // defpackage.ig
    public final void d(kg kgVar, dg dgVar) {
        int i = r9.a[dgVar.ordinal()];
        if (i == 3) {
            this.a.a();
        } else if (i == 7) {
            z6.f("ON_ANY must not been send by anybody");
            return;
        }
        ig igVar = this.b;
        if (igVar != null) {
            igVar.d(kgVar, dgVar);
        }
    }
}
