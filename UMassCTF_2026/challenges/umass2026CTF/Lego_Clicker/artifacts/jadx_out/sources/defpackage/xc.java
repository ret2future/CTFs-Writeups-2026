package defpackage;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class xc {
    public final uc a;

    public xc(uc ucVar) {
        this.a = ucVar;
    }

    public final void a() {
        gd gdVar = this.a.c;
        if (gdVar.r == null) {
            return;
        }
        gdVar.y = false;
        gdVar.z = false;
        gdVar.E.getClass();
        Iterator it = gdVar.c.h().iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                z6.a();
                return;
            }
        }
    }
}
