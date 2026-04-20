package defpackage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class so extends pq {
    public static final x5 c = new x5(5);
    public final /* synthetic */ int a;
    public final pq b;

    public /* synthetic */ so(pq pqVar, int i) {
        this.a = i;
        this.b = pqVar;
    }

    @Override // defpackage.pq
    public final Object a(sf sfVar) {
        int i = this.a;
        pq pqVar = this.b;
        switch (i) {
            case 0:
                Date date = (Date) pqVar.a(sfVar);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            case 1:
                return new AtomicLong(((Number) pqVar.a(sfVar)).longValue());
            case 2:
                ArrayList arrayList = new ArrayList();
                sfVar.a();
                while (sfVar.i()) {
                    arrayList.add(Long.valueOf(((Number) pqVar.a(sfVar)).longValue()));
                }
                sfVar.e();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i2 = 0; i2 < size; i2++) {
                    atomicLongArray.set(i2, ((Long) arrayList.get(i2)).longValue());
                }
                return atomicLongArray;
            default:
                if (sfVar.v() != 9) {
                    return pqVar.a(sfVar);
                }
                sfVar.r();
                return null;
        }
    }

    @Override // defpackage.pq
    public final void b(tf tfVar, Object obj) throws IOException {
        int i = this.a;
        pq pqVar = this.b;
        switch (i) {
            case 0:
                pqVar.b(tfVar, (Timestamp) obj);
                break;
            case 1:
                pqVar.b(tfVar, Long.valueOf(((AtomicLong) obj).get()));
                break;
            case 2:
                AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
                tfVar.b();
                int length = atomicLongArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    pqVar.b(tfVar, Long.valueOf(atomicLongArray.get(i2)));
                }
                tfVar.e();
                break;
            default:
                if (obj == null) {
                    tfVar.i();
                } else {
                    pqVar.b(tfVar, obj);
                }
                break;
        }
    }
}
