package defpackage;

import android.os.Trace;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class q6 implements Runnable {
    public final /* synthetic */ int a = 1;

    public /* synthetic */ q6() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                return;
            default:
                try {
                    int i = lq.a;
                    Trace.beginSection("EmojiCompat.EmojiCompatInitializer.run");
                    if (va.k != null) {
                        va.a().c();
                        break;
                    }
                    Trace.endSection();
                    return;
                } catch (Throwable th) {
                    int i2 = lq.a;
                    Trace.endSection();
                    throw th;
                }
        }
    }

    public q6(e0 e0Var, int i) {
    }

    private final void a() {
    }
}
