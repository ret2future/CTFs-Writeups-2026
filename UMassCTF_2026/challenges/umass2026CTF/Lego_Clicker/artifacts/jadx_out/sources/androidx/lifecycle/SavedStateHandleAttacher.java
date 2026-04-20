package androidx.lifecycle;

import defpackage.dg;
import defpackage.ig;
import defpackage.kg;
import defpackage.sn;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandleAttacher implements ig {
    public final sn a;

    public SavedStateHandleAttacher(sn snVar) {
        this.a = snVar;
    }

    @Override // defpackage.ig
    public final void d(kg kgVar, dg dgVar) {
        if (dgVar == dg.ON_CREATE) {
            kgVar.getLifecycle().b(this);
            this.a.b();
        } else {
            throw new IllegalStateException(("Next event must be ON_CREATE, it was " + dgVar).toString());
        }
    }
}
