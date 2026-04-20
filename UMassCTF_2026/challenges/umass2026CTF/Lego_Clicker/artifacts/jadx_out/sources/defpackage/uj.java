package defpackage;

import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class uj implements qq {
    public final /* synthetic */ int a;
    public final /* synthetic */ pq b;

    public /* synthetic */ uj(pq pqVar, int i) {
        this.a = i;
        this.b = pqVar;
    }

    @Override // defpackage.qq
    public final pq a(re reVar, wq wqVar) {
        int i = this.a;
        pq pqVar = this.b;
        switch (i) {
            case 0:
                if (wqVar.a == Number.class) {
                }
                break;
            default:
                Class cls = wqVar.a;
                if (cls == Calendar.class || cls == GregorianCalendar.class) {
                }
                break;
        }
        return (vj) pqVar;
    }

    public String toString() {
        switch (this.a) {
            case 1:
                return "Factory[type=" + Calendar.class.getName() + "+" + GregorianCalendar.class.getName() + ",adapter=" + ((vj) this.b) + "]";
            default:
                return super.toString();
        }
    }
}
