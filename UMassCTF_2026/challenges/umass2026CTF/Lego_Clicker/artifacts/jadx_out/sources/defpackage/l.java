package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class l extends d {
    public final AtomicReferenceFieldUpdater H;
    public final AtomicReferenceFieldUpdater I;
    public final AtomicReferenceFieldUpdater J;
    public final AtomicReferenceFieldUpdater K;
    public final AtomicReferenceFieldUpdater L;

    public l(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.H = atomicReferenceFieldUpdater;
        this.I = atomicReferenceFieldUpdater2;
        this.J = atomicReferenceFieldUpdater3;
        this.K = atomicReferenceFieldUpdater4;
        this.L = atomicReferenceFieldUpdater5;
    }

    @Override // defpackage.d
    public final void L(n nVar, n nVar2) {
        this.I.lazySet(nVar, nVar2);
    }

    @Override // defpackage.d
    public final void M(n nVar, Thread thread) {
        this.H.lazySet(nVar, thread);
    }

    @Override // defpackage.d
    public final boolean f(o oVar, k kVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.K;
            if (atomicReferenceFieldUpdater.compareAndSet(oVar, kVar, k.b)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(oVar) == kVar);
        return false;
    }

    @Override // defpackage.d
    public final boolean g(o oVar, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.L;
            if (atomicReferenceFieldUpdater.compareAndSet(oVar, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(oVar) == obj);
        return false;
    }

    @Override // defpackage.d
    public final boolean h(o oVar, n nVar, n nVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.J;
            if (atomicReferenceFieldUpdater.compareAndSet(oVar, nVar, nVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(oVar) == nVar);
        return false;
    }
}
