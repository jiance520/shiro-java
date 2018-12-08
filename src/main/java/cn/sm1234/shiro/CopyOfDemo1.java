package cn.sm1234.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class CopyOfDemo1 {

	public static void main(String[] args) {
//		创建安全管理器工厂Facroty
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//		创建安全管理器
		SecurityManager securityManager = factory.getInstance();
//		初始化工具类
		SecurityUtils.setSecurityManager(securityManager);
//		从SecurityUtils工具类中获取Subject
		Subject subject = SecurityUtils.getSubject();
//		认证登陆,
//		AuthenticationToken:用于封装用户输入的用户名密码
		AuthenticationToken token = new UsernamePasswordToken("jack","1234");
		try {
			subject.login(token);
			Object principal = subject.getPrincipal();//SimpleAuthenticationInfo的第一个参数
//			如果没有任何异常，代表认证成功。
			System.out.println("登陆成功:"+principal);
			
//			进行Shiro的授权
//			基于资源的授权
//			判断当前登陆用户是否有"productAdd"功能
//			isPermitted();返回true，有权限，false:没有权限
			System.out.println("productAdd="+subject.isPermitted("productAdd"));
			System.out.println("product:add="+subject.isPermitted("product:add"));
			System.out.println("product:del="+subject.isPermitted("product:del"));
			
//			基于角色的授权
//			判断当前登陆用户是否为"admin"
			System.out.println("admin="+subject.hasRole("admin"));
		} catch (UnknownAccountException e) {
			System.out.println("帐户不存在");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码错误");
		} catch (AuthenticationException e) {
			System.out.println("认证错误");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("登陆错误");
		}
	}
}
