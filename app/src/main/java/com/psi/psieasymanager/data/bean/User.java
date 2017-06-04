package com.psi.psieasymanager.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dorado on 2017/4/22.
 */

@Entity public class User {
  @Id private Long id;

  @Generated(hash = 1248599927)
  public User(Long id) {
      this.id = id;
  }

  @Generated(hash = 586692638)
  public User() {
  }

  public Long getId() {
      return this.id;
  }

  public void setId(Long id) {
      this.id = id;
  }
}
