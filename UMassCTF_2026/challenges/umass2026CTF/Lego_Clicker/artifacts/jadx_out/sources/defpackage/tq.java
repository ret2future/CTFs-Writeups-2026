package defpackage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class tq extends pq {
    public final /* synthetic */ int a;

    public /* synthetic */ tq(int i) {
        this.a = i;
    }

    @Override // defpackage.pq
    public final Object a(sf sfVar) {
        switch (this.a) {
            case 0:
                return new AtomicBoolean(sfVar.l());
            default:
                try {
                    return new AtomicInteger(sfVar.n());
                } catch (NumberFormatException e) {
                    throw new of(e);
                }
        }
    }

    @Override // defpackage.pq
    public final void b(tf tfVar, Object obj) throws IOException {
        switch (this.a) {
            case 0:
                tfVar.p(((AtomicBoolean) obj).get());
                break;
            default:
                tfVar.m(((AtomicInteger) obj).get());
                break;
        }
    }
}
