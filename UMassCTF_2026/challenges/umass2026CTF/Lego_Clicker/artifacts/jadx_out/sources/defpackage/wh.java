package defpackage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.LegoClicker.R;
import com.example.LegoClicker.RA;
import com.example.LegoClicker.model.GameState;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class wh extends l2 {
    public static final int[] l = {-1, -256, -16711681, -8388864, -38476, -29696, -16711809, -10496};
    public m1 a;
    public je b;
    public pn c;
    public o6 e;
    public o6 f;
    public final Random d = new Random();
    public boolean g = true;
    public long h = 0;
    public int i = 0;
    public int j = 0;
    public int k = 0;

    public static String h(int... iArr) {
        char[] cArr = new char[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            cArr[i] = (char) (iArr[i] ^ 58);
        }
        return new String(cArr);
    }

    public final void d() {
        this.a.c.animate().cancel();
        this.a.c.setScaleX(0.84f);
        this.a.c.setScaleY(0.84f);
        this.a.c.animate().scaleX(1.06f).scaleY(1.06f).setDuration(80L).withEndAction(new th(this, 2)).start();
    }

    public final void e() {
        try {
            o();
            int i = this.j + 1;
            this.j = i;
            if (i % 10 == 0) {
                g();
            }
        } catch (Exception unused) {
        }
    }

    public final void f() {
        o6 o6Var = new o6(this.b.c(), new vh(this, 2), 1);
        this.f = o6Var;
        o6Var.e = this.b.a;
    }

    public final void g() {
        double d = this.b.a;
        if (this.g) {
            this.e.e = d;
            this.a.e.post(new th(this, 0));
        } else {
            this.f.e = d;
            this.a.e.post(new th(this, 1));
        }
    }

    public final void i() {
        je jeVar = this.b;
        jeVar.getClass();
        this.e = new o6(new ArrayList(jeVar.d.values()), new vh(this, 0), 0);
        this.f = new o6(this.b.c(), new vh(this, 1), 1);
        this.a.e.setLayoutManager(new LinearLayoutManager());
    }

    public final void j() {
        this.a.c.setOnClickListener(new uh(this, 0));
        this.a.a.setOnClickListener(new uh(this, 1));
        this.a.b.setOnClickListener(new uh(this, 2));
    }

    public final void k() {
        this.g = true;
        this.a.a.setTextColor(getResources().getColor(R.color.lego_white, null));
        this.a.b.setTextColor(getResources().getColor(R.color.lego_yellow, null));
        this.a.e.setAdapter(this.e);
        g();
    }

    public final void l(double d) {
        g2 g2Var = new g2(this);
        c2 c2Var = (c2) g2Var.b;
        c2Var.d = c2Var.a.getText(R.string.offline_title);
        c2Var.f = getString(R.string.offline_message, d.s(d));
        c2Var.g = c2Var.a.getText(R.string.ok);
        c2Var.h = null;
        g2Var.a().show();
    }

    public final void m(String str) {
        TextView textView = this.a.j;
        textView.animate().cancel();
        textView.setText(h(10025, 26) + str + h(26, 74, 79, 72, 89, 82, 91, 73, 95, 94, 27));
        textView.setAlpha(1.0f);
        textView.setVisibility(0);
        textView.animate().alpha(0.0f).setStartDelay(1200L).setDuration(400L).withEndAction(new k1(5, textView)).start();
        if (this.k >= 12) {
            return;
        }
        try {
            ConstraintLayout constraintLayout = this.a.d;
            constraintLayout.post(new w2(this, 1, constraintLayout));
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void n(double d) {
        Random random = this.d;
        if (this.i >= 8) {
            return;
        }
        int i = 1;
        try {
            m1 m1Var = this.a;
            ConstraintLayout constraintLayout = m1Var.d;
            m1Var.c.getLocationInWindow(new int[2]);
            constraintLayout.getLocationInWindow(new int[2]);
            float width = (this.a.c.getWidth() * 0.5f) + (r8[0] - r7[0]);
            float fNextFloat = ((random.nextFloat() - 0.5f) * 200.0f) + width;
            float fNextFloat2 = (random.nextFloat() * 50.0f) + (this.a.c.getHeight() * 0.25f) + (r8[1] - r7[1]);
            TextView textView = new TextView(this);
            textView.setText("+".concat(d.s(d)));
            textView.setTextSize(random.nextInt(10) + 15.0f);
            textView.setTextColor(l[random.nextInt(8)]);
            textView.setTypeface(null, 1);
            textView.setShadowLayer(6.0f, 2.0f, 2.0f, -15658735);
            textView.setRotation((random.nextFloat() - 0.5f) * 30.0f);
            textView.setAlpha(1.0f);
            textView.setScaleX(1.35f);
            textView.setScaleY(1.35f);
            constraintLayout.addView(textView, new z7(-2, -2));
            textView.setX(fNextFloat);
            textView.setY(fNextFloat2);
            this.i++;
            textView.animate().y(fNextFloat2 - ((random.nextFloat() * 120.0f) + 160.0f)).alpha(0.0f).scaleX(0.65f).scaleY(0.65f).setDuration(random.nextInt(300) + 750).setStartDelay(60L).withEndAction(new wa(this, textView, constraintLayout, i)).start();
        } catch (Exception unused) {
            this.i = Math.max(0, this.i - 1);
        }
    }

    public final void o() {
        this.a.i.setText(d.s(this.b.a));
        TextView textView = this.a.h;
        StringBuilder sb = new StringBuilder();
        double d = this.b.d();
        sb.append(d <= 0.0d ? "0.00" : d < 1.0d ? String.format(Locale.US, "%.2f", Double.valueOf(d)) : d < 10.0d ? String.format(Locale.US, "%.1f", Double.valueOf(d)) : d < 1000.0d ? String.format(Locale.US, "%,.0f", Double.valueOf(d)) : d.s(d));
        sb.append(h(26, 74, 95, 72, 26, 73, 95, 89, 85, 84, 94));
        textView.setText(sb.toString());
    }

    @Override // defpackage.vc, androidx.activity.b, defpackage.q7, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        GameState gameState = null;
        View viewInflate = getLayoutInflater().inflate(R.layout.activity_main, (ViewGroup) null, false);
        int i = R.id.btn_leaderboard;
        if (((Button) hb.x(viewInflate, R.id.btn_leaderboard)) != null) {
            i = R.id.btn_tab_buildings;
            Button button = (Button) hb.x(viewInflate, R.id.btn_tab_buildings);
            if (button != null) {
                i = R.id.btn_tab_upgrades;
                Button button2 = (Button) hb.x(viewInflate, R.id.btn_tab_upgrades);
                if (button2 != null) {
                    i = R.id.img_lego_brick;
                    ImageView imageView = (ImageView) hb.x(viewInflate, R.id.img_lego_brick);
                    if (imageView != null) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
                        i = R.id.rv_shop;
                        RecyclerView recyclerView = (RecyclerView) hb.x(viewInflate, R.id.rv_shop);
                        if (recyclerView != null) {
                            i = R.id.tab_bar;
                            LinearLayout linearLayout = (LinearLayout) hb.x(viewInflate, R.id.tab_bar);
                            if (linearLayout != null) {
                                i = R.id.top_bar;
                                LinearLayout linearLayout2 = (LinearLayout) hb.x(viewInflate, R.id.top_bar);
                                if (linearLayout2 != null) {
                                    i = R.id.tv_bps;
                                    TextView textView = (TextView) hb.x(viewInflate, R.id.tv_bps);
                                    if (textView != null) {
                                        i = R.id.tv_brick_count;
                                        TextView textView2 = (TextView) hb.x(viewInflate, R.id.tv_brick_count);
                                        if (textView2 != null) {
                                            i = R.id.tv_purchase_banner;
                                            TextView textView3 = (TextView) hb.x(viewInflate, R.id.tv_purchase_banner);
                                            if (textView3 != null) {
                                                this.a = new m1(constraintLayout, button, button2, imageView, constraintLayout, recyclerView, linearLayout, linearLayout2, textView, textView2, textView3);
                                                setContentView(constraintLayout);
                                                this.c = new pn(this);
                                                je jeVar = new je();
                                                this.b = jeVar;
                                                jeVar.j = this;
                                                try {
                                                    pn pnVar = this.c;
                                                    String string = pnVar.a.getString(pn.e, null);
                                                    if (string != null) {
                                                        gameState = (GameState) pnVar.b.b(string, GameState.class);
                                                    }
                                                    if (gameState != null) {
                                                        this.h = gameState.lastSaveTimestamp;
                                                        this.b.b(gameState);
                                                    }
                                                } catch (Exception unused) {
                                                }
                                                try {
                                                    i();
                                                } catch (Exception unused2) {
                                                }
                                                try {
                                                    j();
                                                } catch (Exception unused3) {
                                                }
                                                try {
                                                    k();
                                                } catch (Exception unused4) {
                                                }
                                                try {
                                                    o();
                                                } catch (Exception unused5) {
                                                }
                                                try {
                                                    g();
                                                    return;
                                                } catch (Exception unused6) {
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(viewInflate.getResources().getResourceName(i)));
    }

    @Override // defpackage.l2, defpackage.vc, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        je jeVar = this.b;
        jeVar.i = false;
        b6 b6Var = jeVar.h;
        if (b6Var != null) {
            jeVar.g.removeCallbacks(b6Var);
        }
    }

    public void onLeaderboardClick(View view) {
        startActivity(new Intent(this, (Class<?>) RA.class));
    }

    @Override // defpackage.vc, android.app.Activity
    public final void onPause() {
        super.onPause();
        try {
            je jeVar = this.b;
            jeVar.i = false;
            b6 b6Var = jeVar.h;
            if (b6Var != null) {
                jeVar.g.removeCallbacks(b6Var);
            }
        } catch (Exception unused) {
        }
        try {
            GameState gameStateE = this.b.e();
            pn pnVar = this.c;
            pnVar.a.edit().putString(pn.e, pnVar.b.e(gameStateE)).apply();
            this.c.c(this.b.b);
            this.h = gameStateE.lastSaveTimestamp;
        } catch (Exception unused2) {
        }
    }

    @Override // defpackage.vc, android.app.Activity
    public final void onResume() {
        double d;
        super.onResume();
        try {
            long j = this.h;
            if (j > 0) {
                je jeVar = this.b;
                jeVar.getClass();
                long jMin = Math.min((System.currentTimeMillis() - j) / 1000, 28800L);
                if (jMin <= 0) {
                    d = 0.0d;
                } else {
                    d = jMin * jeVar.d();
                    jeVar.a += d;
                    jeVar.b += d;
                }
                if (d >= 5.0d) {
                    l(d);
                }
                this.h = 0L;
            }
            je jeVar2 = this.b;
            if (jeVar2.i) {
                return;
            }
            jeVar2.i = true;
            b6 b6Var = new b6(5, jeVar2);
            jeVar2.h = b6Var;
            jeVar2.g.postDelayed(b6Var, 50L);
        } catch (Exception unused) {
        }
    }
}
