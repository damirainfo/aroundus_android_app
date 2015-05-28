package com.hilinju.android.ui.dialog;

/**
 * Created by qiuxj on 2015/5/26.
 */
public interface DialogControl {

    public abstract void hideWaitDialog();

    public abstract WaitDialog showWaitDialog();

    public abstract WaitDialog showWaitDialog(int resId);

    public abstract WaitDialog showWaitDialog(String text);

}
