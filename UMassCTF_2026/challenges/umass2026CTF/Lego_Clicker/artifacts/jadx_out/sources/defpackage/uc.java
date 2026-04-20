package defpackage;

import android.os.Handler;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class uc implements at, kg, zn {
    public final l2 a;
    public final Handler b;
    public final gd c;
    public final /* synthetic */ l2 d;

    public uc(l2 l2Var) {
        this.d = l2Var;
        Handler handler = new Handler();
        this.c = new gd();
        this.a = l2Var;
        this.b = handler;
    }

    @Override // defpackage.kg
    public final fg getLifecycle() {
        return this.d.mFragmentLifecycleRegistry;
    }

    @Override // defpackage.zn
    public final xn getSavedStateRegistry() {
        return this.d.getSavedStateRegistry();
    }

    @Override // defpackage.at
    public final zs getViewModelStore() {
        return this.d.getViewModelStore();
    }
}
