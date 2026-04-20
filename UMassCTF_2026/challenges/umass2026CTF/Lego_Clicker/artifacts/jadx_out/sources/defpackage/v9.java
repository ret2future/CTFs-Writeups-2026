package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class v9 implements t9 {
    public final mt d;
    public int f;
    public int g;
    public mt a = null;
    public boolean b = false;
    public boolean c = false;
    public int e = 1;
    public int h = 1;
    public aa i = null;
    public boolean j = false;
    public final ArrayList k = new ArrayList();
    public final ArrayList l = new ArrayList();

    public v9(mt mtVar) {
        this.d = mtVar;
    }

    @Override // defpackage.t9
    public final void a(t9 t9Var) {
        ArrayList arrayList = this.l;
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            if (!((v9) obj).j) {
                return;
            }
        }
        this.c = true;
        mt mtVar = this.a;
        if (mtVar != null) {
            mtVar.a(this);
        }
        if (this.b) {
            this.d.a(this);
            return;
        }
        int size2 = arrayList.size();
        v9 v9Var = null;
        int i3 = 0;
        while (i3 < size2) {
            Object obj2 = arrayList.get(i3);
            i3++;
            v9 v9Var2 = (v9) obj2;
            if (!(v9Var2 instanceof aa)) {
                i++;
                v9Var = v9Var2;
            }
        }
        if (v9Var != null && i == 1 && v9Var.j) {
            aa aaVar = this.i;
            if (aaVar != null) {
                if (!aaVar.j) {
                    return;
                } else {
                    this.f = this.h * aaVar.g;
                }
            }
            d(v9Var.g + this.f);
        }
        mt mtVar2 = this.a;
        if (mtVar2 != null) {
            mtVar2.a(this);
        }
    }

    public final void b(mt mtVar) {
        this.k.add(mtVar);
        if (this.j) {
            mtVar.a(mtVar);
        }
    }

    public final void c() {
        this.l.clear();
        this.k.clear();
        this.j = false;
        this.g = 0;
        this.c = false;
        this.b = false;
    }

    public void d(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            t9 t9Var = (t9) obj;
            t9Var.a(t9Var);
        }
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.d.b.g0);
        sb.append(":");
        switch (this.e) {
            case 1:
                str = "UNKNOWN";
                break;
            case 2:
                str = "HORIZONTAL_DIMENSION";
                break;
            case 3:
                str = "VERTICAL_DIMENSION";
                break;
            case 4:
                str = "LEFT";
                break;
            case 5:
                str = "RIGHT";
                break;
            case 6:
                str = "TOP";
                break;
            case 7:
                str = "BOTTOM";
                break;
            case 8:
                str = "BASELINE";
                break;
            default:
                str = "null";
                break;
        }
        sb.append(str);
        sb.append("(");
        sb.append(this.j ? Integer.valueOf(this.g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.l.size());
        sb.append(":d=");
        sb.append(this.k.size());
        sb.append(">");
        return sb.toString();
    }
}
