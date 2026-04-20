package androidx.activity;

import android.os.Build;
import defpackage.bd;
import defpackage.bk;
import defpackage.dg;
import defpackage.fg;
import defpackage.ig;
import defpackage.kg;
import defpackage.s6;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
final class OnBackPressedDispatcher$LifecycleOnBackPressedCancellable implements ig, s6 {
    public final fg a;
    public final bd b;
    public bk c;
    public final /* synthetic */ c d;

    public OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(c cVar, fg fgVar, bd bdVar) {
        bdVar.getClass();
        this.d = cVar;
        this.a = fgVar;
        this.b = bdVar;
        fgVar.a(this);
    }

    @Override // defpackage.s6
    public final void cancel() {
        this.a.b(this);
        bd bdVar = this.b;
        bdVar.getClass();
        bdVar.b.remove(this);
        bk bkVar = this.c;
        if (bkVar != null) {
            bkVar.cancel();
        }
        this.c = null;
    }

    @Override // defpackage.ig
    public final void d(kg kgVar, dg dgVar) {
        if (dgVar != dg.ON_START) {
            if (dgVar != dg.ON_STOP) {
                if (dgVar == dg.ON_DESTROY) {
                    cancel();
                    return;
                }
                return;
            } else {
                bk bkVar = this.c;
                if (bkVar != null) {
                    bkVar.cancel();
                    return;
                }
                return;
            }
        }
        c cVar = this.d;
        cVar.getClass();
        bd bdVar = this.b;
        bdVar.getClass();
        cVar.b.addLast(bdVar);
        bk bkVar2 = new bk(cVar, bdVar);
        bdVar.b.add(bkVar2);
        if (Build.VERSION.SDK_INT >= 33) {
            cVar.c();
            bdVar.c = cVar.c;
        }
        this.c = bkVar2;
    }
}
