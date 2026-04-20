package defpackage;

import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class nf {
    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            tf tfVar = new tf(stringWriter);
            tfVar.e = true;
            rq rqVar = vq.a;
            vj.d(tfVar, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
