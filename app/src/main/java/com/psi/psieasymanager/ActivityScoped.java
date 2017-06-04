package com.psi.psieasymanager;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by dorado on 2017/4/21.
 */
@Scope @Documented @Retention(RetentionPolicy.RUNTIME) public @interface ActivityScoped {
}
