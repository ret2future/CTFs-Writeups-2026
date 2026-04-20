package defpackage;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class vg extends AbstractSet {
    public final /* synthetic */ int a;
    public final /* synthetic */ xg b;

    public /* synthetic */ vg(xg xgVar, int i) {
        this.a = i;
        this.b = xgVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        int i = this.a;
        xg xgVar = this.b;
        switch (i) {
            case 0:
                xgVar.clear();
                break;
            default:
                xgVar.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        wg wgVarA;
        int i = this.a;
        xg xgVar = this.b;
        switch (i) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                wg wgVar = null;
                if (key != null) {
                    try {
                        wgVarA = xgVar.a(key, false);
                    } catch (ClassCastException unused) {
                        wgVarA = null;
                    }
                    break;
                } else {
                    wgVarA = null;
                }
                if (wgVarA != null && Objects.equals(wgVarA.h, entry.getValue())) {
                    wgVar = wgVarA;
                }
                return wgVar != null;
            default:
                return xgVar.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        int i = this.a;
        xg xgVar = this.b;
        switch (i) {
            case 0:
                return new ug(xgVar, 0);
            default:
                return new ug(xgVar, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        wg wgVarA;
        int i = this.a;
        wg wgVarA2 = null;
        xg xgVar = this.b;
        switch (i) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    Object key = entry.getKey();
                    if (key != null) {
                        try {
                            wgVarA = xgVar.a(key, false);
                        } catch (ClassCastException unused) {
                            wgVarA = null;
                        }
                    } else {
                        wgVarA = null;
                    }
                    if (wgVarA != null && Objects.equals(wgVarA.h, entry.getValue())) {
                        wgVarA2 = wgVarA;
                    }
                    if (wgVarA2 != null) {
                        xgVar.c(wgVarA2, true);
                        break;
                    }
                    break;
                }
                break;
            default:
                if (obj != null) {
                    try {
                        wgVarA2 = xgVar.a(obj, false);
                        break;
                    } catch (ClassCastException unused2) {
                    }
                }
                if (wgVarA2 != null) {
                    xgVar.c(wgVarA2, true);
                }
                if (wgVarA2 != null) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        int i = this.a;
        xg xgVar = this.b;
        switch (i) {
        }
        return xgVar.d;
    }
}
