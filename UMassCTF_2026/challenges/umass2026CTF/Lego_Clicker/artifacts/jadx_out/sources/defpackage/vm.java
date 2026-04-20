package defpackage;

import android.os.Handler;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class vm implements Runnable {
    public dc a;
    public ec b;
    public Handler c;

    @Override // java.lang.Runnable
    public final void run() {
        Object objCall;
        try {
            objCall = this.a.call();
        } catch (Exception unused) {
            objCall = null;
        }
        this.c.post(new c1(this.b, 5, objCall));
    }
}
