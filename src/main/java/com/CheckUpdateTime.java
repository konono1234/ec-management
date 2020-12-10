package com;

/*
 * 排他制御用の機能を持ったクラスです。編集用に使用してください。
 */

public class CheckUpdateTime {

  private String formUpdate_date;

  private String dbUpdate_date;

  public CheckUpdateTime(String formUpdate_date, String dbUpdate_date) {
    this.formUpdate_date = formUpdate_date;
    this.dbUpdate_date = dbUpdate_date;
  }

  public boolean checkExclusion() {

    boolean result = formUpdate_date.equals(dbUpdate_date);

    return result;
  }
}
