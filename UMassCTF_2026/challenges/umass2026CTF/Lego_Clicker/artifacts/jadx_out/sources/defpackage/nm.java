package defpackage;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class nm extends pq {
    public final LinkedHashMap a;

    public nm(LinkedHashMap linkedHashMap) {
        this.a = linkedHashMap;
    }

    @Override // defpackage.pq
    public final Object a(sf sfVar) throws IOException {
        if (sfVar.v() == 9) {
            sfVar.r();
            return null;
        }
        Object objC = c();
        try {
            sfVar.b();
            while (sfVar.i()) {
                mm mmVar = (mm) this.a.get(sfVar.p());
                if (mmVar == null || !mmVar.e) {
                    sfVar.B();
                } else {
                    e(objC, sfVar, mmVar);
                }
            }
            sfVar.f();
            return d(objC);
        } catch (IllegalAccessException e) {
            hb hbVar = lm.a;
            throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.10.1). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e);
        } catch (IllegalStateException e2) {
            throw new of(e2);
        }
    }

    @Override // defpackage.pq
    public final void b(tf tfVar, Object obj) throws IOException {
        if (obj == null) {
            tfVar.i();
            return;
        }
        tfVar.c();
        try {
            Iterator it = this.a.values().iterator();
            while (it.hasNext()) {
                ((mm) it.next()).a(tfVar, obj);
            }
            tfVar.f();
        } catch (IllegalAccessException e) {
            hb hbVar = lm.a;
            throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.10.1). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e);
        }
    }

    public abstract Object c();

    public abstract Object d(Object obj);

    public abstract void e(Object obj, sf sfVar, mm mmVar);
}
