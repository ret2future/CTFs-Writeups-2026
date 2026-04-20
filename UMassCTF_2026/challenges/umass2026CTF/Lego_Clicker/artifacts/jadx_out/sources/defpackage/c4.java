package defpackage;

import android.app.Activity;
import android.content.ClipData;
import android.os.Build;
import android.text.Selection;
import android.text.Spannable;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class c4 {
    public static boolean a(DragEvent dragEvent, TextView textView, Activity activity) {
        t8 e0Var;
        activity.requestDragAndDropPermissions(dragEvent);
        int offsetForPosition = textView.getOffsetForPosition(dragEvent.getX(), dragEvent.getY());
        textView.beginBatchEdit();
        try {
            Selection.setSelection((Spannable) textView.getText(), offsetForPosition);
            ClipData clipData = dragEvent.getClipData();
            if (Build.VERSION.SDK_INT >= 31) {
                e0Var = new e0(clipData, 3);
            } else {
                u8 u8Var = new u8();
                u8Var.b = clipData;
                u8Var.c = 3;
                e0Var = u8Var;
            }
            os.f(textView, e0Var.build());
            textView.endBatchEdit();
            return true;
        } catch (Throwable th) {
            textView.endBatchEdit();
            throw th;
        }
    }

    public static boolean b(DragEvent dragEvent, View view, Activity activity) {
        t8 e0Var;
        activity.requestDragAndDropPermissions(dragEvent);
        ClipData clipData = dragEvent.getClipData();
        if (Build.VERSION.SDK_INT >= 31) {
            e0Var = new e0(clipData, 3);
        } else {
            u8 u8Var = new u8();
            u8Var.b = clipData;
            u8Var.c = 3;
            e0Var = u8Var;
        }
        os.f(view, e0Var.build());
        return true;
    }
}
