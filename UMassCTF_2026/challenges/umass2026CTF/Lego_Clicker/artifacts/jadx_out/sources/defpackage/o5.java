package defpackage;

import java.util.concurrent.Executors;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class o5 extends hb {
    public static volatile o5 p;
    public final Object o;

    public o5(int i) {
        switch (i) {
            case 1:
                this.o = new Object();
                Executors.newFixedThreadPool(4, new s9());
                break;
            default:
                this.o = new o5(1);
                break;
        }
    }
}
