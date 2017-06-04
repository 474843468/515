package com.psi.psieasymanager;

import android.content.Context;
import com.psi.psieasymanager.data.source.DbDataSource;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by dorado on 2017/4/21.
 * //ActivityModule：注入Activity，同时规定Activity所对应的域是@PerActivity
 * model提供你的对象，也就是你用到的对象，初始化你要的present的时候，或者全局用到的对象
 */
@Module public class AppModule {
  private Context context;

  public AppModule(Context context) {

    this.context = context;
  }

  @Provides @Singleton App provideApplication() {

    return (App) context.getApplicationContext();
  }

  @Provides @Singleton Context provideContext() {

    return context;
  }
  @Provides @Singleton DbDataSource provideDbDataSource() {

    return new DbDataSource();
  }
}
