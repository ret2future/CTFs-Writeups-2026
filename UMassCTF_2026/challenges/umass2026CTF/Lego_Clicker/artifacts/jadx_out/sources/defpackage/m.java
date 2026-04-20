package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class m extends d {
    @Override // defpackage.d
    public final void L(n nVar, n nVar2) {
        nVar.b = nVar2;
    }

    @Override // defpackage.d
    public final void M(n nVar, Thread thread) {
        nVar.a = thread;
    }

    @Override // defpackage.d
    public final boolean f(o oVar, k kVar) {
        k kVar2 = k.b;
        synchronized (oVar) {
            try {
                if (oVar.b != kVar) {
                    return false;
                }
                oVar.b = kVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // defpackage.d
    public final boolean g(o oVar, Object obj, Object obj2) {
        synchronized (oVar) {
            try {
                if (oVar.a != obj) {
                    return false;
                }
                oVar.a = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // defpackage.d
    public final boolean h(o oVar, n nVar, n nVar2) {
        synchronized (oVar) {
            try {
                if (oVar.c != nVar) {
                    return false;
                }
                oVar.c = nVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
