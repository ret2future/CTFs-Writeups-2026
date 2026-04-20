package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class th implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ wh b;

    public /* synthetic */ th(wh whVar, int i) {
        this.a = i;
        this.b = whVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.a;
        wh whVar = this.b;
        switch (i) {
            case 0:
                whVar.e.a.a();
                break;
            case 1:
                whVar.f.a.a();
                break;
            default:
                whVar.a.c.animate().scaleX(1.0f).scaleY(1.0f).setDuration(60L).start();
                break;
        }
    }
}
