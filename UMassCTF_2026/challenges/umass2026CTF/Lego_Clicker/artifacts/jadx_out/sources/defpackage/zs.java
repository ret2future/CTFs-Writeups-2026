package defpackage;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class zs {
    public final LinkedHashMap a = new LinkedHashMap();

    public final void a() {
        for (vs vsVar : this.a.values()) {
            vsVar.c = true;
            HashMap map = vsVar.a;
            if (map != null) {
                synchronized (map) {
                    try {
                        Iterator it = vsVar.a.values().iterator();
                        while (it.hasNext()) {
                            vs.a(it.next());
                        }
                    } finally {
                    }
                }
            }
            LinkedHashSet linkedHashSet = vsVar.b;
            if (linkedHashSet != null) {
                synchronized (linkedHashSet) {
                    try {
                        Iterator it2 = vsVar.b.iterator();
                        while (it2.hasNext()) {
                            vs.a((Closeable) it2.next());
                        }
                    } finally {
                    }
                }
            }
            vsVar.b();
        }
        this.a.clear();
    }
}
