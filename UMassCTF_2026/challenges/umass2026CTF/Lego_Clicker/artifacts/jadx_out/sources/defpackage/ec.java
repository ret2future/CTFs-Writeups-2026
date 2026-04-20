package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ec implements p8 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ ec(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.p8
    public final void accept(Object obj) {
        switch (this.a) {
            case 0:
                fc fcVar = (fc) obj;
                if (fcVar == null) {
                    fcVar = new fc(-3);
                }
                ((z3) this.b).s(fcVar);
                return;
            default:
                fc fcVar2 = (fc) obj;
                synchronized (gc.c) {
                    try {
                        ko koVar = gc.d;
                        ArrayList arrayList = (ArrayList) koVar.getOrDefault((String) this.b, null);
                        if (arrayList == null) {
                            return;
                        }
                        koVar.remove((String) this.b);
                        for (int i = 0; i < arrayList.size(); i++) {
                            ((p8) arrayList.get(i)).accept(fcVar2);
                        }
                        return;
                    } finally {
                    }
                }
        }
    }
}
