package androidx.lifecycle;

import defpackage.dg;
import defpackage.fg;
import defpackage.ig;
import defpackage.kg;
import defpackage.xn;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class LegacySavedStateHandleController$tryToAddRecreator$1 implements ig {
    public final /* synthetic */ fg a;
    public final /* synthetic */ xn b;

    public LegacySavedStateHandleController$tryToAddRecreator$1(fg fgVar, xn xnVar) {
        this.a = fgVar;
        this.b = xnVar;
    }

    @Override // defpackage.ig
    public final void d(kg kgVar, dg dgVar) {
        if (dgVar == dg.ON_START) {
            this.a.b(this);
            this.b.d();
        }
    }
}
