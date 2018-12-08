package cn.sm1234.realms;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.*;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm{
//	授权 获取授权信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("执行授权...表示认证已成功");
		SimpleAuthorizationInfo  info = new SimpleAuthorizationInfo();
//		资源码授权
		info.addStringPermission("productAdd");
//		通配符资源授权
		info.addStringPermission("product:*");
//		角色授权
		info.addRole("admin");
		return info;
	}
//	认证 获取认证信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("执行认证...判断用户名和密码是否正确");
//		获取用户输入的帐户信息
		UsernamePasswordToken uToken = (UsernamePasswordToken)arg0;//arg0是AuthenticationToken类型，所以arg0 = token = new UsernamePasswordToken("eric","123456");
		String username = uToken.getUsername();
//		获取数据库的帐户信息，模拟数据库的帐户信息
		String name = "jack";
		String password = "1234";
//		判断用户名
		if(!username.equals(name)){
			return null;//shiro底层自动抛出UnknownAccountException异常，类似于切面编程？
		}
//		判断密码，正确则通过，错误则抛出异常IncorrectCredentialsException
//		参数一，principal,用于把数据传回到login，判断。
//		参数二，数据库的密码，shiro，底层对比密码的结果，密码正确，认证通过，不正确抛出异常IncorrectCredentialsException
//		参数三，realm的别名，用于有多个用户表时，一般默认为""。
		return new SimpleAuthenticationInfo("callback",password,"");//SimpleAuthenticationInfo是接口AuthenticationInfo的实现类
	}	
}
