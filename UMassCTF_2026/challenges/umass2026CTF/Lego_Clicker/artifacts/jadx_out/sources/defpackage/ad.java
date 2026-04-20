package defpackage;

import android.util.Log;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ad implements r1 {
    public final /* synthetic */ int a;
    public final /* synthetic */ fd b;

    public /* synthetic */ ad(gd gdVar, int i) {
        this.a = i;
        this.b = gdVar;
    }

    @Override // defpackage.r1
    public final void a(Object obj) {
        int i = this.a;
        fd fdVar = this.b;
        switch (i) {
            case 0:
                Map map = (Map) obj;
                ArrayList arrayList = new ArrayList(map.values());
                int[] iArr = new int[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    iArr[i2] = ((Boolean) arrayList.get(i2)).booleanValue() ? 0 : -1;
                }
                ed edVar = (ed) fdVar.x.pollFirst();
                if (edVar == null) {
                    Log.w("FragmentManager", "No permissions were requested for " + this);
                } else {
                    String str = edVar.a;
                    fdVar.c.a();
                    Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str);
                }
                break;
            case 1:
                ed edVar2 = (ed) fdVar.x.pollFirst();
                if (edVar2 == null) {
                    Log.w("FragmentManager", "No Activities were started for result for " + this);
                } else {
                    String str2 = edVar2.a;
                    fdVar.c.a();
                    Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str2);
                }
                break;
            default:
                ed edVar3 = (ed) fdVar.x.pollFirst();
                if (edVar3 == null) {
                    Log.w("FragmentManager", "No IntentSenders were started for " + this);
                } else {
                    String str3 = edVar3.a;
                    fdVar.c.a();
                    Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str3);
                }
                break;
        }
    }
}
