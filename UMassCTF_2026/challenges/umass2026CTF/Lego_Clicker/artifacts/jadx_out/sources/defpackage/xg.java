package defpackage;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class xg extends AbstractMap implements Serializable {
    public static final ke i = new ke(1);
    public final boolean b;
    public wg c;
    public final wg f;
    public vg g;
    public vg h;
    public int d = 0;
    public int e = 0;
    public final Comparator a = i;

    public xg(boolean z) {
        this.b = z;
        this.f = new wg(z);
    }

    public final wg a(Object obj, boolean z) {
        int iCompareTo;
        wg wgVar;
        wg wgVar2 = this.c;
        ke keVar = i;
        Comparator comparator = this.a;
        if (wgVar2 != null) {
            Comparable comparable = comparator == keVar ? (Comparable) obj : null;
            while (true) {
                Object obj2 = wgVar2.f;
                iCompareTo = comparable != null ? comparable.compareTo(obj2) : comparator.compare(obj, obj2);
                if (iCompareTo == 0) {
                    return wgVar2;
                }
                wg wgVar3 = iCompareTo < 0 ? wgVar2.b : wgVar2.c;
                if (wgVar3 == null) {
                    break;
                }
                wgVar2 = wgVar3;
            }
        } else {
            iCompareTo = 0;
        }
        wg wgVar4 = wgVar2;
        if (!z) {
            return null;
        }
        wg wgVar5 = this.f;
        if (wgVar4 != null) {
            wgVar = new wg(this.b, wgVar4, obj, wgVar5, wgVar5.e);
            if (iCompareTo < 0) {
                wgVar4.b = wgVar;
            } else {
                wgVar4.c = wgVar;
            }
            b(wgVar4, true);
        } else {
            if (comparator == keVar && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName().concat(" is not Comparable"));
            }
            wgVar = new wg(this.b, wgVar4, obj, wgVar5, wgVar5.e);
            this.c = wgVar;
        }
        this.d++;
        this.e++;
        return wgVar;
    }

    public final void b(wg wgVar, boolean z) {
        while (wgVar != null) {
            wg wgVar2 = wgVar.b;
            wg wgVar3 = wgVar.c;
            int i2 = wgVar2 != null ? wgVar2.i : 0;
            int i3 = wgVar3 != null ? wgVar3.i : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                wg wgVar4 = wgVar3.b;
                wg wgVar5 = wgVar3.c;
                int i5 = (wgVar4 != null ? wgVar4.i : 0) - (wgVar5 != null ? wgVar5.i : 0);
                if (i5 == -1 || (i5 == 0 && !z)) {
                    e(wgVar);
                } else {
                    f(wgVar3);
                    e(wgVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                wg wgVar6 = wgVar2.b;
                wg wgVar7 = wgVar2.c;
                int i6 = (wgVar6 != null ? wgVar6.i : 0) - (wgVar7 != null ? wgVar7.i : 0);
                if (i6 == 1 || (i6 == 0 && !z)) {
                    f(wgVar);
                } else {
                    e(wgVar2);
                    f(wgVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                wgVar.i = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                wgVar.i = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            wgVar = wgVar.a;
        }
    }

    public final void c(wg wgVar, boolean z) {
        wg wgVar2;
        wg wgVar3;
        int i2;
        if (z) {
            wg wgVar4 = wgVar.e;
            wgVar4.d = wgVar.d;
            wgVar.d.e = wgVar4;
        }
        wg wgVar5 = wgVar.b;
        wg wgVar6 = wgVar.c;
        wg wgVar7 = wgVar.a;
        int i3 = 0;
        if (wgVar5 == null || wgVar6 == null) {
            if (wgVar5 != null) {
                d(wgVar, wgVar5);
                wgVar.b = null;
            } else if (wgVar6 != null) {
                d(wgVar, wgVar6);
                wgVar.c = null;
            } else {
                d(wgVar, null);
            }
            b(wgVar7, false);
            this.d--;
            this.e++;
            return;
        }
        if (wgVar5.i > wgVar6.i) {
            wg wgVar8 = wgVar5.c;
            while (true) {
                wg wgVar9 = wgVar8;
                wgVar3 = wgVar5;
                wgVar5 = wgVar9;
                if (wgVar5 == null) {
                    break;
                } else {
                    wgVar8 = wgVar5.c;
                }
            }
        } else {
            wg wgVar10 = wgVar6.b;
            while (true) {
                wgVar2 = wgVar6;
                wgVar6 = wgVar10;
                if (wgVar6 == null) {
                    break;
                } else {
                    wgVar10 = wgVar6.b;
                }
            }
            wgVar3 = wgVar2;
        }
        c(wgVar3, false);
        wg wgVar11 = wgVar.b;
        if (wgVar11 != null) {
            i2 = wgVar11.i;
            wgVar3.b = wgVar11;
            wgVar11.a = wgVar3;
            wgVar.b = null;
        } else {
            i2 = 0;
        }
        wg wgVar12 = wgVar.c;
        if (wgVar12 != null) {
            i3 = wgVar12.i;
            wgVar3.c = wgVar12;
            wgVar12.a = wgVar3;
            wgVar.c = null;
        }
        wgVar3.i = Math.max(i2, i3) + 1;
        d(wgVar, wgVar3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.c = null;
        this.d = 0;
        this.e++;
        wg wgVar = this.f;
        wgVar.e = wgVar;
        wgVar.d = wgVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        wg wgVarA = null;
        if (obj != null) {
            try {
                wgVarA = a(obj, false);
            } catch (ClassCastException unused) {
            }
        }
        return wgVarA != null;
    }

    public final void d(wg wgVar, wg wgVar2) {
        wg wgVar3 = wgVar.a;
        wgVar.a = null;
        if (wgVar2 != null) {
            wgVar2.a = wgVar3;
        }
        if (wgVar3 == null) {
            this.c = wgVar2;
        } else if (wgVar3.b == wgVar) {
            wgVar3.b = wgVar2;
        } else {
            wgVar3.c = wgVar2;
        }
    }

    public final void e(wg wgVar) {
        wg wgVar2 = wgVar.b;
        wg wgVar3 = wgVar.c;
        wg wgVar4 = wgVar3.b;
        wg wgVar5 = wgVar3.c;
        wgVar.c = wgVar4;
        if (wgVar4 != null) {
            wgVar4.a = wgVar;
        }
        d(wgVar, wgVar3);
        wgVar3.b = wgVar;
        wgVar.a = wgVar3;
        int iMax = Math.max(wgVar2 != null ? wgVar2.i : 0, wgVar4 != null ? wgVar4.i : 0) + 1;
        wgVar.i = iMax;
        wgVar3.i = Math.max(iMax, wgVar5 != null ? wgVar5.i : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        vg vgVar = this.g;
        if (vgVar != null) {
            return vgVar;
        }
        vg vgVar2 = new vg(this, 0);
        this.g = vgVar2;
        return vgVar2;
    }

    public final void f(wg wgVar) {
        wg wgVar2 = wgVar.b;
        wg wgVar3 = wgVar.c;
        wg wgVar4 = wgVar2.b;
        wg wgVar5 = wgVar2.c;
        wgVar.b = wgVar5;
        if (wgVar5 != null) {
            wgVar5.a = wgVar;
        }
        d(wgVar, wgVar2);
        wgVar2.c = wgVar;
        wgVar.a = wgVar2;
        int iMax = Math.max(wgVar3 != null ? wgVar3.i : 0, wgVar5 != null ? wgVar5.i : 0) + 1;
        wgVar.i = iMax;
        wgVar2.i = Math.max(iMax, wgVar4 != null ? wgVar4.i : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        wg wgVarA;
        if (obj != null) {
            try {
                wgVarA = a(obj, false);
            } catch (ClassCastException unused) {
                wgVarA = null;
            }
        } else {
            wgVarA = null;
        }
        if (wgVarA != null) {
            return wgVarA.h;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        vg vgVar = this.h;
        if (vgVar != null) {
            return vgVar;
        }
        vg vgVar2 = new vg(this, 1);
        this.h = vgVar2;
        return vgVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        if (obj2 == null && !this.b) {
            throw new NullPointerException("value == null");
        }
        wg wgVarA = a(obj, true);
        Object obj3 = wgVarA.h;
        wgVarA.h = obj2;
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        wg wgVarA;
        if (obj != null) {
            try {
                wgVarA = a(obj, false);
            } catch (ClassCastException unused) {
                wgVarA = null;
            }
        } else {
            wgVarA = null;
        }
        if (wgVarA != null) {
            c(wgVarA, true);
        }
        if (wgVarA != null) {
            return wgVarA.h;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.d;
    }
}
