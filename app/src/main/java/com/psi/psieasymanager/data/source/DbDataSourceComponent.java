package com.psi.psieasymanager.data.source;

import android.app.Activity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by dorado on 2017/5/9.
 */

@Singleton @Component public interface DbDataSourceComponent {
  void inject(Activity activity);
}
