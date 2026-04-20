package defpackage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class li {
    public final Runnable a;
    public final CopyOnWriteArrayList b = new CopyOnWriteArrayList();
    public final HashMap c = new HashMap();

    public li(Runnable runnable) {
        this.a = runnable;
    }

    public final void a() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            fd fdVar = ((cd) ((cj) it.next())).a;
            if (fdVar.q >= 1) {
                Iterator it2 = fdVar.c.h().iterator();
                while (it2.hasNext()) {
                    if (it2.next() != null) {
                        z6.a();
                        return;
                    }
                }
            }
        }
    }

    public final void b() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            fd fdVar = ((cd) ((cj) it.next())).a;
            if (fdVar.q >= 1) {
                Iterator it2 = fdVar.c.h().iterator();
                while (it2.hasNext()) {
                    if (it2.next() != null) {
                        z6.a();
                        return;
                    }
                }
            }
        }
    }

    public final void c() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            fd fdVar = ((cd) ((cj) it.next())).a;
            if (fdVar.q >= 1) {
                Iterator it2 = fdVar.c.h().iterator();
                while (it2.hasNext()) {
                    if (it2.next() != null) {
                        z6.a();
                        return;
                    }
                }
            }
        }
    }

    public final void d(cj cjVar) {
        this.b.remove(cjVar);
        ki kiVar = (ki) this.c.remove(cjVar);
        if (kiVar != null) {
            kiVar.a.b(kiVar.b);
            kiVar.b = null;
        }
        this.a.run();
    }
}
