package defpackage;

import android.util.Base64;
import androidx.recyclerview.widget.RecyclerView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class a2 {
    public final /* synthetic */ int a;
    public final Object b;
    public final Serializable c;
    public final Serializable d;
    public final Object e;
    public final Object f;

    public a2(String str, String str2, String str3, List list) {
        this.a = 1;
        str.getClass();
        this.b = str;
        str2.getClass();
        this.c = str2;
        this.d = str3;
        list.getClass();
        this.e = list;
        this.f = str + "-" + str2 + "-" + str3;
    }

    public boolean a(int i) {
        ArrayList arrayList = (ArrayList) this.d;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            z1 z1Var = (z1) arrayList.get(i2);
            int i3 = z1Var.a;
            if (i3 != 8) {
                if (i3 == 1) {
                    int i4 = z1Var.b;
                    int i5 = z1Var.c + i4;
                    while (i4 < i5) {
                        if (e(i4, i2 + 1) == i) {
                            return true;
                        }
                        i4++;
                    }
                } else {
                    continue;
                }
            } else {
                if (e(z1Var.c, i2 + 1) == i) {
                    return true;
                }
            }
        }
        return false;
    }

    public void b() {
        Object obj = this.e;
        il ilVar = (il) obj;
        ArrayList arrayList = (ArrayList) this.d;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((il) obj).a((z1) arrayList.get(i));
        }
        i(arrayList);
        ArrayList arrayList2 = (ArrayList) this.c;
        int size2 = arrayList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            z1 z1Var = (z1) arrayList2.get(i2);
            int i3 = z1Var.a;
            if (i3 == 1) {
                ilVar.a(z1Var);
                ilVar.d(z1Var.b, z1Var.c);
            } else if (i3 == 2) {
                ilVar.a(z1Var);
                int i4 = z1Var.b;
                int i5 = z1Var.c;
                RecyclerView recyclerView = ilVar.a;
                recyclerView.M(i4, i5, true);
                recyclerView.g0 = true;
                recyclerView.d0.b += i5;
            } else if (i3 == 4) {
                ilVar.a(z1Var);
                ilVar.c(z1Var.b, z1Var.c);
            } else if (i3 == 8) {
                ilVar.a(z1Var);
                ilVar.e(z1Var.b, z1Var.c);
            }
        }
        i(arrayList2);
    }

    public void c(z1 z1Var) {
        int i;
        mk mkVar = (mk) this.b;
        int i2 = z1Var.a;
        if (i2 == 1 || i2 == 8) {
            z6.f("should not dispatch add or move for pre layout");
            return;
        }
        int iJ = j(z1Var.b, i2);
        int i3 = z1Var.b;
        int i4 = z1Var.a;
        if (i4 == 2) {
            i = 0;
        } else {
            if (i4 != 4) {
                z6.n(z1Var, "op should be remove or update.");
                return;
            }
            i = 1;
        }
        int i5 = 1;
        for (int i6 = 1; i6 < z1Var.c; i6++) {
            int iJ2 = j((i * i6) + z1Var.b, z1Var.a);
            int i7 = z1Var.a;
            if (i7 == 2 ? iJ2 != iJ : !(i7 == 4 && iJ2 == iJ + 1)) {
                z1 z1VarG = g(i7, iJ, i5);
                d(z1VarG, i3);
                mkVar.c(z1VarG);
                if (z1Var.a == 4) {
                    i3 += i5;
                }
                i5 = 1;
                iJ = iJ2;
            } else {
                i5++;
            }
        }
        mkVar.c(z1Var);
        if (i5 > 0) {
            z1 z1VarG2 = g(z1Var.a, iJ, i5);
            d(z1VarG2, i3);
            mkVar.c(z1VarG2);
        }
    }

    public void d(z1 z1Var, int i) {
        il ilVar = (il) this.e;
        ilVar.a(z1Var);
        int i2 = z1Var.a;
        if (i2 != 2) {
            if (i2 == 4) {
                ilVar.c(i, z1Var.c);
                return;
            } else {
                z6.f("only remove and update ops can be dispatched in first pass");
                return;
            }
        }
        int i3 = z1Var.c;
        RecyclerView recyclerView = ilVar.a;
        recyclerView.M(i, i3, true);
        recyclerView.g0 = true;
        recyclerView.d0.b += i3;
    }

    public int e(int i, int i2) {
        ArrayList arrayList = (ArrayList) this.d;
        int size = arrayList.size();
        while (i2 < size) {
            z1 z1Var = (z1) arrayList.get(i2);
            int i3 = z1Var.a;
            int i4 = z1Var.b;
            if (i3 == 8) {
                if (i4 == i) {
                    i = z1Var.c;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (z1Var.c <= i) {
                        i++;
                    }
                }
            } else if (i4 > i) {
                continue;
            } else if (i3 == 2) {
                int i5 = z1Var.c;
                if (i < i4 + i5) {
                    return -1;
                }
                i -= i5;
            } else if (i3 == 1) {
                i += z1Var.c;
            }
            i2++;
        }
        return i;
    }

    public boolean f() {
        return ((ArrayList) this.c).size() > 0;
    }

    public z1 g(int i, int i2, int i3) {
        z1 z1Var = (z1) ((mk) this.b).a();
        if (z1Var != null) {
            z1Var.a = i;
            z1Var.b = i2;
            z1Var.c = i3;
            return z1Var;
        }
        z1 z1Var2 = new z1();
        z1Var2.a = i;
        z1Var2.b = i2;
        z1Var2.c = i3;
        return z1Var2;
    }

    public void h(z1 z1Var) {
        il ilVar = (il) this.e;
        ((ArrayList) this.d).add(z1Var);
        int i = z1Var.a;
        if (i == 1) {
            ilVar.d(z1Var.b, z1Var.c);
            return;
        }
        if (i == 2) {
            int i2 = z1Var.b;
            int i3 = z1Var.c;
            RecyclerView recyclerView = ilVar.a;
            recyclerView.M(i2, i3, false);
            recyclerView.g0 = true;
            return;
        }
        if (i == 4) {
            ilVar.c(z1Var.b, z1Var.c);
        } else if (i == 8) {
            ilVar.e(z1Var.b, z1Var.c);
        } else {
            z6.n(z1Var, "Unknown update op type for ");
        }
    }

    public void i(ArrayList arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            z1 z1Var = (z1) arrayList.get(i);
            z1Var.getClass();
            ((mk) this.b).c(z1Var);
        }
        arrayList.clear();
    }

    public int j(int i, int i2) {
        int i3;
        int i4;
        mk mkVar = (mk) this.b;
        ArrayList arrayList = (ArrayList) this.d;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            z1 z1Var = (z1) arrayList.get(size);
            int i5 = z1Var.a;
            int i6 = z1Var.b;
            if (i5 == 8) {
                int i7 = z1Var.c;
                if (i6 < i7) {
                    i4 = i7;
                    i3 = i6;
                } else {
                    i3 = i7;
                    i4 = i6;
                }
                if (i < i3 || i > i4) {
                    if (i < i6) {
                        if (i2 == 1) {
                            z1Var.b = i6 + 1;
                            z1Var.c = i7 + 1;
                        } else if (i2 == 2) {
                            z1Var.b = i6 - 1;
                            z1Var.c = i7 - 1;
                        }
                    }
                } else if (i3 == i6) {
                    if (i2 == 1) {
                        z1Var.c = i7 + 1;
                    } else if (i2 == 2) {
                        z1Var.c = i7 - 1;
                    }
                    i++;
                } else {
                    if (i2 == 1) {
                        z1Var.b = i6 + 1;
                    } else if (i2 == 2) {
                        z1Var.b = i6 - 1;
                    }
                    i--;
                }
            } else if (i6 <= i) {
                if (i5 == 1) {
                    i -= z1Var.c;
                } else if (i5 == 2) {
                    i += z1Var.c;
                }
            } else if (i2 == 1) {
                z1Var.b = i6 + 1;
            } else if (i2 == 2) {
                z1Var.b = i6 - 1;
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            z1 z1Var2 = (z1) arrayList.get(size2);
            int i8 = z1Var2.a;
            int i9 = z1Var2.c;
            if (i8 == 8) {
                if (i9 == z1Var2.b || i9 < 0) {
                    arrayList.remove(size2);
                    mkVar.c(z1Var2);
                }
            } else if (i9 <= 0) {
                arrayList.remove(size2);
                mkVar.c(z1Var2);
            }
        }
        return i;
    }

    public String toString() {
        switch (this.a) {
            case 1:
                List list = (List) this.e;
                StringBuilder sb = new StringBuilder();
                sb.append("FontRequest {mProviderAuthority: " + ((String) this.b) + ", mProviderPackage: " + ((String) this.c) + ", mQuery: " + ((String) this.d) + ", mCertificates:");
                for (int i = 0; i < list.size(); i++) {
                    sb.append(" [");
                    List list2 = (List) list.get(i);
                    for (int i2 = 0; i2 < list2.size(); i2++) {
                        sb.append(" \"");
                        sb.append(Base64.encodeToString((byte[]) list2.get(i2), 0));
                        sb.append("\"");
                    }
                    sb.append(" ]");
                }
                sb.append("}mCertificatesArray: 0");
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public a2(il ilVar) {
        this.a = 0;
        this.b = new mk(30);
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = ilVar;
        this.f = new e0(16, this);
    }
}
