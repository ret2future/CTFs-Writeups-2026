package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ci extends hb {
    public static Map f0(ArrayList arrayList) {
        int size = arrayList.size();
        if (size == 0) {
            return pb.a;
        }
        int i = 0;
        if (size == 1) {
            jk jkVar = (jk) arrayList.get(0);
            jkVar.getClass();
            Map mapSingletonMap = Collections.singletonMap(jkVar.a, jkVar.b);
            mapSingletonMap.getClass();
            return mapSingletonMap;
        }
        int size2 = arrayList.size();
        if (size2 >= 0) {
            size2 = size2 < 3 ? size2 + 1 : size2 < 1073741824 ? (int) ((size2 / 0.75f) + 1.0f) : Integer.MAX_VALUE;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(size2);
        int size3 = arrayList.size();
        while (i < size3) {
            Object obj = arrayList.get(i);
            i++;
            jk jkVar2 = (jk) obj;
            linkedHashMap.put(jkVar2.a, jkVar2.b);
        }
        return linkedHashMap;
    }
}
