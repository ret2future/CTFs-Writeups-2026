package defpackage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class lt {
    public static int f;
    public ArrayList a;
    public int b;
    public int c;
    public ArrayList d;
    public int e;

    public final void a(ArrayList arrayList) {
        int size = this.a.size();
        if (this.e != -1 && size > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                lt ltVar = (lt) arrayList.get(i);
                if (this.e == ltVar.b) {
                    c(this.c, ltVar);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public final int b(tg tgVar, int i) {
        int iN;
        int iN2;
        ArrayList arrayList = this.a;
        if (arrayList.size() == 0) {
            return 0;
        }
        l8 l8Var = (l8) ((k8) arrayList.get(0)).S;
        tgVar.t();
        l8Var.b(tgVar, false);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ((k8) arrayList.get(i2)).b(tgVar, false);
        }
        if (i == 0 && l8Var.y0 > 0) {
            d.a(l8Var, tgVar, arrayList, 0);
        }
        if (i == 1 && l8Var.z0 > 0) {
            d.a(l8Var, tgVar, arrayList, 1);
        }
        try {
            tgVar.p();
        } catch (Exception e) {
            System.err.println(e.toString() + "\n" + Arrays.toString(e.getStackTrace()).replace("[", "   at ").replace(",", "\n   at").replace("]", ""));
        }
        this.d = new ArrayList();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            k8 k8Var = (k8) arrayList.get(i3);
            rn rnVar = new rn();
            new WeakReference(k8Var);
            tg.n(k8Var.H);
            tg.n(k8Var.I);
            tg.n(k8Var.J);
            tg.n(k8Var.K);
            tg.n(k8Var.L);
            this.d.add(rnVar);
        }
        if (i == 0) {
            iN = tg.n(l8Var.H);
            iN2 = tg.n(l8Var.J);
            tgVar.t();
        } else {
            iN = tg.n(l8Var.I);
            iN2 = tg.n(l8Var.K);
            tgVar.t();
        }
        return iN2 - iN;
    }

    public final void c(int i, lt ltVar) {
        int i2 = ltVar.b;
        ArrayList arrayList = this.a;
        int size = arrayList.size();
        int i3 = 0;
        while (i3 < size) {
            Object obj = arrayList.get(i3);
            i3++;
            k8 k8Var = (k8) obj;
            ArrayList arrayList2 = ltVar.a;
            if (!arrayList2.contains(k8Var)) {
                arrayList2.add(k8Var);
            }
            if (i == 0) {
                k8Var.m0 = i2;
            } else {
                k8Var.n0 = i2;
            }
        }
        this.e = i2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.c;
        sb.append(i == 0 ? "Horizontal" : i == 1 ? "Vertical" : i == 2 ? "Both" : "Unknown");
        sb.append(" [");
        sb.append(this.b);
        sb.append("] <");
        String string = sb.toString();
        ArrayList arrayList = this.a;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            string = string + " " + ((k8) obj).g0;
        }
        return string.concat(" >");
    }
}
