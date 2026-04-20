package defpackage;

import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class xa extends d {
    public final /* synthetic */ d H;
    public final /* synthetic */ ThreadPoolExecutor I;

    public xa(d dVar, ThreadPoolExecutor threadPoolExecutor) {
        this.H = dVar;
        this.I = threadPoolExecutor;
    }

    @Override // defpackage.d
    public final void H(Throwable th) {
        ThreadPoolExecutor threadPoolExecutor = this.I;
        try {
            this.H.H(th);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    @Override // defpackage.d
    public final void I(kd kdVar) {
        ThreadPoolExecutor threadPoolExecutor = this.I;
        try {
            this.H.I(kdVar);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
