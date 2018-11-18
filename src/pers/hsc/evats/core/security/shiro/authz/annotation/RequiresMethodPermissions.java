package pers.hsc.evats.core.security.shiro.authz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.shiro.authz.annotation.Logical;

/**
 * 自定义权限控制器
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresMethodPermissions {

	String[] value();

	Logical logical() default Logical.AND;

}
