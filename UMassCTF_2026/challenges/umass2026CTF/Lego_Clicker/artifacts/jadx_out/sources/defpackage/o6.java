package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.LegoClicker.R;
import com.example.LegoClicker.model.Building;
import com.example.LegoClicker.model.BuildingType;
import com.example.LegoClicker.model.Upgrade;
import com.example.LegoClicker.model.UpgradeType;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class o6 extends jl {
    public final /* synthetic */ int c;
    public final ArrayList d;
    public double e;
    public final vh f;

    public /* synthetic */ o6(ArrayList arrayList, vh vhVar, int i) {
        this.c = i;
        this.d = arrayList;
        this.f = vhVar;
    }

    @Override // defpackage.jl
    public final int a() {
        switch (this.c) {
        }
        return this.d.size();
    }

    @Override // defpackage.jl
    public final void b(em emVar, int i) {
        final boolean z;
        int i2 = this.c;
        ArrayList arrayList = this.d;
        switch (i2) {
            case 0:
                n6 n6Var = (n6) emVar;
                final Building building = (Building) arrayList.get(i);
                TextView textView = n6Var.t;
                Button button = n6Var.w;
                textView.setText(building.type.displayName);
                n6Var.u.setText(String.valueOf(building.owned));
                double currentCost = building.getCurrentCost();
                n6Var.v.setText(d.s(currentCost));
                z = this.e >= currentCost;
                button.setEnabled(z);
                button.setAlpha(z ? 1.0f : 0.4f);
                button.setOnClickListener(new View.OnClickListener() { // from class: m6
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        vh vhVar = this.a.f;
                        BuildingType buildingType = building.type;
                        wh whVar = (wh) vhVar.b;
                        je jeVar = whVar.b;
                        Building building2 = (Building) jeVar.d.get(buildingType);
                        if (building2 == null) {
                            return;
                        }
                        double currentCost2 = building2.getCurrentCost();
                        double d = jeVar.a;
                        if (d < currentCost2) {
                            return;
                        }
                        jeVar.a = d - currentCost2;
                        building2.owned++;
                        wh whVar2 = jeVar.j;
                        if (whVar2 != null) {
                            whVar2.e();
                        }
                        whVar.m(buildingType.displayName);
                        whVar.g();
                    }
                });
                break;
            default:
                or orVar = (or) emVar;
                final Upgrade upgrade = (Upgrade) arrayList.get(i);
                TextView textView2 = orVar.t;
                View view = orVar.a;
                textView2.setText(upgrade.type.displayName);
                orVar.u.setText(upgrade.type.description);
                orVar.v.setText(d.s(upgrade.type.cost));
                z = this.e >= upgrade.type.cost;
                view.setAlpha(z ? 1.0f : 0.5f);
                view.setOnClickListener(new View.OnClickListener() { // from class: nr
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        if (z) {
                            vh vhVar = this.a.f;
                            UpgradeType upgradeType = upgrade.type;
                            int i3 = vhVar.a;
                            wh whVar = (wh) vhVar.b;
                            switch (i3) {
                                case 1:
                                    if (whVar.b.a(upgradeType)) {
                                        whVar.m(upgradeType.displayName);
                                        whVar.f();
                                        if (!whVar.g) {
                                            whVar.a.e.setAdapter(whVar.f);
                                        }
                                    }
                                    break;
                                default:
                                    if (whVar.b.a(upgradeType)) {
                                        whVar.m(upgradeType.displayName);
                                        whVar.f();
                                        if (!whVar.g) {
                                            whVar.a.e.setAdapter(whVar.f);
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                });
                break;
        }
    }

    @Override // defpackage.jl
    public final em c(ViewGroup viewGroup) {
        switch (this.c) {
            case 0:
                View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_building, viewGroup, false);
                n6 n6Var = new n6(viewInflate);
                n6Var.t = (TextView) viewInflate.findViewById(R.id.tv_building_name);
                n6Var.u = (TextView) viewInflate.findViewById(R.id.tv_building_owned);
                n6Var.v = (TextView) viewInflate.findViewById(R.id.tv_building_cost);
                n6Var.w = (Button) viewInflate.findViewById(R.id.btn_buy_building);
                return n6Var;
            default:
                View viewInflate2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_upgrade, viewGroup, false);
                or orVar = new or(viewInflate2);
                orVar.t = (TextView) viewInflate2.findViewById(R.id.tv_upgrade_name);
                orVar.u = (TextView) viewInflate2.findViewById(R.id.tv_upgrade_desc);
                orVar.v = (TextView) viewInflate2.findViewById(R.id.tv_upgrade_cost);
                return orVar;
        }
    }
}
