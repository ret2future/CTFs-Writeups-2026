package defpackage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class d7 {
    public final HashMap a = new HashMap();
    public final HashMap b;

    public d7(HashMap map) {
        this.b = map;
        for (Map.Entry entry : map.entrySet()) {
            dg dgVar = (dg) entry.getValue();
            List arrayList = (List) this.a.get(dgVar);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.a.put(dgVar, arrayList);
            }
            arrayList.add((e7) entry.getKey());
        }
    }

    public static void a(List list, kg kgVar, dg dgVar, jg jgVar) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                e7 e7Var = (e7) list.get(size);
                Method method = e7Var.b;
                try {
                    int i = e7Var.a;
                    if (i == 0) {
                        method.invoke(jgVar, null);
                    } else if (i == 1) {
                        method.invoke(jgVar, kgVar);
                    } else if (i == 2) {
                        method.invoke(jgVar, kgVar, dgVar);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException("Failed to call observer method", e2.getCause());
                }
            }
        }
    }
}
