package cn.sm1234.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Demo1 {

	public static void main(String[] args) {
//		������ȫ����������Facroty
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//		������ȫ������
		SecurityManager securityManager = factory.getInstance();
//		��ʼ��������
		SecurityUtils.setSecurityManager(securityManager);
//		��SecurityUtils�������л�ȡSubject
		Subject subject = SecurityUtils.getSubject();
//		��֤��½,
//		AuthenticationToken:���ڷ�װ�û�������û�������
		AuthenticationToken token = new UsernamePasswordToken("eric","123456");
		try {
			subject.login(token);
			Object principal = subject.getPrincipal();//SimpleAuthenticationInfo�ĵ�һ������
//			���û���κ��쳣��������֤�ɹ���
			System.out.println("��½�ɹ�:"+principal);
		} catch (UnknownAccountException e) {
			System.out.println("�ʻ�������");
		} catch (IncorrectCredentialsException e) {
			System.out.println("�������");
		} catch (AuthenticationException e) {
			System.out.println("��֤����");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��½����");
		}
	}
}
